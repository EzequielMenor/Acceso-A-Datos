package com.ezequiel.tema02.ejemplosClase;

import com.ezequiel.tema02.ejemplosClase.connection.DataSource;
import com.ezequiel.tema02.ejemplosClase.model.Producto;
import com.ezequiel.tema02.ejemplosClase.repository.ProductoRepository;
import com.ezequiel.tema02.ejemplosClase.repository.ProductoRepositoryImpl;
import com.ezequiel.tema02.ejemplosClase.repository.dao.ProductoDAO;
import com.ezequiel.tema02.ejemplosClase.repository.jdbc.ProductoDAOImplJDBC;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataSource dataSource = new DataSource(DataSource.Driver.POSTGRESQL, "localhost", 5432, "tienda", "root", "test");
        ProductoDAO productoDAO = new ProductoDAOImplJDBC(dataSource);
        ProductoRepository productoRepository = new ProductoRepositoryImpl(productoDAO);

        Producto producto = new Producto("Pala", "Pala de cavar", 30.99, 10);

        try{
            producto = productoRepository.save(producto);
            System.out.println("Producto guardado con ID: " + producto.getId());
        }catch (Exception e){
            System.err.println("Error al guardar el producto: " + e.getMessage());
            e.printStackTrace();
        }

        List<Producto> productos = productoRepository.findAll();
        System.out.println(productos);

    }
}
