package io.github.gabrielfps.api.controller;

import io.github.gabrielfps.domain.entity.Pedido;
import io.github.gabrielfps.domain.repository.Pedidos;
import io.github.gabrielfps.service.PedidoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {
    private PedidoService service;
    public PedidoController(PedidoService service) {
        this.service = service;
    }
}
