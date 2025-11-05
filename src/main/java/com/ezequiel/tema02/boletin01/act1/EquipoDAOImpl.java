package com.ezequiel.tema02.boletin01.act1;

import com.ezequiel.tema02.boletin01.DB;
import com.ezequiel.tema02.boletin01.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAOImpl implements EquipoDAO {
    private final DB db;

    public EquipoDAOImpl(DB db){
        this.db = db;
    }

    @Override
    public List<Equipo> findAll(){
        String sql = "SELECT id_equipo, nombre, pais FROM equipos;";
        List<Equipo> equipos = new ArrayList<>();
        try (Connection conn = db.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()){
                equipos.add(new Equipo(
                        resultSet.getInt("id_equipo"),
                        resultSet.getString("nombre"),
                        resultSet.getString("pais")
                ));
            }
        }catch (SQLException sqlException){
            throw new DataAccessException("Error al ejecutar la consulta", sqlException);
        }
        return equipos;
    }
}
