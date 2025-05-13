package com.biblioteca.gestion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    private int id;
    private String isbn;
    private String titulo;
    private String autor;
    private EstadodeLibro estado;
}
