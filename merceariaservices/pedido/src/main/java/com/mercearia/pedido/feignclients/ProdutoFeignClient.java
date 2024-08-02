package com.mercearia.pedido.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mercearia.pedido.model.Produto;

@Component
@FeignClient(name = "produto", path = "/api/produto")
public interface ProdutoFeignClient {

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Integer id);
}
