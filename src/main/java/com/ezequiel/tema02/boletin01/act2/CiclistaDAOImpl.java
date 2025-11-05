package com.ezequiel.tema02.boletin01.act2;

import com.ezequiel.tema02.boletin01.DB;
import com.ezequiel.tema02.boletin01.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CiclistaDAOImpl implements CiclistaDAO{
    private final DB db;

    public CiclistaDAOImpl(DB db){
        this.db = db;
    }

    @Override
    public List<Ciclista> findByIdEquipo(int id_equipo){
        String sql = "SELECT id_ciclista, id_equipo, nombre, pais, fecha_nac FROM ciclistas WHERE id_equipo = ? ORDER BY nombre;";
        List<Ciclista> ciclistas = new ArrayList<>();
        try(Connection conn = db.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1, id_equipo);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()){
                    LocalDate fecha = resultSet.getDate("fecha_nac").toLocalDate();
                     ciclistas.add(new Ciclista(
                            resultSet.getInt("id_ciclista"),
                            resultSet.getInt("id_equipo"),
                            resultSet.getString("nombre"),
                            resultSet.getString("pais"),
                            fecha
                    ));
                }
            }
        }catch (SQLException e){
            throw new DataAccessException("Error al buscar ciclista en equipo " + id_equipo, e);
        }
        return ciclistas;
    }

    @Override
    public List<Ciclista> findAll() {
        String sql = "SELECT id_ciclista, id_equipo, nombre, pais, fecha_nac FROM ciclistas ORDER BY id_equipo, nombre;";
        List<Ciclista> ciclistas = new ArrayList<>();
        try (Connection conn = db.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                LocalDate fecha = resultSet.getDate("fecha_nac").toLocalDate();
                ciclistas.add(new Ciclista(
                        resultSet.getInt("id_ciclista"),
                        resultSet.getInt("id_equipo"),
                        resultSet.getString("nombre"),
                        resultSet.getString("pais"),
                        fecha
                ));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error al buscar ciclistas", e);
        }
        return ciclistas;
    }
}
