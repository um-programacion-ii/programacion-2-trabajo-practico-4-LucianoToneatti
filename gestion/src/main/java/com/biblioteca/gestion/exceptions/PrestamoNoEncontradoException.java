package com.biblioteca.gestion.exceptions;

public class PrestamoNoEncontradoException extends RuntimeException {

    public PrestamoNoEncontradoException(Long id) {
        super("Préstamo con ID " + id + " no encontrado");
    }
}

