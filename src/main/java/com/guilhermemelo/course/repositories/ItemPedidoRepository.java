package com.guilhermemelo.course.repositories;

import com.guilhermemelo.course.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
