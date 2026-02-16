package com.ezequiel.tema03.proyectoFinal.service;

import com.ezequiel.tema03.proyectoFinal.dto.FraseRequestDTO;
import com.ezequiel.tema03.proyectoFinal.dto.FraseResponseDTO;
import com.ezequiel.tema03.proyectoFinal.exception.ResourceNotFoundException;
import com.ezequiel.tema03.proyectoFinal.model.Autor;
import com.ezequiel.tema03.proyectoFinal.model.Categoria;
import com.ezequiel.tema03.proyectoFinal.model.Frase;
import com.ezequiel.tema03.proyectoFinal.repository.AutorRepository;
import com.ezequiel.tema03.proyectoFinal.repository.CategoriaRepository;
import com.ezequiel.tema03.proyectoFinal.repository.FraseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FraseService {
  private final FraseRepository fraseRepository;
  private final AutorRepository autorRepository;
  private final CategoriaRepository categoriaRepository;

  public Page<FraseResponseDTO> findAll(Pageable pageable) {
    return fraseRepository.findAll(pageable).map(this::mapToDTO);
  }

  public FraseResponseDTO findById(Long id) {
    return fraseRepository.findById(id)
        .map(this::mapToDTO)
        .orElseThrow(() -> new ResourceNotFoundException("Frase no encontrada"));
  }

  public FraseResponseDTO findFraseDelDia(LocalDate fecha) {
    LocalDate target = (fecha == null) ? LocalDate.now() : fecha;
    return fraseRepository.findByFechaProgramada(target)
        .map(this::mapToDTO)
        .orElseThrow(() -> new ResourceNotFoundException("No hay frase programada para el " + target));
  }

  public List<FraseResponseDTO> findByAutor(Long autorId) {
    return fraseRepository.findByAutorId(autorId).stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
  }

  public List<FraseResponseDTO> findByCategoria(Long categoriaId) {
    return fraseRepository.findByCategoriaId(categoriaId).stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
  }

  @Transactional
  public FraseResponseDTO save(FraseRequestDTO dto) {
    Autor autor = autorRepository.findById(dto.getAutorId())
        .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado"));
    Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
        .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

    Frase frase = Frase.builder()
        .texto(dto.getTexto())
        .fechaProgramada(dto.getFechaProgramada())
        .autor(autor)
        .categoria(categoria)
        .build();
    return mapToDTO(fraseRepository.save(frase));
  }

  @Transactional
  public FraseResponseDTO update(Long id, FraseRequestDTO dto) {
    Frase frase = fraseRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Frase no encontrada"));
    Autor autor = autorRepository.findById(dto.getAutorId())
        .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado"));
    Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
        .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

    frase.setTexto(dto.getTexto());
    frase.setFechaProgramada(dto.getFechaProgramada());
    frase.setAutor(autor);
    frase.setCategoria(categoria);
    return mapToDTO(fraseRepository.save(frase));
  }

  @Transactional
  public void delete(Long id) {
    if (!fraseRepository.existsById(id)) {
      throw new ResourceNotFoundException("Frase no encontrada");
    }
    fraseRepository.deleteById(id);
  }

  private FraseResponseDTO mapToDTO(Frase frase) {
    FraseResponseDTO dto = new FraseResponseDTO();
    dto.setId(frase.getId());
    dto.setTexto(frase.getTexto());
    dto.setFechaProgramada(frase.getFechaProgramada());
    dto.setAutorNombre(frase.getAutor().getNombre());
    dto.setCategoriaNombre(frase.getCategoria().getNombre());
    return dto;
  }
}
