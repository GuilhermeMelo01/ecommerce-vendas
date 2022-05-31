package com.guilhermemelo.course.resources;

import com.guilhermemelo.course.domain.Cidade;
import com.guilhermemelo.course.domain.Estado;
import com.guilhermemelo.course.dto.CidadeDto;
import com.guilhermemelo.course.dto.EstadoDto;
import com.guilhermemelo.course.services.CidadeService;
import com.guilhermemelo.course.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estados")
public class EstadoResource {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CidadeService cidadeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EstadoDto>> findAll(){
        List<Estado> list = estadoService.findAll();
        List<EstadoDto> listDto = list.stream().map(EstadoDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/{estadoId}/cidades", method = RequestMethod.GET)
    public ResponseEntity<List<CidadeDto>> findCidades(@PathVariable Integer estadoId){
        List<Cidade> list = cidadeService.findByEstado(estadoId);
        List<CidadeDto> listDto = list.stream().map(CidadeDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
}
