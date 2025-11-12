package com.ezequiel.tema02.boletin01.act8;

import java.time.Duration;

public class ClasifGeneral {
    private String nombreCiclista;
    private String nombreEquipo;
    private Duration tiempoTotal;

    public ClasifGeneral(String nombreCiclista, String nombreEquipo, Duration tiempoTotal) {
        this.nombreCiclista = nombreCiclista;
        this.nombreEquipo = nombreEquipo;
        this.tiempoTotal = tiempoTotal;
    }

    public String getNombreCiclista() {
        return nombreCiclista;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public Duration getTiempoTotal() {
        return tiempoTotal;
    }

}
