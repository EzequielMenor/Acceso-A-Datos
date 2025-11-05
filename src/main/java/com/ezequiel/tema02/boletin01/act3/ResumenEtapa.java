package com.ezequiel.tema02.boletin01.act3;

public class ResumenEtapa {
    private final String tipo;
    private final long cantidad;
    private final double KmTotales;

    public String getTipo() {
        return tipo;
    }

    public long getCantidad() {
        return cantidad;
    }

    public double getKmTotales() {
        return KmTotales;
    }

    public ResumenEtapa(String tipo, long cantidad, double kmTotales) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        KmTotales = kmTotales;
    }
}
