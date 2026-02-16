package com.ezequiel.tema03.proyectoFinal.controller;

import com.ezequiel.tema03.proyectoFinal.dto.FraseRequestDTO;
import com.ezequiel.tema03.proyectoFinal.dto.FraseResponseDTO;
import com.ezequiel.tema03.proyectoFinal.service.FraseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/frases")
@RequiredArgsConstructor
public class FraseController {
  private final FraseService fraseService;

  @GetMapping
  public ResponseEntity<Page<FraseResponseDTO>> getAll(Pageable pageable) {
    return ResponseEntity.ok(fraseService.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<FraseResponseDTO> getById(@PathVariable Long id) {
    return ResponseEntity.ok(fraseService.findById(id));
  }

  @GetMapping("/dia")
  public ResponseEntity<FraseResponseDTO> getFraseDelDia(
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
    return ResponseEntity.ok(fraseService.findFraseDelDia(fecha));
  }

  @PostMapping
  public ResponseEntity<FraseResponseDTO> create(
      @org.springframework.web.bind.annotation.RequestBody @jakarta.validation.Valid FraseRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(fraseService.save(dto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<FraseResponseDTO> update(@PathVariable Long id,
      @org.springframework.web.bind.annotation.RequestBody @jakarta.validation.Valid FraseRequestDTO dto) {
    return ResponseEntity.ok(fraseService.update(id, dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    fraseService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
