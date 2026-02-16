package com.ezequiel.tema03.proyectoFinal.exception;

import com.ezequiel.tema03.proyectoFinal.dto.ErrorDTO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorDTO> handleResourceNotFoundException(ResourceNotFoundException ex) {
    ErrorDTO error = ErrorDTO.builder()
        .mensaje(ex.getMessage())
        .fecha(LocalDateTime.now())
        .status(HttpStatus.NOT_FOUND.value())
        .build();
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DuplicateResourceException.class)
  public ResponseEntity<ErrorDTO> handleDuplicateResourceException(DuplicateResourceException ex) {
    ErrorDTO error = ErrorDTO.builder()
        .mensaje(ex.getMessage())
        .fecha(LocalDateTime.now())
        .status(HttpStatus.CONFLICT.value())
        .build();
    return new ResponseEntity<>(error, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
    String errors = ex.getBindingResult().getAllErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.joining(", "));

    ErrorDTO error = ErrorDTO.builder()
        .mensaje("Error de validación: " + errors)
        .fecha(LocalDateTime.now())
        .status(HttpStatus.BAD_REQUEST.value())
        .build();
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(org.springframework.data.mapping.PropertyReferenceException.class)
  public ResponseEntity<ErrorDTO> handlePropertyReferenceException(
      org.springframework.data.mapping.PropertyReferenceException ex) {
    ErrorDTO error = ErrorDTO.builder()
        .mensaje("Error en el filtro o ordenación: " + ex.getMessage())
        .fecha(LocalDateTime.now())
        .status(HttpStatus.BAD_REQUEST.value())
        .build();
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDTO> handleGlobalException(Exception ex) {
    ErrorDTO error = ErrorDTO.builder()
        .mensaje("Error interno del servidor: " + ex.getMessage())
        .fecha(LocalDateTime.now())
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .build();
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
