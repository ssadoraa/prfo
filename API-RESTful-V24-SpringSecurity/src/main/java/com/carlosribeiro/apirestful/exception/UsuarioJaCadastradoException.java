package com.carlosribeiro.apirestful.exception;

public class UsuarioJaCadastradoException extends RuntimeException {
    public UsuarioJaCadastradoException(String msg) {
        super(msg);
    }
}
