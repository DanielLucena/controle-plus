package com.mercearia.oauth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercearia.oauth.model.User;
import com.mercearia.oauth.services.UserService;

@RestController
@RequestMapping(value = "api/oauth/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping(value = "/search")
    public ResponseEntity<User> findByLogin(@RequestParam String login) {
        try {
            User user = service.findByLogin(login);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
