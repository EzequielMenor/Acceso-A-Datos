package com.ezequiel.tema02.boletin01.act1;

public class Equipo {
    private final int id_equipo;
    private final String nombre;
    private final String pais;

    public Equipo(int id_equipo, String nombre, String pais) {
        this.id_equipo = id_equipo;
        this.nombre = nombre;
        this.pais = pais;
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
}
