package com.mercearia.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercearia.pedido.entities.ProdutoPedido;

public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedido, Integer> {

}
