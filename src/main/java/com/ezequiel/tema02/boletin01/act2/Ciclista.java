package com.ezequiel.tema02.boletin01.act2;

import java.time.LocalDate;

public class Ciclista {
    private final int id_ciclista;
    private final int id_equipo;
    private final String nombre;
    private final String pais;
    private final LocalDate fecha_nac;

    public Ciclista(int id_ciclista, int id_equipo, String nombre, String pais, LocalDate fecha_nac) {
        this.id_ciclista = id_ciclista;
        this.id_equipo = id_equipo;
        this.nombre = nombre;
        this.pais = pais;
        this.fecha_nac = fecha_nac;
    }

    public int getId_ciclista() {
        return id_ciclista;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public LocalDate getFecha_nac() {
        return fecha_nac;
    }
}
