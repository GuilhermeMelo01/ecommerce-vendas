package com.guilhermemelo.course.resources;

import com.guilhermemelo.course.domain.Categoria;
import com.guilhermemelo.course.domain.Cliente;
import com.guilhermemelo.course.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable Integer id){

        Cliente clienteId = clienteService.buscarId(id);

        return ResponseEntity.ok().body(clienteId);

    }


}
