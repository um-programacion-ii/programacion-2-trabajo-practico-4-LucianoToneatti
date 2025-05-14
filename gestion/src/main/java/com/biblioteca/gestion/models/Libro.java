package com.biblioteca.gestion.models;

import com.biblioteca.gestion.estados.EstadodeLibro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    private Long id;
    private String isbn;
    private String titulo;
    private String autor;
    private EstadodeLibro estado;
}
