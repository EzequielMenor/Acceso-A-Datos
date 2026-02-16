package com.ezequiel.tema03.proyectoFinal.controller;

import com.ezequiel.tema03.proyectoFinal.dto.AutorRequestDTO;
import com.ezequiel.tema03.proyectoFinal.dto.AutorResponseDTO;
import com.ezequiel.tema03.proyectoFinal.dto.FraseResponseDTO;
import com.ezequiel.tema03.proyectoFinal.service.AutorService;
import com.ezequiel.tema03.proyectoFinal.service.FraseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/autores")
@RequiredArgsConstructor
public class AutorController {
  private final AutorService autorService;
  private final FraseService fraseService;

  @GetMapping
  public ResponseEntity<Page<AutorResponseDTO>> getAll(Pageable pageable) {
    return ResponseEntity.ok(autorService.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<AutorResponseDTO> getById(@PathVariable Long id) {
    return ResponseEntity.ok(autorService.findById(id));
  }

  @GetMapping("/{id}/frases")
  public ResponseEntity<List<FraseResponseDTO>> getFrasesByAutor(@PathVariable Long id) {
    return ResponseEntity.ok(fraseService.findByAutor(id));
  }

  @PostMapping
  public ResponseEntity<AutorResponseDTO> create(
      @org.springframework.web.bind.annotation.RequestBody @jakarta.validation.Valid AutorRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(autorService.save(dto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<AutorResponseDTO> update(@PathVariable Long id,
      @org.springframework.web.bind.annotation.RequestBody @jakarta.validation.Valid AutorRequestDTO dto) {
    return ResponseEntity.ok(autorService.update(id, dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    autorService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
