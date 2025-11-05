package com.ezequiel.tema01.boletin01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Ej6 {
    public static class GestionArchivo{
        public boolean crearArchivo(String directorio, String archivo){
            File dir = new File(directorio);
            if (!dir.exists()) {
                // Crea todos los directorios necesarios
                if (!dir.mkdirs()) {
                    System.out.println("No se pudo crear el directorio: " + directorio);
                    return false;
                }
            }

            File f = new File(dir, archivo);
            try {
                if (f.createNewFile()) {
                    return true;
                } else {
                    return false; // ya existía
                }
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + e.getMessage());
                return false;
            }
        }


        public void listarDirectorio(String directorio){
            File dir = new File(directorio);

            if (!dir.exists()){
                System.out.println("El directorio no existe");
                return;
            }

            File[] lista = dir.listFiles();
            if (lista != null){
                for (File f : lista){
                    String tipo = f.isDirectory() ? "d" : "f";
                    String lectura = f.canRead() ? "r" : "-";
                    String escritura = f.canWrite() ? "w" : "-";

                    System.out.println(f.getName() + " " + tipo + " " + f.length() + " bytes " + lectura + escritura);

                }
            }
        }

        public void verInfo(String directorio, String archivo) {
            File f = new File(directorio, archivo);

            if (!f.exists()) {
                System.out.println("El archivo no existe");
                return;
            }

            System.out.println("Nombre: " + f.getName());
            System.out.println("Ruta absoluta: " + f.getAbsolutePath());
            System.out.println("Se puede leer: " + f.canRead());
            System.out.println("Se puede escribir: " + f.canWrite());
            System.out.println("Tamaño: " + f.length() + " bytes");
            System.out.println("Es directorio: " + f.isDirectory());
            System.out.println("Es archivo: " + f.isFile());
        }

        public void leerArchivo(String directorio, String archivo) {
            File f = new File(directorio, archivo);

            if (!f.exists() || !f.isFile()) {
                System.out.println("El archivo no existe o no es un archivo: " + archivo);
                return;
            }

            try {
                Scanner sc = new Scanner(f); // Scanner para leer el archivo
                System.out.println("Contenido del archivo " + archivo + ":");
                while (sc.hasNextLine()) {
                    String linea = sc.nextLine();
                    System.out.println(linea); // Imprime cada línea
                }
                sc.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
        }
    }



    public static void main (String[] args) {
        GestionArchivo ga = new GestionArchivo();
        Scanner sc = new Scanner(System.in);

        System.out.println("--Crear Archivo--");
        System.out.print("Directorio: ");
        String dirCrear = sc.nextLine();
        System.out.print("Nombre del archivo: ");
        String archCrear = sc.nextLine();

        boolean creado = ga.crearArchivo(dirCrear, archCrear);
        if (creado){
            System.out.println("Archivo creado correctamente");
        }else {
            System.out.println("Error al crear el archivo");
        }

        System.out.println("--Listar Directorio--");
        System.out.print("Directorio: ");
        String dirListar = sc.nextLine();
        ga.listarDirectorio(dirListar);

        System.out.println("--Ver Información--");
        System.out.print("Directorio: ");
        String dirInfo = sc.nextLine();
        System.out.print("Nombre del archivo: ");
        String archInfo = sc.nextLine();
        ga.verInfo(dirInfo, archInfo);

        System.out.println("--Leer Archivo--");
        System.out.print("Directorio: ");
        String dirLeer = sc.nextLine();
        System.out.print("Nombre del archivo: ");
        String archLeer = sc.nextLine();
        ga.leerArchivo(dirLeer, archLeer);


    }
}
