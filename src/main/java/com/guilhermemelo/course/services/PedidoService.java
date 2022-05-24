package com.guilhermemelo.course.services;

import com.guilhermemelo.course.domain.*;
import com.guilhermemelo.course.enums.EstadoPagamento;
import com.guilhermemelo.course.repositories.ClienteRepository;
import com.guilhermemelo.course.repositories.ItemPedidoRepository;
import com.guilhermemelo.course.repositories.PagamentoRepository;
import com.guilhermemelo.course.repositories.PedidoRepository;
import com.guilhermemelo.course.security.UserSS;
import com.guilhermemelo.course.services.exception.AuthorizationException;
import com.guilhermemelo.course.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;

    public Pedido findById(Integer id) {
        Optional<Pedido> pedidoId = pedidoRepository.findById(id);
        return pedidoId.orElseThrow(() -> new ObjectNotFoundException("Object não encontrado! Id: " + id +
                ", Tipo: " + Pedido.class.getName()));
    }

    @Transactional
    public Pedido insert(Pedido obj){
        obj.setId(null);
        obj.setInstante(new Date());
        obj.setCliente(clienteService.findById(obj.getCliente().getId()));
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto){
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }
        obj = pedidoRepository.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for (ItemPedido ip: obj.getItens()){
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.findById(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        emailService.sendOrderConfirmationHtmlEmail(obj);
        return obj;
    }

    public Page<Pedido> findByPage(Integer page, Integer linePerPage, String orderBy, String direction){
        UserSS user = UserService.authenticated();
        if(user == null) {
            throw new AuthorizationException("Acesso negado");
        }
        PageRequest pageRequest = PageRequest.of(page, linePerPage, Sort.Direction.valueOf(direction), orderBy);
        Cliente cliente = clienteService.findById(user.getId());
        return pedidoRepository.findByCliente(cliente, pageRequest);
    }
}
