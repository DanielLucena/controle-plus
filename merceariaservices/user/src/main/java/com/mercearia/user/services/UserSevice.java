package com.mercearia.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercearia.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import com.mercearia.user.domain.User;
import com.mercearia.user.exception.LoginJaCadastradoException;

@Service
public class UserSevice {

    @Autowired
    UserRepository repository;

    public User getUserById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException());
    }

    public User getUserByLogin(String login) {
        return repository.findByLogin(login).orElse(null);
    }

    @Transactional
    public User salvar(User usuario) {
        User usuarioExistente = repository.findByLogin(usuario.getLogin()).orElse(null);
        if (usuarioExistente != null)
            throw new LoginJaCadastradoException(usuarioExistente.getLogin());
        return repository.save(usuario);
    }
}
