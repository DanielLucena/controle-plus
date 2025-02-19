package com.mercearia.produto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mercearia.produto.entities.Produto;
import com.mercearia.produto.dto.ProdutoCreationDTO;
import com.mercearia.produto.services.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/produto")
@Tag(name = "Produto")
@CrossOrigin(origins = "*")
public class ProdutoController {
    @Autowired
    ProdutoService service;

    @Operation(summary = "Cria um produto", method = "POST")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody ProdutoCreationDTO dto) {
        System.out.println("controller");
        Produto produto = service.criarProduto(dto);
        return produto.getId();
    }

    @Operation(summary = "Edita um produto", method = "PUT")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody ProdutoCreationDTO dto) {
        Produto produto = service.converteDtoParaProduto(dto);

        service.atualizarProduto(produto, id);
    }

    @Operation(summary = "Recupera um produto pelo ID", method = "GET")
    @GetMapping("{id}")
    public Produto getById(@PathVariable Integer id) {
        return service.getProdutoById(id);
    }

    @Operation(summary = "Exclui um produto", method = "DELETE")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.deleteProdutoById(id);
    }

    @Operation(summary = "Mostra a listagem de todos os produtos", method = "POST")
    @GetMapping
    public List<Produto> find(ProdutoCreationDTO dto) {
        return service.listaProdutosPorFiltro(dto);
    }

}