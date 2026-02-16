package com.ezequiel.tema03.proyectoFinal.repository;

import com.ezequiel.tema03.proyectoFinal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  Optional<Usuario> findByUsername(String username);
}
