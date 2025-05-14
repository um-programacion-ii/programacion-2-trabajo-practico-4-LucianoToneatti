package com.biblioteca.gestion.services;

import com.biblioteca.gestion.exceptions.UsuarioNoEncontradoException;
import com.biblioteca.gestion.models.Usuario;
import com.biblioteca.gestion.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNoEncontradoException("email", email));
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException(id));
    }

    @Override
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNoEncontradoException(id);
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario actualizar(Long id, Usuario usuario) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNoEncontradoException(id);
        }
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }
}

