package com.ezequiel.tema03.proyectoFinal.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ErrorDTO {
  private String mensaje;
  private LocalDateTime fecha;
  private int status;
}
