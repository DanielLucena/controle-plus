package com.mercearia.produto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.mercearia.produto.exception.RegistroNaoEncontradoException;
import com.mercearia.produto.exception.RegraNegocioException;
import com.mercearia.produto.entities.Fornecedor;
import com.mercearia.produto.entities.Produto;
import com.mercearia.produto.repository.FornecedorRepository;
import com.mercearia.produto.repository.ProdutoRepository;

@Component
public class FornecedorService {

    @Autowired
    FornecedorRepository fornecedorRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    public List<Fornecedor> getListaFornecedores() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor criarFornecedor(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor getFornecedorById(Integer id) {
        if (id == null) {
            throw new RegraNegocioException("Id do fornecedor não pode ser nulo.");
        }
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(Produto.class, id));
    }

    public void atualizarFornecedor(Fornecedor fornecedor, Integer id) {
        if (id == null) {
            throw new RegraNegocioException("Id do fornecedor não pode ser nulo.");
        }
        fornecedorRepository.findById(id)
                .map(p -> {
                    fornecedor.setId(p.getId());
                    fornecedorRepository.save(fornecedor);
                    return fornecedor;
                }).orElseThrow(() -> new RegistroNaoEncontradoException(Fornecedor.class, id));
    }

    public void deletarFornecedor(Integer id) {
        if (id == null) {
            throw new RegraNegocioException("Id do fornecedor não pode ser nulo.");
        }
        fornecedorRepository.findById(id)
                .map(p -> {
                    fornecedorRepository.delete(p);
                    return Void.TYPE;
                }).orElseThrow(() -> new RegistroNaoEncontradoException(Fornecedor.class, id));
    }

    public List<Produto> getProdutosPorFornecedor(Fornecedor fornecedor) {
        return produtoRepository.findByFornecedor(fornecedor);
    }

    public List<Fornecedor> listaFornecedorPorFiltro(Fornecedor filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Fornecedor> example = Example.of(filtro, matcher);
        return fornecedorRepository.findAll(example, Sort.by(Sort.Direction.ASC, "id"));
    }

}
