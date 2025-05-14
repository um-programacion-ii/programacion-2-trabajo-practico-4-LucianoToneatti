package com.biblioteca.gestion.services;

import com.biblioteca.gestion.estados.EstadodeLibro;
import com.biblioteca.gestion.exceptions.LibroNoEncontradoException;
import com.biblioteca.gestion.exceptions.PrestamoNoEncontradoException;
import com.biblioteca.gestion.exceptions.UsuarioNoEncontradoException;
import com.biblioteca.gestion.models.Prestamo;
import com.biblioteca.gestion.models.Usuario;
import com.biblioteca.gestion.models.Libro;
import com.biblioteca.gestion.repositories.PrestamoRepository;
import com.biblioteca.gestion.repositories.UsuarioRepository;
import com.biblioteca.gestion.repositories.LibroRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LibroRepository libroRepository;

    // Constructor que inicializa los repositorios
    public PrestamoServiceImpl(
            PrestamoRepository prestamoRepository,
            UsuarioRepository usuarioRepository,
            LibroRepository libroRepository
    ) {
        this.prestamoRepository = prestamoRepository;
        this.usuarioRepository = usuarioRepository;
        this.libroRepository = libroRepository;
    }

    @Override
    public Prestamo buscarPorId(Long id) {
        return prestamoRepository.findById(id)
                .orElseThrow(() -> new PrestamoNoEncontradoException(id));
    }

    @Override
    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.findAll();
    }

    @Override
    public Prestamo guardar(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    @Override
    public void eliminar(Long id) {
        if (!prestamoRepository.existsById(id)) {
            throw new PrestamoNoEncontradoException(id);
        }
        prestamoRepository.deleteById(id);
    }

    @Override
    public Prestamo actualizar(Long id, Prestamo prestamo) {
        if (!prestamoRepository.existsById(id)) {
            throw new PrestamoNoEncontradoException(id);
        }
        prestamo.setId(id);
        return prestamoRepository.save(prestamo);
    }

    @Override
    public Prestamo crearPrestamo(Long usuarioId, Long libroId) {
        // Buscar usuario y libro
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNoEncontradoException(usuarioId));
        Libro libro = libroRepository.findById(libroId)
                .orElseThrow(() -> new LibroNoEncontradoException(libroId));

        // Verificar si el libro está disponible
        if (libro.getEstado() == EstadodeLibro.PRESTADO) {
            throw new LibroNoEncontradoException(libroId); // Lanza la excepción si el libro está prestado
        }

        // Crear y guardar el préstamo
        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);
        prestamo.setLibro(libro);
        prestamo.setFechaPrestamo(LocalDate.now());

        return prestamoRepository.save(prestamo); // Guardar el préstamo
    }
}

