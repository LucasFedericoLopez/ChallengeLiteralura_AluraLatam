package com.example.aluracursos.challengeliteralura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Libro> librosPropios = new ArrayList<>();

    public Autor(){}

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaDeNacimiento = datosAutor.fechaDeNacimiento();
        this.fechaDeFallecimiento = datosAutor.fechaDeFallecimiento();
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public Integer getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public List<Libro> getLibrosPropios() {
        return librosPropios;
    }

    public void agregarLibro(Libro libro) {
        librosPropios.add(libro);
    }

    public void obtenerLosTitulosDeLosLibros() {
        librosPropios.forEach(Libro::getTitulo);
    }

    @Override
    public String toString() {
        return """
            -------------------------
            Autor: %s
            Nacimiento: %s
            Fallecimiento: %s
            """.formatted(
                nombre,
                fechaDeNacimiento != null ? fechaDeNacimiento : "Desconocido",
                fechaDeFallecimiento != null ? fechaDeFallecimiento : "Desconocido"
        );
    }
}

