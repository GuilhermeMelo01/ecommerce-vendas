package com.guilhermemelo.course.services;

import com.guilhermemelo.course.domain.Categoria;
import com.guilhermemelo.course.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired //Essa dependencia vai ser automaticamente
              //instanciada pelo o Spring(Injeção de Dependencia ou Inversao de Controle)
    private CategoriaRepository repository;

    public Categoria buscar(Integer id){
        return repository.findById(id).orElse(null);
    }

}
