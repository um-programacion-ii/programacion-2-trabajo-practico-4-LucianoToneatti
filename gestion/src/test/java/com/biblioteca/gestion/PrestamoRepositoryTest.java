package com.biblioteca.gestion;

import com.biblioteca.gestion.estados.EstadodeLibro;
import com.biblioteca.gestion.models.Libro;
import com.biblioteca.gestion.models.Prestamo;
import com.biblioteca.gestion.models.Usuario;
import com.biblioteca.gestion.repositories.LibroRepository;
import com.biblioteca.gestion.repositories.PrestamoRepository;
import com.biblioteca.gestion.repositories.UsuarioRepository;
import com.biblioteca.gestion.repositories.PrestamoRepositoryImpl;
import com.biblioteca.gestion.repositories.UsuarioRepositoryImpl;
import com.biblioteca.gestion.repositories.LibroRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PrestamoRepositoryTest {

    private PrestamoRepository prestamoRepository;
    private UsuarioRepository usuarioRepository;
    private LibroRepository libroRepository;

    @BeforeEach
    void setUp() {
        // Usamos las implementaciones en memoria de los repositorios
        prestamoRepository = new PrestamoRepositoryImpl();
        usuarioRepository = new UsuarioRepositoryImpl();
        libroRepository = new LibroRepositoryImpl();
    }

    @Test
    void guardarYBuscarPrestamoPorId() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setEmail("juan@example.com");
        usuario = usuarioRepository.save(usuario);

        Libro libro = new Libro();
        libro.setTitulo("Libro Préstamo");
        libro.setAutor("Autor");
        libro.setEstado(EstadodeLibro.DISPONIBLE);
        libro = libroRepository.save(libro);

        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);
        prestamo.setLibro(libro);
        prestamo.setFechaPrestamo(LocalDate.now());

        Prestamo guardado = prestamoRepository.save(prestamo);
        Optional<Prestamo> encontrado = prestamoRepository.findById(guardado.getId());

        assertTrue(encontrado.isPresent());
        assertEquals(usuario.getId(), encontrado.get().getUsuario().getId());
        assertEquals(libro.getId(), encontrado.get().getLibro().getId());
    }

    @Test
    void eliminarPrestamo() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Eliminar Préstamo");
        usuario.setEmail("eliminar@prestamo.com");
        usuario = usuarioRepository.save(usuario);

        Libro libro = new Libro();
        libro.setTitulo("Libro para borrar");
        libro.setAutor("Autor");
        libro.setEstado(EstadodeLibro.DISPONIBLE);
        libro = libroRepository.save(libro);

        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);
        prestamo.setLibro(libro);
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo = prestamoRepository.save(prestamo);

        prestamoRepository.deleteById(prestamo.getId());

        assertFalse(prestamoRepository.findById(prestamo.getId()).isPresent());
    }
}

