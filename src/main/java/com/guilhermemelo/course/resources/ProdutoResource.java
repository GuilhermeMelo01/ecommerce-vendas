package com.guilhermemelo.course.resources;

import com.guilhermemelo.course.domain.Categoria;
import com.guilhermemelo.course.domain.Produto;
import com.guilhermemelo.course.dto.CategoriaDto;
import com.guilhermemelo.course.dto.ProdutoDto;
import com.guilhermemelo.course.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> find(@PathVariable Integer id){
        Produto findByid = produtoService.findById(id);
        return ResponseEntity.ok().body(findByid);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDto>> findPage(
            @RequestParam(value = "nome", defaultValue = "") Integer nome,
            @RequestParam(value = "categorias", defaultValue = "") Integer categorias,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linePerPage", defaultValue = "24")Integer linePerPage,
            @RequestParam(value = "orderBy", defaultValue = "name")String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC")String direction) {

        Page<Produto> listPage = produtoService.search(???, ???, page, linePerPage, orderBy, direction);

        Page<CategoriaDto> pageDto = listPage.map(CategoriaDto::new);

        return ResponseEntity.ok().body(pageDto);
    }

}
