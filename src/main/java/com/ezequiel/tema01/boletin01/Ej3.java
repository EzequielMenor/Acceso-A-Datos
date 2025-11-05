package com.ezequiel.tema01.boletin01;

import java.io.File;

public class Ej3 {
    public static void main(String[] args) {
        File carpeta = new File("/Users/ezequielmenor/2DAM/AccesoDatos/tema01/ficheros");

        System.out.println("Nombre de la carpeta: " + carpeta.getName());
        System.out.println("Ruta absoluta: " + carpeta.getAbsolutePath());
        System.out.println("Se puede leer: " + carpeta.canRead());
        System.out.println("Se puede escribir: " + carpeta.canWrite());
    }
}
