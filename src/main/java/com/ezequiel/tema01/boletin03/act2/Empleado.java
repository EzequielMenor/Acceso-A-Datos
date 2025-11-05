package com.ezequiel.tema01.boletin03.act2;

public class Empleado {

    private String id;
    private String nombre;
    private double sueldo;

    public Empleado(String id, String nombre, double sueldo){
        this.id = id;
        this.nombre = nombre;
        this.sueldo = sueldo;
    }

    public String getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Nombre: " + nombre + "\n" +
                "Sueldo: " + sueldo;
    }
}
