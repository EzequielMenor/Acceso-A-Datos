package com.ezequiel.tema02.boletin01;

import com.ezequiel.tema02.boletin01.act1.Equipo;
import com.ezequiel.tema02.boletin01.act1.EquipoDAO;
import com.ezequiel.tema02.boletin01.act1.EquipoDAOImpl;
import com.ezequiel.tema02.boletin01.act2.Ciclista;
import com.ezequiel.tema02.boletin01.act2.CiclistaDAO;
import com.ezequiel.tema02.boletin01.act2.CiclistaDAOImpl;

import java.util.List;

public class Controlador {
    private static final DB.Driver DB_DRIVER = DB.Driver.POSTGRESQL; // mysql
    private static final String DB_HOST = "localhost";
    private static final int DB_PORT = 5432; // 3306 para mysql
    private static final String DB_NAME = "postgres";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "test";

    private final DB db;
    private final EquipoDAO equipoDAO;
    private final CiclistaDAO ciclistaDAO;

    public Controlador(){
        this.db = new DB(DB_DRIVER, DB_HOST, DB_PORT, DB_NAME, DB_USERNAME,DB_PASSWORD);
        this.equipoDAO = new EquipoDAOImpl(db);
        this.ciclistaDAO = new CiclistaDAOImpl(db);
    }



    public void listarEquipos(){

        try {
            List<Equipo> equipos = equipoDAO.findAll();
            TextTable t = new TextTable("ID", "Nombre", "País");

            for (Equipo equipo : equipos) {
                t.addRow(
                        String.valueOf(equipo.getId_equipo()),
                        equipo.getNombre(),
                        equipo.getPais()
                );
            }
            System.out.println(t.toString());

        } catch (DataAccessException e){
            System.err.println(e.getMessage());
        }
    }

    public void ciclistasPorEquipo(int id_equipo){
        try{
            List<Ciclista> ciclistas = ciclistaDAO.findByIdEquipo(id_equipo);
            System.out.println("--- Ciclistas del equipo " + id_equipo + " ---");
            for (Ciclista ciclista : ciclistas){
                System.out.println("ID: " + ciclista.getId_ciclista() +
                        " | Nombre: " + ciclista.getNombre() +
                        " | País: " + ciclista.getPais() +
                        " | Fecha de Nacimiento: " + ciclista.getFecha_nac());
            }
        }catch (DataAccessException e){
            System.err.println(e.getMessage());
        }
    }

    public void listarCiclistas(){
        try{
            List<Ciclista> ciclistas = ciclistaDAO.findAll();
            System.out.println("--- Ciclistas Registrados ---");
            for (Ciclista ciclista : ciclistas) {
                System.out.println("ID: " + ciclista.getId_ciclista() +
                        " | Nombre: " + ciclista.getNombre() +
                        " | País: " + ciclista.getPais() +
                        " | Equipo: " + ciclista.getId_equipo() +
                        " | Fecha de Nacimiento: " + ciclista.getFecha_nac());
            }
            }catch (DataAccessException e){
            System.err.println(e.getMessage());
        }
    }
}