package com.biblioteca.gestion.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(LibroNoEncontradoException.class)
    public ResponseEntity<String> handleLibroNotFound(LibroNoEncontradoException ex) {
        // Retorna un 404 Not Found
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


}

