package com.ezequiel.tema01.boletin01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ej10 {
    public void concat(String archivo1, String archivo2, String archivoDestino) {
        File f1 = new File(archivo1);
        File f2 = new File(archivo2);
        File fDest = new File(archivoDestino);

        if (!f1.exists() || !f1.isFile() || !f2.exists() || !f2.isFile()) {
            System.out.println("Uno de los archivos originales no existe o no es válido.");
            return;
        }

        try (FileInputStream fis1 = new FileInputStream(f1);
             FileInputStream fis2 = new FileInputStream(f2);
             FileOutputStream fos = new FileOutputStream(fDest)) {

            int byteLeido;

            // Copiar archivo1 al destino
            while ((byteLeido = fis1.read()) != -1) {
                fos.write(byteLeido);
            }

            // Copiar archivo2 al destino
            while ((byteLeido = fis2.read()) != -1) {
                fos.write(byteLeido);
            }

            System.out.println("Archivos concatenados correctamente en: " + archivoDestino);

        } catch (IOException e) {
            System.out.println("Error al concatenar archivos: " + e.getMessage());
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Ej10 ej = new Ej10();

        System.out.println("--Concatenar Archivos--");
        System.out.print("Ruta del primer archivo: ");
        String ruta1 = sc.nextLine();
        System.out.print("Ruta del segundo archivo: ");
        String ruta2 = sc.nextLine();
        System.out.print("Ruta del archivo destino: ");
        String rutaDestino = sc.nextLine();

        ej.concat(ruta1, ruta2, rutaDestino);

    }
}
