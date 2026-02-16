package com.ezequiel.tema03.proyectoFinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  @NotBlank(message = "El nombre es obligatorio")
  private String nombre;

  @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
  private List<Frase> frases;
}
