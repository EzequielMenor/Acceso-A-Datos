package com.ezequiel.tema01.boletin02;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ej1 {
    public void procesarDNI(String archivoOrigen){
        File fOrigen = new File(archivoOrigen);

        if (!fOrigen.exists() || !fOrigen.isFile()){
            System.out.println("El archivo no existe o no es un fichero");
            return;
        }

        String nombre = fOrigen.getName();
        int punto = nombre.lastIndexOf(".");
        String nombreSinExt = (punto == -1) ? nombre : nombre.substring(0, punto);
        String extension = (punto == -1) ? "" : nombre.substring(punto);
        File fDestino = new File(fOrigen.getParent(), nombreSinExt + "_conLetras" + extension);

        char[] letras = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J',
                'Z','S','Q','V','H','L','C','K','E'};

        try (Scanner sc = new Scanner(fOrigen);
             FileWriter fw = new FileWriter(fDestino)){


            while (sc.hasNextLine()) {
                String dni = sc.nextLine().trim();

                if (dni.length() < 8){
                    dni = String.format("%08d", Long.parseLong(dni));
                }

                int numero = Integer.parseInt(dni);
                char letra = letras[numero % 23];

                fw.write(dni + letra + "\n");
            }

            System.out.println("Archivo generado correctamente en: " + fDestino.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error procesando el archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ej1 ga = new Ej1();

        System.out.println("Introduce la ruta del archvio con los DNI");
        String ruta = sc.nextLine();

        ga.procesarDNI(ruta);
    }
}
