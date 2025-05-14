package com.biblioteca.gestion.models;

import com.biblioteca.gestion.estados.EstadodeUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private EstadodeUsuario estado;
}
