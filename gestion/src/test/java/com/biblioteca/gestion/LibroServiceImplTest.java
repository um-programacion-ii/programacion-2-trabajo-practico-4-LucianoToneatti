package com.biblioteca.gestion;

import com.biblioteca.gestion.estados.EstadodeLibro;
import com.biblioteca.gestion.exceptions.LibroNoEncontradoException;
import com.biblioteca.gestion.models.Libro;
import com.biblioteca.gestion.repositories.LibroRepository;
import com.biblioteca.gestion.services.LibroServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibroServiceImplTest {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroServiceImpl libroService;

    @Test
    void cuandoBuscarPorIsbnExiste_entoncesRetornaLibro() {
        // Arrange
        String isbn = "123-456-789";
        Libro libroEsperado = new Libro(1L, isbn, "Papanato", "Esteban", EstadodeLibro.DISPONIBLE);
        when(libroRepository.findByIsbn(isbn)).thenReturn(Optional.of(libroEsperado));

        // Act
        Libro resultado = libroService.buscarPorIsbn(isbn);

        // Assert
        assertNotNull(resultado);
        assertEquals(isbn, resultado.getIsbn());
        verify(libroRepository).findByIsbn(isbn);
    }

    @Test
    void cuandoBuscarPorIsbnNoExiste_entoncesLanzaExcepcion() {
        // Arrange
        String isbn = "123-456-789";
        when(libroRepository.findByIsbn(isbn)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(LibroNoEncontradoException.class, () ->
                libroService.buscarPorIsbn(isbn)
        );
    }

    @Test
    void cuandoBuscarPorIdExiste_entoncesRetornaLibro() {
        // Arrange
        Long id = 1L;
        Libro libroEsperado = new Libro(id, "123-456-789", "Papanato", "Esteban", EstadodeLibro.DISPONIBLE);
        when(libroRepository.findById(id)).thenReturn(Optional.of(libroEsperado));

        // Act
        Libro resultado = libroService.buscarPorId(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(libroRepository).findById(id);
    }

    @Test
    void cuandoBuscarPorIdNoExiste_entoncesLanzaExcepcion() {
        // Arrange
        Long id = 1L;
        when(libroRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(LibroNoEncontradoException.class, () ->
                libroService.buscarPorId(id)
        );
    }

    @Test
    void cuandoGuardarLibro_entoncesRetornaLibroGuardado() {
        // Arrange
        Libro libro = new Libro(null, "123-456-789", "Papanato", "Esteban", EstadodeLibro.DISPONIBLE);
        Libro libroGuardado = new Libro(1L, "123-456-789", "Papanato", "Esteban", EstadodeLibro.DISPONIBLE);
        when(libroRepository.save(libro)).thenReturn(libroGuardado);

        // Act
        Libro resultado = libroService.guardar(libro);

        // Assert
        assertNotNull(resultado);
        assertEquals(libroGuardado.getId(), resultado.getId());
        verify(libroRepository).save(libro);
    }

    @Test
    void cuandoActualizarLibro_entoncesRetornaLibroActualizado() {
        // Arrange
        Long id = 1L;
        Libro libro = new Libro(id, "123-456-789", "Papanato", "Esteban", EstadodeLibro.DISPONIBLE);
        when(libroRepository.existsById(id)).thenReturn(true);
        when(libroRepository.save(libro)).thenReturn(libro);

        // Act
        Libro resultado = libroService.actualizar(id, libro);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(libroRepository).save(libro);
    }

    @Test
    void cuandoEliminarLibro_entoncesEliminaCorrectamente() {
        // Arrange
        Long id = 1L;
        when(libroRepository.existsById(id)).thenReturn(true);
        doNothing().when(libroRepository).deleteById(id);

        // Act
        libroService.eliminar(id);

        // Assert
        verify(libroRepository).existsById(id);
        verify(libroRepository).deleteById(id);
    }


}

