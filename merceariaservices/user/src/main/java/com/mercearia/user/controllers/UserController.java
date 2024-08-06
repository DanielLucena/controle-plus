package com.mercearia.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercearia.user.domain.User;
import com.mercearia.user.services.UserSevice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/users")
// @Tag(name = "Usuário")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserSevice service;

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id) {
        return service.getUserById(id);
    }

    @GetMapping(value = "/search")
    public User getByLogin(@RequestParam String login) {
        return service.getUserByLogin(login);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User salvar(@RequestBody User usuario) {
        return service.salvar(usuario);

    }

}
