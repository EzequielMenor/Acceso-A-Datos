package com.ezequiel.tema02.ejemplosClase.repository;

import com.ezequiel.tema02.ejemplosClase.model.Producto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductoRepository {
    Producto save(Producto producto);
    boolean delete(Producto producto);
    Optional<Producto> findById(UUID id);
    List<Producto> findAll();
    boolean existsById(UUID id);
}
