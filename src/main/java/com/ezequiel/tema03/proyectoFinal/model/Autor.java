package com.ezequiel.tema03.proyectoFinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "autores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Autor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  @NotBlank(message = "El nombre es obligatorio")
  private String nombre;

  @Column(name = "anio_nacimiento")
  private Integer anioNacimiento;

  @Column(name = "anio_fallecimiento")
  private Integer anioFallecimiento;

  private String profesion;

  @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
  private List<Frase> frases;
}
