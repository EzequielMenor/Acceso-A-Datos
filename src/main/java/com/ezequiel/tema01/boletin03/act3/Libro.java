package com.ezequiel.tema01.boletin03.act3;

import java.util.List;

public class Libro {
    private String titulo;
    private List<String> genero;

    public Libro(String titulo, List<String> genero){
        this.titulo = titulo;
        this.genero = genero;
    }


    public String getTitulo() {
        return titulo;
    }
    public List<String> getGeneros() {
        return genero;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setGenero(List<String> genero) {
        this.genero = genero;
    }


    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", genero=" + genero +
                '}';
    }
}
