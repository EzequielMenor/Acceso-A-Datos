package com.ezequiel.tema02.boletin01.act6;

public class ClasificacionMontana {
    private String nombreCiclista;
    private String nombreEquipo;
    private long totalPuntos;

    public ClasificacionMontana(String nombreCiclista, String nombreEquipo, long totalPuntos) {
        this.nombreCiclista = nombreCiclista;
        this.nombreEquipo = nombreEquipo;
        this.totalPuntos = totalPuntos;
    }

    public void setNombreCiclista(String nombreCiclista) {
        this.nombreCiclista = nombreCiclista;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public void setTotalPuntos(long totalPuntos) {
        this.totalPuntos = totalPuntos;
    }

    public String getNombreCiclista() {
        return nombreCiclista;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public long getTotalPuntos() {
        return totalPuntos;
    }

    @Override
    public String toString() {
        return "ClasificacionMontana{" +
                "nombreCiclista='" + nombreCiclista + '\'' +
                ", nombreEquipo='" + nombreEquipo + '\'' +
                ", totalPuntos=" + totalPuntos +
                '}';
    }

}
