package com.ezequiel.tema02.boletin01.act5;

import java.time.Duration;

public class ClasifEtapa {
    private final int posicion;
    private final String nombreCiclista;
    private final String nombreEquipo;
    private final Duration tiempo;

    public ClasifEtapa(int posicion, String nombreCiclista, String nombreEquipo, Duration tiempo) {
        this.posicion = posicion;
        this.nombreCiclista = nombreCiclista;
        this.nombreEquipo = nombreEquipo;
        this.tiempo = tiempo;
    }

    public int getPosicion() {
        return posicion;
    }

    public String getNombreCiclista() {
        return nombreCiclista;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public Duration getTiempo() {
        return tiempo;
    }
}
