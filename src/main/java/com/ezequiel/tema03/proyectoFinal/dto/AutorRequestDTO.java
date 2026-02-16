package com.ezequiel.tema03.proyectoFinal.dto;

import lombok.Data;

@Data
public class AutorRequestDTO {
  @jakarta.validation.constraints.NotBlank(message = "El nombre es obligatorio")
  private String nombre;

  private Integer anioNacimiento;

  private Integer anioFallecimiento;

  private String profesion;
}
