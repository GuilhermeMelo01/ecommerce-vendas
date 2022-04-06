package com.guilhermemelo.course.repositories;

import com.guilhermemelo.course.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
