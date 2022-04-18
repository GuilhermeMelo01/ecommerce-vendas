package com.guilhermemelo.course.resources;

import com.guilhermemelo.course.domain.Categoria;
import com.guilhermemelo.course.dto.CategoriaDTO;
import com.guilhermemelo.course.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> findById(@PathVariable Integer id) {

        Categoria findByid = service.findById(id);

        return ResponseEntity.ok().body(findByid);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO categoriaDto) {
        Categoria categoria = service.fromDto(categoriaDto);
        categoria = service.insert(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{/id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO categoriaDto,  @PathVariable Integer id) {
        Categoria categoria = service.fromDto(categoriaDto);
        categoria.setId(id);
        categoria = service.update(categoria);
        return ResponseEntity.noContent().build();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll() {

        List<Categoria> list = service.findAll();

        List<CategoriaDTO> listDto = list.stream().map(CategoriaDTO::new).toList();

        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value="/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linePerPage", defaultValue = "24")Integer linePerPage,
            @RequestParam(value = "orderBy", defaultValue = "name")String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC")String direction) {

        Page<Categoria> listPage = service.findByPage(page, linePerPage, orderBy, direction);

        Page<CategoriaDTO> pageDto = listPage.map(CategoriaDTO::new);

        return ResponseEntity.ok().body(pageDto);
    }


}
