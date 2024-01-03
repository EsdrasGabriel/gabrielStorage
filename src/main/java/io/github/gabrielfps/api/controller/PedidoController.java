package io.github.gabrielfps.api.controller;

import io.github.gabrielfps.api.dto.PedidoDTO;
import io.github.gabrielfps.domain.entity.Pedido;
import io.github.gabrielfps.service.PedidoService;
import io.github.gabrielfps.service.impl.PedidoServiceImpl;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {
    private PedidoServiceImpl service;
    public PedidoController(PedidoServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO pedidoDto){
        Pedido pedido = service.salvar(pedidoDto);
        return pedido.getId();
    }
}
