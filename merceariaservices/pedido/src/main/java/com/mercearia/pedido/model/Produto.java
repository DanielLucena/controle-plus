package com.mercearia.pedido.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Produto implements Serializable {

    private Integer id;

    private String nome;

    private double preco;

    private int quantidadeEstoque;

}
