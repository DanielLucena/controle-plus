package com.mercearia.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercearia.user.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    // User findByLogin(String login);
    Optional<User> findByLogin(String login);
}