package com.ezequiel.tema02.boletin01.act3;

import java.time.LocalDate;

public class Etapa {
    private final int id_etapa;
    private final String tipo;
    private final LocalDate fecha;
    private final String salida;
    private final String llegada;
    private final double km;

    public Etapa(int id_etapa, String tipo, LocalDate fecha, String salida, String llegada, double km) {
        this.id_etapa = id_etapa;
        this.tipo = tipo;
        this.fecha = fecha;
        this.salida = salida;
        this.llegada = llegada;
        this.km = km;
    }

    public int getId_etapa() {
        return id_etapa;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getSalida() {
        return salida;
    }

    public String getLlegada() {
        return llegada;
    }

    public double getKm() {
        return km;
    }
}
