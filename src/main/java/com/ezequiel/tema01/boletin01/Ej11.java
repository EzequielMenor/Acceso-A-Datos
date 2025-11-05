package com.ezequiel.tema01.boletin01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ej11 {
    public void concatLines(String archivo1, String archivo2, String archivoDestino) {
        File f1 = new File(archivo1);
        File f2 = new File(archivo2);
        File fDest = new File(archivoDestino);

        if (!f1.exists() || !f1.isFile() || !f2.exists() || !f2.isFile()) {
            System.out.println("Uno de los archivos originales no existe o no es válido.");
            return;
        }

        try (Scanner sc1 = new Scanner(f1);
             Scanner sc2 = new Scanner(f2);
             FileWriter fw = new FileWriter(fDest)) {

            while (sc1.hasNextLine() || sc2.hasNextLine()) {
                String linea1 = sc1.hasNextLine() ? sc1.nextLine() : "";
                String linea2 = sc2.hasNextLine() ? sc2.nextLine() : "";

                // Escribimos la concatenación de las dos líneas
                fw.write(linea1 + linea2 + "\n");
            }

            System.out.println("Archivos concatenados línea a línea correctamente en: " + archivoDestino);

        } catch (IOException e) {
            System.out.println("Error al concatenar archivos línea a línea: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ej11 ej = new Ej11();

        System.out.println("--Concatenar Archivos Línea a Línea--");
        System.out.print("Ruta del primer archivo: ");
        String ruta1 = sc.nextLine();
        System.out.print("Ruta del segundo archivo: ");
        String ruta2 = sc.nextLine();
        System.out.print("Ruta del archivo destino: ");
        String rutaDestino = sc.nextLine();

        ej.concatLines(ruta1, ruta2, rutaDestino);
    }
}
