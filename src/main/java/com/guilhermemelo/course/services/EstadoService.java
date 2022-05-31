package com.guilhermemelo.course.services;

import com.guilhermemelo.course.domain.Estado;
import com.guilhermemelo.course.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> findAll() {
        return estadoRepository.findAllByOrderByNome();
    }

}
