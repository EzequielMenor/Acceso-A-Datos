package com.ezequiel.tema02.ejemplosClase.repository;

import com.ezequiel.tema02.ejemplosClase.exception.DataAccessException;
import com.ezequiel.tema02.ejemplosClase.model.Producto;
import com.ezequiel.tema02.ejemplosClase.repository.dao.ProductoDAO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductoRepositoryImpl implements ProductoRepository {
    private final ProductoDAO productoDAO;

    public ProductoRepositoryImpl(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    @Override
    public Producto save(Producto producto) {
        try {
            if (productoDAO.update(producto) == 0) {
                if (productoDAO.insert(producto) == 0) {
                    throw new DataAccessException(
                            "No se pudo insertar ni actualizar el producto con ID " + producto.getId());
                }
            }
            return producto;
        } catch (Exception ex) {
            throw new DataAccessException("Error en save(producto)", ex);
        }
    }

    @Override
    public boolean delete(Producto producto) {
        try {
            return productoDAO.delete(producto.getId()) > 0;
        } catch (Exception e) {
            throw new DataAccessException("Error en delete(producto)", e);
        }
    }

    @Override
    public Optional<Producto> findById(UUID id) {
        try {
            return productoDAO.findById(id);
        } catch (Exception e) {
            throw new DataAccessException("Error en findById(id)", e);
        }
    }

    @Override
    public List<Producto> findAll() {
        try {
            return productoDAO.findAll();
        } catch (Exception e) {
            throw new DataAccessException("Error en findAll()", e);
        }
    }

    @Override
    public boolean existsById(UUID id) {
        return false;
    }

}
