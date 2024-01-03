package io.github.gabrielfps.service.impl;

import io.github.gabrielfps.api.dto.ItemPedidoDTO;
import io.github.gabrielfps.api.dto.PedidoDTO;
import io.github.gabrielfps.domain.entity.Cliente;
import io.github.gabrielfps.domain.entity.ItemPedido;
import io.github.gabrielfps.domain.entity.Pedido;
import io.github.gabrielfps.domain.entity.Produto;
import io.github.gabrielfps.domain.repository.Clientes;
import io.github.gabrielfps.domain.repository.ItemsPedidos;
import io.github.gabrielfps.domain.repository.Pedidos;
import io.github.gabrielfps.domain.repository.Produtos;
import io.github.gabrielfps.exception.RegraNegocioException;
import io.github.gabrielfps.service.PedidoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {
    private final Pedidos pedidosRepository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItemsPedidos itemsPedidosRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO pedidoDto) {
        Integer idCliente = pedidoDto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(pedidoDto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemsPedidos = converterItems(pedido, pedidoDto.getItems());
        pedidosRepository.save(pedido);
        itemsPedidosRepository.saveAll(itemsPedidos);
        pedido.setItems(itemsPedidos);

        return pedido;
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
        if (items.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }
        return items.stream().map(dto -> {
            Integer idProduto = dto.getProduto();
            Produto produto = produtosRepository
                    .findById(idProduto)
                    .orElseThrow(() -> new RegraNegocioException("Código de produto inválido: "+idProduto));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(dto.getQuantidade());
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            return itemPedido;
        }).collect(Collectors.toList());
    }
}
