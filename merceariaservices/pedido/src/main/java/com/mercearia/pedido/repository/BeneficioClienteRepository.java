package com.mercearia.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercearia.pedido.entities.BeneficioCliente;

public interface BeneficioClienteRepository extends JpaRepository<BeneficioCliente, Integer> {
    // @Query(value = "select b from beneficio_produto where b.cpf like %:cpf%")
    public BeneficioCliente findByCpf(String cpf);
}
