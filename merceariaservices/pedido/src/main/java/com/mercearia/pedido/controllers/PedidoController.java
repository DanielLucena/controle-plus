package com.mercearia.pedido.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercearia.pedido.entities.Pedido;

import com.mercearia.pedido.dto.PedidoCreationDTO;
import com.mercearia.pedido.dto.InformacoesPedidoDto;

import com.mercearia.pedido.services.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/pedido")
@Tag(name = "Pedido")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    PedidoService service;

    @Operation(summary = "Salva um novo pedido", method = "POST")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido save(@RequestBody PedidoCreationDTO dto) {
        System.out.println("PedidoController.save() " + dto);
        return service.processarPedido(dto);
    }

    @Operation(summary = "Busca pedidos com filtro", method = "GET")
    @GetMapping
    public List<Pedido> find(PedidoCreationDTO dto) {
        return service.listaPedidosPorFiltro(dto);
    }

    @Operation(summary = "Busca pedidos por id", method = "GET")
    @GetMapping(value = "{id}")
    public InformacoesPedidoDto getById(@PathVariable Integer id) {
        return service.converterPedidoParaDto(service.getPedidoById(id));
    }

}
