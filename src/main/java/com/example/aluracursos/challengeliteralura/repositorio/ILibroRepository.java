package com.example.aluracursos.challengeliteralura.repositorio;

import com.example.aluracursos.challengeliteralura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILibroRepository extends JpaRepository<Libro,Long> {
    List<Libro> findByIdioma(String idioma);
}
