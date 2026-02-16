package com.ezequiel.tema03.proyectoFinal;

import com.ezequiel.tema03.proyectoFinal.model.Usuario;
import com.ezequiel.tema03.proyectoFinal.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FrasesCelebresApplication {
  public static void main(String[] args) {
    SpringApplication.run(FrasesCelebresApplication.class, args);
  }

  @Bean
  CommandLineRunner start(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
    return args -> {
      // Borramos y creamos para asegurar que la pass es admin123/standard123
      usuarioRepository.findByUsername("admin").ifPresent(usuarioRepository::delete);
      usuarioRepository.findByUsername("standard").ifPresent(usuarioRepository::delete);

      usuarioRepository.save(new Usuario(null, "admin", passwordEncoder.encode("admin123"), "ADMIN"));
      usuarioRepository.save(new Usuario(null, "standard", passwordEncoder.encode("standard123"), "STANDARD"));

      System.out.println(">>> Usuarios de prueba listos:");
      System.out.println(">>> admin / admin123");
      System.out.println(">>> standard / standard123");
    };
  }
}
