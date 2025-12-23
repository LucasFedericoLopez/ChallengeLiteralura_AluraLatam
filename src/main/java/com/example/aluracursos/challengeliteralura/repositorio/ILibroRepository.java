package com.example.aluracursos.challengeliteralura.repositorio;

import com.example.aluracursos.challengeliteralura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILibroRepository extends JpaRepository<Libro,Long> {
}
