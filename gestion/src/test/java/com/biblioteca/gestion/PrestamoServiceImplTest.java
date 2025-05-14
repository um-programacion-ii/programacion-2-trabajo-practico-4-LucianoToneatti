package com.biblioteca.gestion;

import com.biblioteca.gestion.estados.EstadodeLibro;
import com.biblioteca.gestion.exceptions.LibroNoEncontradoException;
import com.biblioteca.gestion.exceptions.PrestamoNoEncontradoException;
import com.biblioteca.gestion.exceptions.UsuarioNoEncontradoException;
import com.biblioteca.gestion.models.Libro;
import com.biblioteca.gestion.models.Prestamo;
import com.biblioteca.gestion.models.Usuario;
import com.biblioteca.gestion.repositories.LibroRepository;
import com.biblioteca.gestion.repositories.PrestamoRepository;
import com.biblioteca.gestion.repositories.UsuarioRepository;
import com.biblioteca.gestion.services.PrestamoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrestamoServiceImplTest {

    @Mock
    private PrestamoRepository prestamoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private PrestamoServiceImpl prestamoService;

    private Usuario usuario;
    private Libro libro;
    private Prestamo prestamo;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Luciano");

        libro = new Libro();
        libro.setId(1L);
        libro.setTitulo("El Principito");
        libro.setEstado(EstadodeLibro.DISPONIBLE);

        prestamo = new Prestamo();
        prestamo.setId(1L);
        prestamo.setUsuario(usuario);
        prestamo.setLibro(libro);
        prestamo.setFechaPrestamo(LocalDate.now());
    }

    @Test
    void cuandoCrearPrestamo_entoncesRetornaPrestamoGuardado() {
        // Configurar los mocks para que devuelvan los valores esperados
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(libroRepository.findById(1L)).thenReturn(Optional.of(libro));
        when(prestamoRepository.save(any(Prestamo.class))).thenReturn(prestamo);

        // Llamada al método que estamos probando
        Prestamo resultado = prestamoService.crearPrestamo(1L, 1L);

        // Verificaciones
        assertNotNull(resultado);
        assertEquals(usuario, resultado.getUsuario());
        assertEquals(libro, resultado.getLibro());

        // Verificar que el método save del repositorio de préstamo haya sido llamado
        verify(prestamoRepository).save(any(Prestamo.class));
    }


    @Test
    void cuandoCrearPrestamo_usuarioNoExiste_entoncesLanzaExcepcion() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UsuarioNoEncontradoException.class, () -> prestamoService.crearPrestamo(1L, 1L));
    }

    @Test
    void cuandoCrearPrestamo_libroNoExiste_entoncesLanzaExcepcion() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(libroRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(LibroNoEncontradoException.class, () -> prestamoService.crearPrestamo(1L, 1L));
    }

    @Test
    void cuandoCrearPrestamo_libroNoDisponible_entoncesLanzaExcepcion() {
        libro.setEstado(EstadodeLibro.PRESTADO);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(libroRepository.findById(1L)).thenReturn(Optional.of(libro));

        assertThrows(LibroNoEncontradoException.class, () -> prestamoService.crearPrestamo(1L, 1L));
    }

    @Test
    void cuandoObtenerTodos_entoncesRetornaListaPrestamos() {
        when(prestamoRepository.findAll()).thenReturn(List.of(prestamo));

        List<Prestamo> resultado = prestamoService.obtenerTodos();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(prestamoRepository).findAll();
    }

    @Test
    void cuandoBuscarPorIdExiste_entoncesRetornaPrestamo() {
        when(prestamoRepository.findById(1L)).thenReturn(Optional.of(prestamo));

        Prestamo resultado = prestamoService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(prestamoRepository).findById(1L);
    }

    @Test
    void cuandoBuscarPorIdNoExiste_entoncesLanzaExcepcion() {
        when(prestamoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PrestamoNoEncontradoException.class, () -> prestamoService.buscarPorId(1L));
    }
}

