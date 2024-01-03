package io.github.gabrielfps.service.impl;

import io.github.gabrielfps.domain.repository.Pedidos;
import io.github.gabrielfps.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {
    private Pedidos repository;
    public PedidoServiceImpl(Pedidos repository) {
        this.repository = repository;
    }
}
