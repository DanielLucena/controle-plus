package com.mercearia.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercearia.user.repository.UserRepository;

import jakarta.ws.rs.NotFoundException;

import com.mercearia.user.domain.User;

@Service
public class UserSevice {

    @Autowired
    UserRepository repository;

    public User getUserById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException());
    }

    public User getUserByLogin(String login) {
        return repository.findByLogin(login);
    }
}
