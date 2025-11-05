package com.ezequiel.tema02.boletin01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String DB_DRIVER = "postgresql"; // mysql
    private static final String DB_HOST = "localhost";
    private static final int DB_PORT = 5432; // 3306 para mysql
    private static final String DB_NAME = "postgres";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "test";
    public static void main(String[] args) {
        String url = "jdbc:" + DB_DRIVER + "://" + DB_HOST + ":" + DB_PORT + "/" +
                DB_NAME;
        try (Connection conexion = DriverManager.getConnection(url, DB_USERNAME,
                DB_PASSWORD)) {
            System.out.println("Conexión exitosa a la base de datos");
            // Resto de sentencias mysql
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            System.out.println(e.getMessage());
        }
    }
}