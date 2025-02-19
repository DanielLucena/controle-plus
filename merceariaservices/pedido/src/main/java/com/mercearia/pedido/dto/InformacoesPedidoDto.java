package com.mercearia.pedido.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InformacoesPedidoDto {
    private Integer id;
    private String cpfCliente;
    private List<InformacaoItemDto> itens;
    private BigDecimal desconto;
    private BigDecimal valorTotal;
    private String status;
}
