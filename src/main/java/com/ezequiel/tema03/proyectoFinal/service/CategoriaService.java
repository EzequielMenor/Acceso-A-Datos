package com.ezequiel.tema03.proyectoFinal.service;

import com.ezequiel.tema03.proyectoFinal.dto.CategoriaRequestDTO;
import com.ezequiel.tema03.proyectoFinal.dto.CategoriaResponseDTO;
import com.ezequiel.tema03.proyectoFinal.exception.DuplicateResourceException;
import com.ezequiel.tema03.proyectoFinal.exception.ResourceNotFoundException;
import com.ezequiel.tema03.proyectoFinal.model.Categoria;
import com.ezequiel.tema03.proyectoFinal.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoriaService {
  private final CategoriaRepository categoriaRepository;

  public Page<CategoriaResponseDTO> findAll(Pageable pageable) {
    return categoriaRepository.findAll(pageable).map(this::mapToDTO);
  }

  public CategoriaResponseDTO findById(Long id) {
    return categoriaRepository.findById(id)
        .map(this::mapToDTO)
        .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
  }

  @Transactional
  public CategoriaResponseDTO save(CategoriaRequestDTO dto) {
    if (categoriaRepository.findByNombre(dto.getNombre()).isPresent()) {
      throw new DuplicateResourceException("La categoría ya existe");
    }
    Categoria categoria = Categoria.builder()
        .nombre(dto.getNombre())
        .build();
    return mapToDTO(categoriaRepository.save(categoria));
  }

  @Transactional
  public CategoriaResponseDTO update(Long id, CategoriaRequestDTO dto) {
    Categoria categoria = categoriaRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
    categoria.setNombre(dto.getNombre());
    return mapToDTO(categoriaRepository.save(categoria));
  }

  @Transactional
  public void delete(Long id) {
    if (!categoriaRepository.existsById(id)) {
      throw new ResourceNotFoundException("Categoría no encontrada");
    }
    categoriaRepository.deleteById(id);
  }

  private CategoriaResponseDTO mapToDTO(Categoria categoria) {
    CategoriaResponseDTO dto = new CategoriaResponseDTO();
    dto.setId(categoria.getId());
    dto.setNombre(categoria.getNombre());
    return dto;
  }
}
