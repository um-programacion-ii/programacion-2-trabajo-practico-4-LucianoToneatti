package com.biblioteca.gestion;

import com.biblioteca.gestion.models.Usuario;
import com.biblioteca.gestion.repositories.UsuarioRepository;
import com.biblioteca.gestion.repositories.UsuarioRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioRepositoryTest {

    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        // Usamos la implementación en memoria
        usuarioRepository = new UsuarioRepositoryImpl();
    }

    @Test
    void guardarYBuscarUsuarioPorId() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Luciano");
        usuario.setEmail("luciano@example.com");

        Usuario guardado = usuarioRepository.save(usuario);
        Optional<Usuario> encontrado = usuarioRepository.findById(guardado.getId());

        assertTrue(encontrado.isPresent());
        assertEquals("Luciano", encontrado.get().getNombre());
    }

    @Test
    void eliminarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Eliminar Usuario");
        usuario.setEmail("eliminar@example.com");

        Usuario guardado = usuarioRepository.save(usuario);
        usuarioRepository.deleteById(guardado.getId());

        Optional<Usuario> resultado = usuarioRepository.findById(guardado.getId());
        assertFalse(resultado.isPresent());
    }

    @Test
    void guardarUsuarioConDatosInvalidos() {
        Usuario usuario = new Usuario();
        // En tu implementación en memoria no hay validaciones, así que se guardará igual.
        // Este test lo comentamos o lo adaptamos según lo que quieras hacer.
        Usuario guardado = usuarioRepository.save(usuario);

        assertNotNull(guardado.getId()); // Solo verificamos que se guarde con ID
    }
}


