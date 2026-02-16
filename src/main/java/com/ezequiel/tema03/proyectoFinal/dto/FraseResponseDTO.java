package com.ezequiel.tema03.proyectoFinal.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class FraseResponseDTO {
  private Long id;
  private String texto;
  private LocalDate fechaProgramada;
  private String autorNombre;
  private String categoriaNombre;
}
