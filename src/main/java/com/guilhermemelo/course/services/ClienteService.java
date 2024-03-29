package com.guilhermemelo.course.services;

import com.guilhermemelo.course.domain.Cidade;
import com.guilhermemelo.course.domain.Cliente;
import com.guilhermemelo.course.domain.Endereco;
import com.guilhermemelo.course.dto.ClienteDto;
import com.guilhermemelo.course.dto.ClienteNewDto;
import com.guilhermemelo.course.enums.Perfil;
import com.guilhermemelo.course.enums.TipoCliente;
import com.guilhermemelo.course.repositories.ClienteRepository;
import com.guilhermemelo.course.repositories.EnderecoRepository;
import com.guilhermemelo.course.security.UserSS;
import com.guilhermemelo.course.services.exception.AuthorizationException;
import com.guilhermemelo.course.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ImageService imageService;

    @Value("${img.prefix.client.profile}")
    private String prefix;

    @Value("${img.profile.size}")
    private Integer size;

    public Cliente findById(Integer id) {
        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso negado");
        }

        Optional<Cliente> clienteId = clienteRepository.findById(id);
        return clienteId.orElseThrow(() -> new ObjectNotFoundException("Object is not valid!"));
    }

    @Transactional
    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        cliente = clienteRepository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;
    }

    public Cliente update(Cliente cliente) {
        Cliente newCliente = findById(cliente.getId());
        updateData(newCliente, cliente);
        return clienteRepository.save(newCliente);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException dIVE) {
            throw new DataIntegrityViolationException("Não é possivel apagar, há entidades associadas");
        }
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findByPage(Integer page, Integer linePerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linePerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);

    }

    public Cliente findByEmail(String email) {
        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Perfil.ADMIN) && !user.equals(user.getUsername())) {
            throw new AuthorizationException("Acesso negado");
        }

        Cliente obj = clienteRepository.findByEmail(email);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + user.getId() + ", " +
                    "Tipo: " + Cliente.class.getName());
        }
        return obj;
    }

    public Cliente fromDto(ClienteDto clienteDTO) {
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null, null);
    }

    public Cliente fromDto(ClienteNewDto objDto) {
        Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj()
                , TipoCliente.toEnum(objDto.getTipoCliente()), passwordEncoder.encode(objDto.getSenha()));
        Cidade cidade = new Cidade(objDto.getCidadeId(), null, null);
        Endereco endereco = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento()
                , objDto.getBairro(), objDto.getCep(), cliente, cidade);
        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(objDto.getTelefone1());
        if (objDto.getTelefone2() != null) {
            cliente.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3() != null) {
            cliente.getTelefones().add(objDto.getTelefone3());
        }

        return cliente;
    }

    private void updateData(Cliente newCliente, Cliente cliente) {
        newCliente.setNome(cliente.getNome());
        newCliente.setEmail(cliente.getEmail());
    }

    public URI uploadProfilePicture(MultipartFile multipartFile) {
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso negado");
        }

        BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
        jpgImage = imageService.cropSquare(jpgImage);
        jpgImage = imageService.resize(jpgImage, size);

        String fileName = prefix + user.getId() + ".jpg";

        return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
    }
}
