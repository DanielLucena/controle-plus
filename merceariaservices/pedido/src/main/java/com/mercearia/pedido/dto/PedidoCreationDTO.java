package com.mercearia.pedido.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PedidoCreationDTO {
    private List<ItemDto> itens;
    private String cpfCliente;
    private boolean usandoCashback;
}
