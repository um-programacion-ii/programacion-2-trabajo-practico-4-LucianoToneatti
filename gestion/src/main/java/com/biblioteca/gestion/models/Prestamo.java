package com.biblioteca.gestion.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prestamo {
    private Long id;
    private Libro libro;
    private Usuario usuario;
    private LocalDate fechaPrestamo;
    private Date fechaDevolucion;
}