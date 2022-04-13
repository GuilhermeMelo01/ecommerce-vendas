package com.guilhermemelo.course.services;

import com.guilhermemelo.course.domain.Pedido;
import com.guilhermemelo.course.repositories.PedidoRepository;
import com.guilhermemelo.course.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public Pedido findById(Integer id) {
        Optional<Pedido> pedidoId = repository.findById(id);
        return pedidoId.orElseThrow(() -> new ObjectNotFoundException("Object não encontrado! Id: " + id +
                ", Tipo: " + Pedido.class.getName()));



    }

}
