package com.ezequiel.tema01.boletin01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ej8 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Debes pasar la ruta del archivo como parámetro.");
            return;
        }

        String rutaArchivo = args[0];
        File f = new File(rutaArchivo);

        if (!f.exists() || !f.isFile()) {
            System.out.println("El archivo no existe o no es un archivo: " + rutaArchivo);
            return;
        }

        try (Scanner sc = new Scanner(f)) {
            System.out.println("Contenido del archivo " + f.getName() + ":");
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
