package com.ezequiel.tema03.proyectoFinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "frases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Frase {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "TEXT", nullable = false)
  @NotBlank(message = "El texto de la frase es obligatorio")
  private String texto;

  @Column(name = "fecha_programada")
  private LocalDate fechaProgramada;

  @ManyToOne
  @JoinColumn(name = "autor_id", nullable = false)
  private Autor autor;

  @ManyToOne
  @JoinColumn(name = "categoria_id", nullable = false)
  private Categoria categoria;
}
