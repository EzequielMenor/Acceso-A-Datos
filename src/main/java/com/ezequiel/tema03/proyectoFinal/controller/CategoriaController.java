package com.ezequiel.tema03.proyectoFinal.controller;

import com.ezequiel.tema03.proyectoFinal.dto.CategoriaRequestDTO;
import com.ezequiel.tema03.proyectoFinal.dto.CategoriaResponseDTO;
import com.ezequiel.tema03.proyectoFinal.dto.FraseResponseDTO;
import com.ezequiel.tema03.proyectoFinal.service.CategoriaService;
import com.ezequiel.tema03.proyectoFinal.service.FraseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
@RequiredArgsConstructor
public class CategoriaController {
  private final CategoriaService categoriaService;
  private final FraseService fraseService;

  @GetMapping
  public ResponseEntity<Page<CategoriaResponseDTO>> getAll(Pageable pageable) {
    return ResponseEntity.ok(categoriaService.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoriaResponseDTO> getById(@PathVariable Long id) {
    return ResponseEntity.ok(categoriaService.findById(id));
  }

  @GetMapping("/{id}/frases")
  public ResponseEntity<List<FraseResponseDTO>> getFrasesByCategoria(@PathVariable Long id) {
    return ResponseEntity.ok(fraseService.findByCategoria(id));
  }

  @PostMapping
  public ResponseEntity<CategoriaResponseDTO> create(
      @org.springframework.web.bind.annotation.RequestBody @jakarta.validation.Valid CategoriaRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(dto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoriaResponseDTO> update(@PathVariable Long id,
      @org.springframework.web.bind.annotation.RequestBody @jakarta.validation.Valid CategoriaRequestDTO dto) {
    return ResponseEntity.ok(categoriaService.update(id, dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    categoriaService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
