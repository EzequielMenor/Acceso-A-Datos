package com.ezequiel.tema03.proyectoFinal.dto;

import lombok.Data;

@Data
public class CategoriaRequestDTO {
  @jakarta.validation.constraints.NotBlank(message = "El nombre es obligatorio")
  private String nombre;
}
