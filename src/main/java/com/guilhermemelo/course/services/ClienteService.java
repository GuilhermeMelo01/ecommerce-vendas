package com.guilhermemelo.course.services;

import com.guilhermemelo.course.domain.Cliente;
import com.guilhermemelo.course.repositories.ClienteRepository;
import com.guilhermemelo.course.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscarId(Integer id) {
        Optional<Cliente> clienteId = clienteRepository.findById(id);
        return clienteId.orElseThrow(() -> new ObjectNotFoundException("Object is not valid!"));
    }
}
