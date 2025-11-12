package com.ezequiel.tema02.boletin01.act1;

import com.ezequiel.tema02.boletin01.DB;
import com.ezequiel.tema02.boletin01.DataAccessException;
import com.ezequiel.tema02.boletin01.act9.ClasifEquipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
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

    //Ejercicio 9
    @Override
    public List<ClasifEquipo> getClasifEquipo() {
        String sql = "WITH ranked_times AS (" +
                "    SELECT " +
                "        c.id_equipo," +
                "        r.tiempo," +
                "        ROW_NUMBER() OVER(PARTITION BY r.id_etapa, c.id_equipo ORDER BY r.tiempo ASC) as ranking_tiempo" +
                "    FROM " +
                "        resultados_etapa r" +
                "    JOIN " +
                "        ciclistas c ON r.id_ciclista = c.id_ciclista" +
                "    WHERE " +
                "        r.estado = 'FINALIZADO' AND r.tiempo IS NOT NULL" +
                "), " +
                "top_3_times AS (" +
                "    SELECT " +
                "        id_equipo," +
                "        tiempo" +
                "    FROM " +
                "        ranked_times" +
                "    WHERE " +
                "        ranking_tiempo <= 3" +
                ") " +
                "SELECT " +
                "    eq.nombre AS nombre_equipo," +
                "    SUM(EXTRACT(EPOCH FROM t.tiempo)) AS total_segundos " +
                "FROM " +
                "    top_3_times t" +
                " JOIN " +
                "    equipos eq ON t.id_equipo = eq.id_equipo " +
                "GROUP BY " +
                "    eq.id_equipo, eq.nombre " +
                "ORDER BY " +
                "    total_segundos ASC;";
        List<ClasifEquipo> clasifEquipos = new ArrayList<>();
        try (Connection conn = db.getConnection();
             PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                long totalSegundos = rs.getLong("total_segundos");
                Duration tiempoTotal = Duration.ofSeconds(totalSegundos);
                clasifEquipos.add(new ClasifEquipo(
                        rs.getString("nombre_equipo"),
                        tiempoTotal
                ));
            }

        } catch (SQLException e) {
            throw new DataAccessException("Error al obtener clasificación de equipo", e);
        }
        return clasifEquipos;
    }
}
