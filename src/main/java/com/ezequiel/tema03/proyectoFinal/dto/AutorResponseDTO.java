package com.ezequiel.tema03.proyectoFinal.dto;

import lombok.Data;

@Data
public class AutorResponseDTO {
  private Long id;
  private String nombre;
  private Integer anioNacimiento;
  private Integer anioFallecimiento;
  private String profesion;
}
