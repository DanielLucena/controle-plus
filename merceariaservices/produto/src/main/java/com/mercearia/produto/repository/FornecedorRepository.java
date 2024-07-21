package com.mercearia.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercearia.produto.entities.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {

}
