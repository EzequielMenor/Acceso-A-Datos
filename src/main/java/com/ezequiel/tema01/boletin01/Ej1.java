package com.ezequiel.tema01.boletin01;

import java.io.File;

public class Ej1 {
    public static void main(String[] args) {
        File carpeta = new File("/Users/ezequielmenor/2DAM/AccesoDatos/tema01/ficheros");

        if (!carpeta.exists()) {
            System.out.println("La carpeta no existe");
        } else if (!carpeta.isDirectory()) {
            System.out.println("Existe, pero no es un directorio");
        } else {
            System.out.println("La carpeta existe y es un directorio");
        }

    }
}
