package com.ezequiel.tema01.boletin01;

import java.io.File;
import java.time.LocalDateTime;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Ej4 {
    public static void main(String[] args) {
        File archivo = new File("/Users/ezequielmenor/2DAM/AccesoDatos/tema01/ficheros/docs/archivo1.txt");

        if (!archivo.exists() || !archivo.isFile()) {
            System.out.println("El archivo no existe o no es un archivo");
            return;
        }

        System.out.println("Nombre: " + archivo.getName());
        System.out.println("Ruta absoluta: " + archivo.getAbsolutePath());

        if (archivo.isHidden()) {
            System.out.println("Archivo ocutlo" );
        }else System.out.println("Archivo no oculto");

        System.out.println("Se puede leer: " + archivo.canRead());

        System.out.println("Se puede escribir: " + archivo.canWrite());

        long ultimaMod = archivo.lastModified();
        LocalDateTime fecha = LocalDateTime.ofInstant(Instant.ofEpochMilli(ultimaMod), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("Fecha de última modificación: " + fecha.format(formatter));

        long bytes = archivo.length();
        System.out.println("Tamaño: " + bytes + " bytes");

        double kb = bytes / 1024.0;
        System.out.println("Tamaño: " + kb + " KB");

        double mb = kb / 1024.0;
        System.out.println("Tamaño: " + mb + " MB");


    }
}
