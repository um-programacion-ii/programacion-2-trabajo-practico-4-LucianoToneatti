package com.biblioteca.gestion.exceptions;


public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(Long id) {
        super("Usuario con ID " + id + " no encontrado");
    }

    public UsuarioNoEncontradoException(String campo, String valor) {
        super("Usuario con " + campo + " \"" + valor + "\" no encontrado");
    }
}

