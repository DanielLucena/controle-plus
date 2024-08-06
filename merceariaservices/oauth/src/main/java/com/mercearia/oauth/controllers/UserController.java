package com.mercearia.oauth.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mercearia.oauth.dto.CredenciaisDTO;
import com.mercearia.oauth.dto.TokenDTO;
import com.mercearia.oauth.model.User;
import com.mercearia.oauth.services.UserService;

@RestController
@RequestMapping(value = "/oauth/users")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping(value = "/search")
    public ResponseEntity<User> findByLogin(@RequestParam String login) {
        try {
            User user = service.findByLogin(login);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "/role")
    public String currentUserRole(Principal principal) {
        // Cast the principal object to the appropriate type
        UserDetails userDetails = (UserDetails) principal;
        return userDetails.getAuthorities().stream().findFirst().get().getAuthority();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User salvar(@RequestBody @Valid User usuario) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return service.save(usuario);

    }
}
