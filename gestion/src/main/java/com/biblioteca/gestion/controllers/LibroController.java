package com.biblioteca.gestion.controllers;

import com.biblioteca.gestion.exceptions.LibroNoEncontradoException;
import com.biblioteca.gestion.models.Libro;
import com.biblioteca.gestion.services.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public List<Libro> obtenerTodos() {
        return libroService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerPorId(@PathVariable Long id) {
        Libro libro = libroService.buscarPorId(id);
        if (libro == null) {
            throw new LibroNoEncontradoException(id);
        }
        return ResponseEntity.ok(libro);
    }

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro libroGuardado = libroService.guardar(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(libroGuardado); // ← status 201
    }

    @PutMapping("/{id}")
    public Libro actualizar(@PathVariable Long id, @RequestBody Libro libro) {
        return libroService.actualizar(id, libro);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        libroService.eliminar(id);
    }
}

