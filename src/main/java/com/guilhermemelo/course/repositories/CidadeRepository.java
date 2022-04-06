package com.guilhermemelo.course.repositories;

import com.guilhermemelo.course.domain.Cidade;
import com.guilhermemelo.course.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
}
