package com.ezequiel.tema03.proyectoFinal.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class FraseRequestDTO {
  @jakarta.validation.constraints.NotBlank(message = "El texto de la frase es obligatorio")
  private String texto;

  private LocalDate fechaProgramada;

  @jakarta.validation.constraints.NotNull(message = "El ID del autor es obligatorio")
  private Long autorId;

  @jakarta.validation.constraints.NotNull(message = "El ID de la categoría es obligatorio")
  private Long categoriaId;
}
