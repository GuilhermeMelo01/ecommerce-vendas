package com.guilhermemelo.course.services;

import com.guilhermemelo.course.domain.Cliente;
import com.guilhermemelo.course.dto.ClienteDTO;
import com.guilhermemelo.course.repositories.ClienteRepository;
import com.guilhermemelo.course.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findById(Integer id) {
        Optional<Cliente> clienteId = clienteRepository.findById(id);
        return clienteId.orElseThrow(() -> new ObjectNotFoundException("Object is not valid!"));
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
        } catch (DataIntegrityViolationException dIVE){
            throw new DataIntegrityViolationException("Não é possivel apagar, há entidades associadas");
        }
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findByPage(Integer page, Integer linePerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linePerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);

    }

    public Cliente fromDto(ClienteDTO clienteDTO){
        return new Cliente(clienteDTO.getId(), clienteDTO.getName(), clienteDTO.getEmail(), null, null);
    }

    private void updateData(Cliente newCliente, Cliente cliente){
        newCliente.setName(cliente.getName());
        newCliente.setEmail(cliente.getEmail());
    }
}
