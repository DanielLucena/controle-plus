package com.mercearia.oauth.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mercearia.oauth.model.User;

@Component
@FeignClient(name = "user", path ="/users")
public interface UserFeignClient {

    @GetMapping(value = "/search")
    ResponseEntity<User> findByLogin(@RequestParam String login);

    @PostMapping
    ResponseEntity<User> salvar(@RequestBody User usuario);
}
