package com.mercearia.pedido.exception;

import com.mercearia.pedido.model.Produto;

public class EstoqueInsuficienteException extends RuntimeException {
    public EstoqueInsuficienteException(Produto produto, int quantidade) {
        super("Quantidade em estoque insuficiente do produto: " +
                produto.getNome() +
                ". Você colocou no pedido " + quantidade +
                " itens desse produto, porem so existem " + produto.getQuantidadeEstoque() +
                " itens no estoque!");
    }
}
