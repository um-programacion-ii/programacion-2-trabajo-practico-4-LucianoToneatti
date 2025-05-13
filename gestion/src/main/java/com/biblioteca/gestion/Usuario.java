package com.biblioteca.gestion;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private EstadodeUsuario estado;

    // Constructor vacío
    public Usuario() {}

    // Constructor completo
    public Usuario(int id, String nombre, String email, EstadodeUsuario estado) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.estado = estado;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EstadodeUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadodeUsuario estado) {
        this.estado = estado;
    }
}
