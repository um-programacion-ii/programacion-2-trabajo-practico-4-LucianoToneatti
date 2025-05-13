package com.biblioteca.gestion;

public class Libro {
    private int id;
    private String isbn;
    private String titulo;
    private String autor;
    private EstadodeLibro estado;

    // Constructor vacío
    public Libro() {}

    // Constructor completo
    public Libro(int id, String isbn, String titulo, String autor, EstadodeLibro estado) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.estado = estado;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public EstadodeLibro getEstado() {
        return estado;
    }

    public void setEstado(EstadodeLibro estado) {
        this.estado = estado;
    }
}

