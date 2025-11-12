package com.ezequiel.tema02.boletin01;

import com.ezequiel.tema02.boletin01.act1.Equipo;
import com.ezequiel.tema02.boletin01.act1.EquipoDAO;
import com.ezequiel.tema02.boletin01.act1.EquipoDAOImpl;
import com.ezequiel.tema02.boletin01.act2.Ciclista;
import com.ezequiel.tema02.boletin01.act2.CiclistaDAO;
import com.ezequiel.tema02.boletin01.act2.CiclistaDAOImpl;
import com.ezequiel.tema02.boletin01.act3.Etapa;
import com.ezequiel.tema02.boletin01.act3.EtapaDAO;
import com.ezequiel.tema02.boletin01.act3.EtapaDAOImpl;
import com.ezequiel.tema02.boletin01.act3.ResumenEtapa;
import com.ezequiel.tema02.boletin01.act5.ClasifEtapa;
import com.ezequiel.tema02.boletin01.act6.ClasificacionMontana;
import com.ezequiel.tema02.boletin01.act7.ClasificacionRegularidad;
import com.ezequiel.tema02.boletin01.act8.ClasifGeneral;
import com.ezequiel.tema02.boletin01.act9.ClasifEquipo;

import java.time.Duration;
import java.util.List;

public class Controllable {
    private static final DB.Driver DB_DRIVER = DB.Driver.POSTGRESQL; // mysql
    private static final String DB_HOST = "localhost";
    private static final int DB_PORT = 5432; // 3306 para mysql
    private static final String DB_NAME = "postgres";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "test";

    private final EquipoDAO equipoDAO;
    private final CiclistaDAO ciclistaDAO;
    private final EtapaDAO etapaDAO;


    public Controllable(){
        DB db = new DB(DB_DRIVER, DB_HOST, DB_PORT, DB_NAME, DB_USERNAME, DB_PASSWORD);
        this.equipoDAO = new EquipoDAOImpl(db);
        this.ciclistaDAO = new CiclistaDAOImpl(db);
        this.etapaDAO = new EtapaDAOImpl(db);
    }


    /**
     * EJERCICIO 1: Listar equipos
     * También es un 'helper' (ayudante) para los ejercicios 2 y 4.
     */
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

    /**
     * EJERCICIO 2: Ciclistas por equipo (Parte 1)
     * Este metodo se usa si el usuario elige un ID de equipo.
     * También es un 'helper' (ayudante) para el ejercicio 4.
     * @param id_equipo
     */
    public void ciclistasPorEquipo(int id_equipo){
        try{
            List<Ciclista> ciclistas = ciclistaDAO.findByIdEquipo(id_equipo);
            TextTable t = new TextTable("ID", "Nombre", "Pais", "Fecha de nacimiento");
            for (Ciclista ciclista : ciclistas){
                t.addRow(
                        String.valueOf(ciclista.getId_ciclista()),
                        ciclista.getNombre(),
                        ciclista.getPais(),
                        ciclista.getFecha_nac().toString()
                );
            }

            System.out.println(t.toString());
        }catch (DataAccessException e){
            System.err.println(e.getMessage());
        }
    }


