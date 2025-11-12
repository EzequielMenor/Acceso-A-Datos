package com.ezequiel.tema02.boletin01.act9;

import java.time.Duration;

public class ClasifEquipo {
    private String nombreEquipo;
    private Duration tiempo_total;

    public ClasifEquipo(String nombreEquipo, Duration tiempo_total) {
        this.nombreEquipo = nombreEquipo;
        this.tiempo_total = tiempo_total;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public Duration getTiempo_total() {
        return tiempo_total;
    }
}
