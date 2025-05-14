package com.biblioteca.gestion.services;

import com.biblioteca.gestion.models.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario buscarPorEmail(String email);
    Usuario buscarPorId(Long id);
    List<Usuario> obtenerTodos();
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);
    Usuario actualizar(Long id, Usuario usuario);
}