    /**
     * EJERCICIO 2: Ciclistas por equipo (Parte 2)
     * Este metodo se usa si el usuario elige '0' (todos los ciclistas).
     */
    public void listarCiclistas(){
        try{
            List<Ciclista> ciclistas = ciclistaDAO.findAll();
            TextTable t = new TextTable("ID", "Nombre", "País", "Equipo", "Fecha de Nacimiento");
            for (Ciclista ciclista : ciclistas) {
                t.addRow(
                        String.valueOf(ciclista.getId_ciclista()),
                        ciclista.getNombre(),
                        ciclista.getPais(),
                        String.valueOf(ciclista.getId_equipo()),
                        ciclista.getFecha_nac().toString()
                );
            }
            System.out.println(t.toString());
            }catch (DataAccessException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * EJERCICIO 3: Listar etapas (Tarea b)
     * Muestra la lista de etapas.
     * También es un 'helper' (ayudante) para el ejercicio 5.
     */
    public void listarEtapas(){
        try{
            List<Etapa> etapas = etapaDAO.findAll();
            TextTable t = new TextTable("ID", "Tipo", "Fecha", "Salida", "Llegada", "KM");
            for (Etapa etapa : etapas){
                t.addRow(
                        String.valueOf(etapa.getId_etapa()),
                        etapa.getTipo(),
                        etapa.getFecha().toString(),
                        etapa.getSalida(),
                        etapa.getLlegada(),
                        String.valueOf(etapa.getKm())
                );
            }
            System.out.println(t.toString());
        }catch (DataAccessException e){
            System.err.println(e.getMessage());
        }

    }


    /**
     * EJERCICIO 3: Resumen de etapas (Tarea c)
     * Muestra el resumen de KM y cantidad por tipo de etapa.
     */
    public void listarEtapasResuimdas(){
        try {
            List<ResumenEtapa> etapasResumidas = etapaDAO.getResumenPorTipo();
            TextTable t = new TextTable("Tipo", "Cantidad", "KM Totales");
            for (ResumenEtapa resumenEtapa : etapasResumidas){
                t.addRow(
                        resumenEtapa.getTipo(),
                        String.valueOf(resumenEtapa.getCantidad()),
                        String.valueOf(resumenEtapa.getKmTotales())
                );
            }
            System.out.println(t.toString());
        }catch (DataAccessException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * EJERCICIO 4: Velocidad media de un ciclista (Tarea c)
     * Recibe el ID del ciclista (elegido en la Vista) y muestra el cálculo.
     * @param idCiclista
     */
    public void mostrarVelocidadMedia(int idCiclista){
        try {
            double velocidad = ciclistaDAO.getVelocidadMedia(idCiclista);
            TextTable t = new TextTable("ID Ciclista", "Velocidad Media");
            String velocidadFormateada = String.format("%.2f km/h", velocidad);
            t.addRow(
                    String.valueOf(idCiclista),
                    velocidadFormateada
            );
            System.out.println(t.toString());
        }catch (DataAccessException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * EJERCICIO 5: Clasificación de una etapa (Tarea c)
     * Recibe el ID de la etapa (elegida en la Vista) y muestra la clasificación.
     * @param idEtapa
     */
    public void mostrarClasificacionEtapa(int idEtapa){
        try{
            List<ClasifEtapa> clasifEtapas = etapaDAO.getClasifEtapa(idEtapa);
            TextTable t = new TextTable("Posicion", "Nombre Ciclista", "Nombre Equipo", "Tiempo");
            for (ClasifEtapa clasifEtapa : clasifEtapas){
                t.addRow(
                        String.valueOf(clasifEtapa.getPosicion()),
                        clasifEtapa.getNombreCiclista(),
                        clasifEtapa.getNombreEquipo(),
                        formatDuration(clasifEtapa.getTiempo())
                );
            }
            System.out.println(t.toString());
        }catch (DataAccessException e){
            System.err.println(e.getMessage());
        }

    }

    /**
     * -- HELPER (Ayudante) para formatear Duration a HH:MM:SS ---
     * Usado por los ejercicios 5, 8 y 9.
     * @param duration
     * @return
     */
    private String formatDuration(Duration duration) {
        long segundosTotales = duration.getSeconds();
        long horas = segundosTotales / 3600;
        long minutos = (segundosTotales % 3600) / 60;
        long segundos = segundosTotales % 60;

        // Formato %02d (dos dígitos, con cero delante si es necesario)
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }

    /**
     * EJERCICIO 6: Clasificación de la montaña (Tarea b)
     */
    public void mostrarClasifMontanas() {
        try {
            List<ClasificacionMontana> clasificacionMontanas = ciclistaDAO.getClasificacionMontana();
            TextTable t = new TextTable("Pos", "Ciclista", "Equipo", "Puntos");
            int pos = 1;
            for (ClasificacionMontana clasificacionMontana : clasificacionMontanas) {
                t.addRow(
                        String.valueOf(pos++),
                        clasificacionMontana.getNombreCiclista(),
                        clasificacionMontana.getNombreEquipo(),
                        String.valueOf(clasificacionMontana.getTotalPuntos())
                );
            }
            System.out.println(t.toString());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * EJERCICIO 7: Clasificación de la regularidad (Tarea b)
     */
    public void mostrarClasifRegularidad() {
        try {
            List<ClasificacionRegularidad> clasificacionRegularidads = ciclistaDAO.getClasifRegularidad();
            TextTable t = new TextTable("Pos", "Ciclista", "Equipo", "Puntos");
            int pos = 1;
            for (ClasificacionRegularidad clasificacionRegularidad : clasificacionRegularidads) {
                t.addRow(
                        String.valueOf(pos++),
                        clasificacionRegularidad.getNombreCiclista(),
                        clasificacionRegularidad.getNombreEquipo(),
                        String.valueOf(clasificacionRegularidad.getTotalPuntos())
                );
            }
            System.out.println(t.toString());
        }catch (DataAccessException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * EJERCICIO 8: Clasificación general (Tarea b)
     */
    public void mostrarClasifGeneral(){
        try {
            List<ClasifGeneral> clasifGenerals = ciclistaDAO.getClasifGeneral();
            TextTable t = new TextTable("Pos", "Ciclista", "Equipo", "Tiempo Total");
            int pos = 1;
            for (ClasifGeneral clasifGeneral : clasifGenerals) {
                t.addRow(
                        String.valueOf(pos++),
                        clasifGeneral.getNombreCiclista(),
                        clasifGeneral.getNombreEquipo(),
                        formatDuration(clasifGeneral.getTiempoTotal())
                );
            }
            System.out.println(t.toString());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * EJERCICIO 9: Clasificación general por equipos (Tarea b)
     */
    public void mostrarClasifPorEquipos(){
        try {
            List<ClasifEquipo> clasifEquipos = equipoDAO.getClasifEquipo();
            TextTable t = new TextTable("Pos", "Equipo", "Tiempo Total");
            int pos = 1;
            for (ClasifEquipo clasifEquipo : clasifEquipos) {
                t.addRow(
                        String.valueOf(pos++),
                        clasifEquipo.getNombreEquipo(),
                        formatDuration(clasifEquipo.getTiempo_total())
                );
            }
            System.out.println(t.toString());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

}