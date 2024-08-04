package com.mercearia.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercearia.user.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);
}