package com.ezequiel.tema01.boletin01;

import java.io.File;

public class Ej2 {
    public static void main(String[] args) {
        File carpeta = new File("/Users/ezequielmenor/2DAM/AccesoDatos/tema01/ficheros");

        File[] lista = carpeta.listFiles();

        for (File f : lista){
            if (f.isDirectory()) {
                System.out.println("Directorio: " + f.getName());
            } else {
                System.out.println("Archivo: " + f.getName());
            }
        }

    }
}
