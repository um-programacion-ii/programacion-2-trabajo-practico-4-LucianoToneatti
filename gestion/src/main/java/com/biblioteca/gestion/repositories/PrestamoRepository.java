package com.biblioteca.gestion.repositories;

import java.util.List;
import java.util.Optional;
import com.biblioteca.gestion.models.Prestamo;

public interface PrestamoRepository {
    Prestamo save(Prestamo prestamo);
    Optional<Prestamo> findById(Long id);
    List<Prestamo> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
