package com.ezequiel.tema03.proyectoFinal.service;

import com.ezequiel.tema03.proyectoFinal.dto.AutorRequestDTO;
import com.ezequiel.tema03.proyectoFinal.dto.AutorResponseDTO;
import com.ezequiel.tema03.proyectoFinal.exception.DuplicateResourceException;
import com.ezequiel.tema03.proyectoFinal.exception.ResourceNotFoundException;
import com.ezequiel.tema03.proyectoFinal.model.Autor;
import com.ezequiel.tema03.proyectoFinal.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AutorService {
  private final AutorRepository autorRepository;

  public Page<AutorResponseDTO> findAll(Pageable pageable) {
    return autorRepository.findAll(pageable).map(this::mapToDTO);
  }

  public AutorResponseDTO findById(Long id) {
    return autorRepository.findById(id)
        .map(this::mapToDTO)
        .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado"));
  }

  @Transactional
  public AutorResponseDTO save(AutorRequestDTO dto) {
    if (autorRepository.findByNombre(dto.getNombre()).isPresent()) {
      throw new DuplicateResourceException("El autor ya existe");
    }
    Autor autor = Autor.builder()
        .nombre(dto.getNombre())
        .anioNacimiento(dto.getAnioNacimiento())
        .anioFallecimiento(dto.getAnioFallecimiento())
        .profesion(dto.getProfesion())
        .build();
    return mapToDTO(autorRepository.save(autor));
  }

  @Transactional
  public AutorResponseDTO update(Long id, AutorRequestDTO dto) {
    Autor autor = autorRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado"));
    autor.setNombre(dto.getNombre());
    autor.setAnioNacimiento(dto.getAnioNacimiento());
    autor.setAnioFallecimiento(dto.getAnioFallecimiento());
    autor.setProfesion(dto.getProfesion());
    return mapToDTO(autorRepository.save(autor));
  }

  @Transactional
  public void delete(Long id) {
    if (!autorRepository.existsById(id)) {
      throw new ResourceNotFoundException("Autor no encontrado");
    }
    autorRepository.deleteById(id);
  }

  private AutorResponseDTO mapToDTO(Autor autor) {
    AutorResponseDTO dto = new AutorResponseDTO();
    dto.setId(autor.getId());
    dto.setNombre(autor.getNombre());
    dto.setAnioNacimiento(autor.getAnioNacimiento());
    dto.setAnioFallecimiento(autor.getAnioFallecimiento());
    dto.setProfesion(autor.getProfesion());
    return dto;
  }
}
