package com.ezequiel.tema02.boletin01.act2;

import com.ezequiel.tema02.boletin01.DB;
import com.ezequiel.tema02.boletin01.DataAccessException;
import com.ezequiel.tema02.boletin01.act6.ClasificacionMontana;
import com.ezequiel.tema02.boletin01.act7.ClasificacionRegularidad;
import com.ezequiel.tema02.boletin01.act8.ClasifGeneral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CiclistaDAOImpl implements CiclistaDAO {
    private final DB db;

    public CiclistaDAOImpl(DB db) {
        this.db = db;
    }

    @Override
    public List<Ciclista> findByIdEquipo(int id_equipo) {
        String sql = "SELECT id_ciclista, id_equipo, nombre, pais, fecha_nac FROM ciclistas WHERE id_equipo = ? ORDER BY nombre;";
        List<Ciclista> ciclistas = new ArrayList<>();
        try (Connection conn = db.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id_equipo);
            try (ResultSet resultSet = statement.executeQuery()) {
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
            }
        } catch (SQLException e) {
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


    //Ejercicio 4
    @Override
    public double getVelocidadMedia(int id_ciclista) {
        double velocidadKmH = 0.0;
        String sql = "SELECT SUM(e.distancia_km) AS total_km, SUM(EXTRACT(EPOCH FROM r.tiempo)) AS total_segundos " +
                "FROM resultados_etapa r " +
                "JOIN etapas e ON r.id_etapa = e.id_etapa " +
                "WHERE r.id_ciclista = ? AND r.estado = 'FINALIZADO'";
        try (Connection conn = db.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);) {
            statement.setInt(1, id_ciclista);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    double totalKM = resultSet.getDouble("total_km");
                    double totalSegundos = resultSet.getDouble("total_segundos");
                    if (totalSegundos == 0) return 0.0;
                    velocidadKmH = totalKM / (totalSegundos / 3600.0);
                }
            }

        } catch (SQLException e) {
            throw new DataAccessException("Error al conseguir la velocidad media del ciclista " + id_ciclista, e);
        }
        return velocidadKmH;
    }


    //Ejercicio 6
    @Override
    public List<ClasificacionMontana> getClasificacionMontana() {
        List<ClasificacionMontana> clasificacionMontanas = new ArrayList<>();
        String sql = "SELECT c.nombre AS nombre_ciclista, eq.nombre AS nombre_equipo, SUM(r.puntos) AS total_puntos FROM resultados_puerto r JOIN ciclistas c ON r.id_ciclista = c.id_ciclista JOIN equipos eq ON c.id_equipo = eq.id_equipo GROUP BY c.id_ciclista, eq.nombre ORDER BY total_puntos DESC;";

        try (Connection conn = db.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                clasificacionMontanas.add(new ClasificacionMontana(
                        resultSet.getString("nombre_ciclista"),
                        resultSet.getString("nombre_equipo"),
                        resultSet.getLong("total_puntos")
                ));
            }
        }catch (SQLException e){
            throw new DataAccessException("Error al obtener clasificación de montaña", e);
        }
        return clasificacionMontanas;
    }

    //Ejercicio 7
    @Override
    public List<ClasificacionRegularidad> getClasifRegularidad(){
        List<ClasificacionRegularidad> clasificacionRegularidad = new ArrayList<>();
        String sql = "WITH puntos_combinados AS (" +
                "    SELECT id_ciclista, puntos FROM resultados_sprint" +
                "    UNION ALL" +
                "    SELECT id_ciclista, puntos FROM puntos_meta" +
                ") " +
                "SELECT c.nombre AS nombre_ciclista, eq.nombre AS nombre_equipo, SUM(p.puntos) AS total_puntos " +
                "FROM puntos_combinados p " +
                "JOIN ciclistas c ON p.id_ciclista = c.id_ciclista " +
                "JOIN equipos eq ON c.id_equipo = eq.id_equipo " +
                "GROUP BY c.id_ciclista, eq.nombre " +
                "ORDER BY total_puntos DESC;";

        try (Connection conn = db.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                clasificacionRegularidad.add(new ClasificacionRegularidad(
                        resultSet.getString("nombre_ciclista"),
                        resultSet.getString("nombre_equipo"),
                        resultSet.getLong("total_puntos")
                ));
            }
        }catch (SQLException e){
            throw new DataAccessException("Error al obtener clasificación de regularidad", e);
        }
        return clasificacionRegularidad;
    }

    //Ejercicio 8
    @Override
    public List<ClasifGeneral> getClasifGeneral(){
        List<ClasifGeneral> clasifGenerals = new ArrayList<>();
        String sql = "SELECT \n" +
                "    c.nombre AS nombre_ciclista, \n" +
                "    eq.nombre AS nombre_equipo, \n" +
                "    SUM(EXTRACT(EPOCH FROM r.tiempo)) AS total_segundos\n" +
                "FROM \n" +
                "    resultados_etapa r\n" +
                "JOIN \n" +
                "    ciclistas c ON r.id_ciclista = c.id_ciclista\n" +
                "JOIN \n" +
                "    equipos eq ON c.id_equipo = eq.id_equipo\n" +
                "WHERE \n" +
                "    r.estado = 'FINALIZADO'\n" +
                "GROUP BY \n" +
                "    c.id_ciclista, eq.nombre\n" +
                "ORDER BY \n" +
                "    total_segundos ASC;";

        try (Connection conn = db.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                long totalSegundos = rs.getLong("total_segundos");
                Duration tiempoTotal = Duration.ofSeconds(totalSegundos);
                        clasifGenerals.add(new ClasifGeneral(
                                rs.getString("nombre_ciclista"),
                                rs.getString("nombre_equipo"),
                                tiempoTotal
                        ));
            }
        }catch (SQLException e ){
            throw new DataAccessException("Error al obtener clasificación general", e);
        }
        return clasifGenerals;
    }
}
