package com.ezequiel.tema03.proyectoFinal.repository;

import com.ezequiel.tema03.proyectoFinal.model.Frase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FraseRepository extends JpaRepository<Frase, Long> {
  List<Frase> findByAutorId(Long autorId);

  List<Frase> findByCategoriaId(Long categoriaId);

  Optional<Frase> findByFechaProgramada(LocalDate fecha);
}
