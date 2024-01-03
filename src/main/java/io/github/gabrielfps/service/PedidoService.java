package io.github.gabrielfps.service;

import io.github.gabrielfps.api.dto.PedidoDTO;
import io.github.gabrielfps.domain.entity.Pedido;

public interface PedidoService {
    Pedido salvar(PedidoDTO pedidoDto);
}
