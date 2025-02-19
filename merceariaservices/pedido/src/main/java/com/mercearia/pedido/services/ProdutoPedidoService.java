package com.mercearia.pedido.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.mercearia.pedido.entities.Pedido;
import com.mercearia.pedido.entities.ProdutoPedido;
import com.mercearia.pedido.feignclients.ProdutoFeignClient;
import com.mercearia.pedido.repository.ProdutoPedidoRepository;
import com.mercearia.pedido.dto.InformacaoItemDto;
import com.mercearia.pedido.dto.ItemDto;

@Component
public class ProdutoPedidoService {
    @Autowired
    ProdutoPedidoRepository produtoPedidoRepository;

    @Autowired
    ProdutoFeignClient produtoFeignCliente;

    // @Autowired
    // PedidoService pedidoService;

    public void persistListaProdutosPedido(List<ItemDto> itens, Pedido pedido) {
        validaListaProdutos(itens);

        for (ItemDto item : itens) {
            System.out.println("\nitem do pedido: " + item + "\n");
            if (item.getQuantidade() > 0) {
                ProdutoPedido produtoPedido = new ProdutoPedido();
                produtoPedido.setPedido(pedido);
                produtoPedido.setProduto(item.getProduto());
                produtoPedido.setQuantidade(item.getQuantidade());
                produtoPedidoRepository.save(produtoPedido);
                // produtoFeignCliente.darBaixaAoEstoque(produtoPedido.getProduto(),
                // item.getQuantidade());
                System.out.println(produtoPedido.getId());
            }
        }

    }

    public void validaListaProdutos(List<ItemDto> itens) {

        // for (ItemDto item : itens) {
        // produtoFeignCliente.validaEstoqueProdutoSuficiente(item.getProduto(),
        // item.getQuantidade());

        // }

    }

    public List<ProdutoPedido> converteDtoToListProdutoPedido(List<ItemDto> itens, Pedido pedido) {
        List<ProdutoPedido> produtos = new ArrayList<>();
        for (ItemDto item : itens) {
            ProdutoPedido produtoPedido = new ProdutoPedido();
            produtoPedido.setPedido(pedido);
            produtoPedido.setProduto(item.getProduto());
            produtoPedido.setQuantidade(item.getQuantidade());
            produtos.add(produtoPedido);
        }
        return produtos;
    }

    public double getValorTotal(List<ProdutoPedido> produtoPedidos) {
        double valorTotal = 0;
        for (ProdutoPedido produtoPedido : produtoPedidos) {
            if (produtoPedido.getQuantidade() > 0) {
                valorTotal += produtoPedido.getQuantidade()
                        * produtoFeignCliente.getProdutoById(produtoPedido.getProduto()).getBody().getPreco();
            }

        }
        return valorTotal;
    }

    public List<InformacaoItemDto> converterListaProdutoPedidoParaDto(List<ProdutoPedido> itens) {
        if (CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }

        return itens.stream().map(
                item -> InformacaoItemDto
                        .builder()
                        .codigoProduto(item.getProduto())
                        .nome(produtoFeignCliente.getProdutoById(item.getProduto()).getBody().getNome())
                        .quantidade(item.getQuantidade())
                        .preco(item.getQuantidade()
                                * produtoFeignCliente.getProdutoById(item.getProduto()).getBody().getPreco())
                        .build())
                .collect(Collectors.toList());
    }

    public void decrementaEstoqueProdutos(List<ProdutoPedido> itens) {
        // for (ProdutoPedido item : itens) {
        // produtoFeignCliente.darBaixaAoEstoque(item.getProduto(),
        // item.getQuantidade());
        // }
    }
}
