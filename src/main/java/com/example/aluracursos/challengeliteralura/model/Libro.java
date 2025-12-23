package com.example.aluracursos.challengeliteralura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private String idioma;
    private Integer nroDescargas;

    public Libro(){}

    public Libro(DatosLibros datosLibros,Autor autor) {
        this.titulo = datosLibros.titulo();
        this.autor = autor;
        this.idioma = datosLibros.idiomas().stream().findFirst().orElse("Idioma desconocido");
        this.nroDescargas = datosLibros.numeroDeDescargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNroDescargas() {
        return nroDescargas;
    }

    public void setNroDescargas(Integer nroDescargas) {
        this.nroDescargas = nroDescargas;
    }

    @Override
    public String toString() {
        return """
            --------Libro--------
            Título: %s
            Autor: %s
            Idioma: %s
            Número de descargas: %d
            ---------------------
            """.formatted(titulo, autor.getNombre(), idioma, nroDescargas);
    }
}
