package com.guilhermemelo.course.services;

import com.guilhermemelo.course.domain.Categoria;
import com.guilhermemelo.course.repositories.CategoriaRepository;
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
public class CategoriaService {

    @Autowired //Essa dependencia vai ser automaticamente
    //instanciada pelo o Spring(Injeção de Dependencia ou Inversao de Controle)
    private CategoriaRepository repository;

    public Categoria findById(Integer id) {
        Optional<Categoria> categoriaId = repository.findById(id);
        return categoriaId.orElseThrow(() -> new ObjectNotFoundException("Object não encontrado! Id: " + id +
                ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        return repository.save(categoria);
    }

    public Categoria update(Categoria categoria) {
        findById(categoria.getId());
        return repository.save(categoria);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException dIVE){
            throw new DataIntegrityViolationException("Não é possivel apagar a categoria com produtos associados");
        }
    }

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Page<Categoria> findByPage(Integer page, Integer linePerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linePerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);

    }


}
