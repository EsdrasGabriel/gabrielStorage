package io.github.gabrielfps.domain.repository;

import io.github.gabrielfps.domain.entity.Cliente;
import io.github.gabrielfps.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface Pedidos extends JpaRepository<Pedido, Integer> {

}
