package com.biblioteca.gestion;

import com.biblioteca.gestion.exceptions.UsuarioNoEncontradoException;
import com.biblioteca.gestion.models.Usuario;
import com.biblioteca.gestion.repositories.UsuarioRepository;
import com.biblioteca.gestion.services.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Luciano");
        usuario.setEmail("luciano@example.com");
    }

    @Test
    void cuandoBuscarPorIdExiste_entoncesRetornaUsuario() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(usuarioRepository).findById(1L);
    }

    @Test
    void cuandoBuscarPorIdNoExiste_entoncesLanzaExcepcion() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UsuarioNoEncontradoException.class, () -> usuarioService.buscarPorId(1L));
        verify(usuarioRepository).findById(1L);
    }

    @Test
    void cuandoObtenerTodos_entoncesRetornaListaUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<Usuario> resultado = usuarioService.obtenerTodos();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(usuarioRepository).findAll();
    }

    @Test
    void cuandoGuardarUsuario_entoncesRetornaUsuarioGuardado() {
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.guardar(usuario);

        assertNotNull(resultado);
        assertEquals("Luciano", resultado.getNombre());
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void cuandoEliminarUsuarioExiste_entoncesElimina() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        doNothing().when(usuarioRepository).deleteById(1L);

        usuarioService.eliminar(1L);

        verify(usuarioRepository).deleteById(1L);
    }

    @Test
    void cuandoEliminarUsuarioNoExiste_entoncesLanzaExcepcion() {
        when(usuarioRepository.existsById(1L)).thenReturn(false);

        assertThrows(UsuarioNoEncontradoException.class, () -> usuarioService.eliminar(1L));
        verify(usuarioRepository, never()).deleteById(anyLong());
    }

    @Test
    void cuandoActualizarUsuarioExiste_entoncesActualiza() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.actualizar(1L, usuario);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void cuandoActualizarUsuarioNoExiste_entoncesLanzaExcepcion() {
        when(usuarioRepository.existsById(1L)).thenReturn(false);

        assertThrows(UsuarioNoEncontradoException.class, () -> usuarioService.actualizar(1L, usuario));
        verify(usuarioRepository, never()).save(any());
    }
}

