package com.ezequiel.tema01.boletin01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ej9 {
    public boolean compararArchivos(String archivo1, String archivo2){
        File f1 = new File(archivo1);
        File f2 = new File(archivo2);

        if (!f1.exists() || !f2.exists() || !f1.isFile() || !f2.isFile()){
            System.out.println("Uno de los archivos no existe o no es un archivo");
            return false;
        }

        if (f1.length() != f2.length()) {
            return false;
        }

        try(
            FileInputStream fis1 = new FileInputStream(f1);
            FileInputStream fis2 = new FileInputStream(f2)) {

            int b1, b2;
            while ((b1 = fis1.read()) != -1) {
                b2 = fis2.read();
                if (b1 != b2) {
                    return false;
                }
            }
            return true;

        }catch (IOException e) {
            System.out.println("Error al comparar los archivos" + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        Ej9 ej = new Ej9();
        Scanner sc = new Scanner(System.in);

        System.out.println("--Comparar Archivos--");
        System.out.print("Ruta del primer archivo: ");
        String ruta1 = sc.nextLine();
        System.out.print("Ruta del segundo archivo: ");
        String ruta2 = sc.nextLine();

        boolean iguales = ej.compararArchivos(ruta1, ruta2);
        if (iguales) {
            System.out.println("Los archivos son iguales.");
        } else {
            System.out.println("Los archivos son diferentes.");
        }

        sc.close();
    }

}
