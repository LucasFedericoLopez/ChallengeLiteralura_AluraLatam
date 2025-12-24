package com.example.aluracursos.challengeliteralura.repositorio;

import com.example.aluracursos.challengeliteralura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IAutorRepository extends JpaRepository<Autor,Long> {
    Optional<Autor> findByNombreIgnoreCase(String nombre);
    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.librosPropios")
    List<Autor> buscarAutoresConLibros();
    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.librosPropios WHERE a.fechaDeNacimiento <= :anio " +
            "AND (a.fechaDeFallecimiento IS NULL OR a.fechaDeFallecimiento > :anio)")
    List<Autor> buscarAutoresVivosEnAnio(Integer anio);

}
