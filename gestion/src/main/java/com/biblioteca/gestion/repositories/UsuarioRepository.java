package com.biblioteca.gestion.repositories;

import java.util.List;
import java.util.Optional;
import com.biblioteca.gestion.models.Usuario;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}

