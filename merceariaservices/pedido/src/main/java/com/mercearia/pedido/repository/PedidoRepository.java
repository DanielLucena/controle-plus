package com.mercearia.pedido.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mercearia.pedido.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Query(value = "select p from Pedido p where p.cpfCliente like %:cpf% ")
    List<Pedido> getPedidosByCpf(@Param("cpf") String cpf);
}
