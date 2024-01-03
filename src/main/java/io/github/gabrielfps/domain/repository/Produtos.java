package io.github.gabrielfps.domain.repository;

import io.github.gabrielfps.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {

}
