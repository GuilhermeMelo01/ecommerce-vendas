package com.guilhermemelo.course.services;

import com.guilhermemelo.course.domain.Cidade;
import com.guilhermemelo.course.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> findByEstado(Integer estadoId){
        return cidadeRepository.findCidades(estadoId);
    }


}
