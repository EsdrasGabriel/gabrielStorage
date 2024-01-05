package io.github.gabrielfps.service;

import io.github.gabrielfps.api.dto.PedidoDTO;
import io.github.gabrielfps.domain.entity.Pedido;
import io.github.gabrielfps.domain.enums.StatusPedido;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO pedidoDto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizarStatus(Integer id, StatusPedido statusPedido);
}
