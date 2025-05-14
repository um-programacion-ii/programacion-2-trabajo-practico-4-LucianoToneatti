package com.biblioteca.gestion.repositories;

import java.util.List;
import java.util.Optional;
import com.biblioteca.gestion.models.Libro;

public interface LibroRepository {
    Libro save(Libro libro);
    Optional<Libro> findById(Long id);
    Optional<Libro> findByIsbn(String isbn);
    List<Libro> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}

