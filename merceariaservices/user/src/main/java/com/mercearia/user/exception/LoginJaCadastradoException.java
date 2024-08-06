package com.mercearia.user.exception;

public class LoginJaCadastradoException extends RuntimeException {
    public LoginJaCadastradoException(String login) {
        super("Já existe um usuario cadastrado com o login: " + login
                + " , Por favor use outro login.");
    }
}
