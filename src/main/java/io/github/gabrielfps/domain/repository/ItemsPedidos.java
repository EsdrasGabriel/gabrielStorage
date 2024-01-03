package io.github.gabrielfps.domain.repository;

import io.github.gabrielfps.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedidos extends JpaRepository<ItemPedido, Integer> {

}
