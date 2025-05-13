package com.biblioteca.gestion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prestamo {
    private int id;
    private String libro;
    private String usuario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
}