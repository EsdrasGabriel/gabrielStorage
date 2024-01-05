package io.github.gabrielfps.domain.repository;

import io.github.gabrielfps.domain.entity.Cliente;
import io.github.gabrielfps.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);

    @Query("SELECT p FROM Pedido p LEFT JOIN FETCH p.items where p.id = :id")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
