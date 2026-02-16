package com.ezequiel.tema03.proyectoFinal.repository;

import com.ezequiel.tema03.proyectoFinal.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
  Optional<Categoria> findByNombre(String nombre);
}
