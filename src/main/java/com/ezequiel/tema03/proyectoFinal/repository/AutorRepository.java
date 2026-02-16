package com.ezequiel.tema03.proyectoFinal.repository;

import com.ezequiel.tema03.proyectoFinal.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
  Optional<Autor> findByNombre(String nombre);
}
