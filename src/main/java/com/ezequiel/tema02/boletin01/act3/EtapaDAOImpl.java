package com.ezequiel.tema02.boletin01.act3;

import com.ezequiel.tema02.boletin01.DB;
import com.ezequiel.tema02.boletin01.DataAccessException;
import com.ezequiel.tema02.boletin01.TextTable;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EtapaDAOImpl implements EtapaDAO{
    private final DB db;

    public EtapaDAOImpl(DB db) {
        this.db = db;
    }

    @Override
    public List<ResumenEtapa> getResumenPorTipo() {
        String sql = "SELECT tipo, COUNT(*) AS cantidad, SUM(distancia_km) AS kms_totales FROM etapas GROUP BY tipo ORDER BY tipo;";
        List<ResumenEtapa> etapasResumidas = new ArrayList<>();
        try(Connection conn = db.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery()){

            while (rs.next()){
                etapasResumidas.add(new ResumenEtapa(
                        rs.getString("tipo"),
                        rs.getLong("cantidad"),
                        rs.getDouble("kms_totales")
                ));
            }

        } catch (SQLException e) {
            throw new DataAccessException("Error al resumir etapas por tipo", e);
        }
        return etapasResumidas;
    }

    @Override
    public List<Etapa> findAll(){
        String sql = "SELECT id_etapa, fecha, salida, llegada, distancia_km, tipo FROM etapas;";
        List<Etapa> etapas = new ArrayList<>();
        try(Connection conn = db.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery()){
            while (rs.next()){
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                etapas.add(new Etapa(
                        rs.getInt("id_etapa"),
                        rs.getString("tipo"),
                        fecha,
                        rs.getString("salida"),
                        rs.getString("llegada"),
                        rs.getDouble("distancia_km")
                ));
            }
        }catch (SQLException e){
            throw new DataAccessException("Error al buscar etapas", e);
        }
        return etapas;
    }
}
