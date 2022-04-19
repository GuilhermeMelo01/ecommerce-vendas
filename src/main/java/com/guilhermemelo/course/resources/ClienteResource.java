package com.guilhermemelo.course.resources;

import com.guilhermemelo.course.domain.Cliente;
import com.guilhermemelo.course.dto.ClienteDTO;
import com.guilhermemelo.course.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> findById(@PathVariable Integer id){
        Cliente clienteId = service.findById(id);
        return ResponseEntity.ok().body(clienteId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDto,  @PathVariable Integer id) {
        Cliente cliente = service.fromDto(clienteDto);
        cliente.setId(id);
        cliente = service.update(cliente);
        return ResponseEntity.noContent().build();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {

        List<Cliente> list = service.findAll();

        List<ClienteDTO> listDto = list.stream().map(ClienteDTO::new).toList();

        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value="/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linePerPage", defaultValue = "24")Integer linePerPage,
            @RequestParam(value = "orderBy", defaultValue = "name")String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC")String direction) {

        Page<Cliente> listPage = service.findByPage(page, linePerPage, orderBy, direction);

        Page<ClienteDTO> pageDto = listPage.map(ClienteDTO::new);

        return ResponseEntity.ok().body(pageDto);
    }

}
