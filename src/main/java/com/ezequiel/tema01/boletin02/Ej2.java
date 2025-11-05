package com.ezequiel.tema01.boletin02;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ej2 {
    public static class Alumno {
        private String nia;
        private String nombre;
        private String apellido1;
        private String apellido2;
        private String fechaNacimiento;

        public Alumno(String nia, String nombre, String apellido1, String apellido2, String fechaNacimiento){
            this.nia = nia;
            this.nombre = nombre;
            this.apellido1 = apellido1;
            this.apellido2 = apellido2;
            this.fechaNacimiento = fechaNacimiento;
        }

        public String getNia() {return nia;}
        public String getNombre() {return nombre;}
        public String getApellido1() {return apellido1;}
        public String getApellido2() {return apellido2;}
        public String getFechaNacimiento() {return fechaNacimiento;}

        public String toString() {
            return nia + ", " + nombre + ", " + apellido1 + ", " + apellido2 + ", " + fechaNacimiento;
        }
    }

    public static class gestionAlumnos {
        private File archivo;

        public gestionAlumnos(String ruta) {
            archivo = new File(ruta);
            try {
                if (!archivo.exists()) archivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creando archivo, " + e.getMessage());
            }
        }

        public void insertarAlumno(Alumno[] alumnos) {
            try (FileWriter fw = new FileWriter(archivo, true)) {
                for (Alumno a : alumnos) {
                    fw.write(a.toString() + "\n");
                }
                System.out.println("Alumnos insertados correctamente");
            } catch (IOException e) {
                System.out.println("Error al insertar: " + e.getMessage());
            }
        }

        public void eliminarAlumno(Alumno alumno) {
            List<String> lineas = new ArrayList<>();
            try (Scanner sc = new Scanner(archivo)) {
                while (sc.hasNextLine()) {
                    String linea = sc.nextLine();
                    if (!linea.equals(alumno.toString())) {
                        lineas.add(linea);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al leer: " + e.getMessage());
            }

            try (FileWriter fw = new FileWriter(archivo)) {
                for (String l : lineas) fw.write(l + "\n");
                System.out.println("Alumno eliminado");
            } catch (IOException e) {
                System.out.println("Error al escribir: " + e.getMessage());
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            try (Scanner sc = new Scanner(archivo)) {
                while (sc.hasNextLine()) {
                    sb.append(sc.nextLine()).append("\n");
                }
            } catch (Exception e) {
                sb.append("Error al leer: ").append(e.getMessage());
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        String rutaArchivos = "/Users/ezequielmenor/2DAM/AccesoDatos/tema01/ficheros/alumnos/alumnos.txt";
        gestionAlumnos ga = new gestionAlumnos(rutaArchivos);

        Alumno a1 = new Alumno("001", "Ana", "López", "Pérez", "2005-03-12");
        Alumno a2 = new Alumno("002", "Juan", "García", "López", "2005-09-23");

        ga.insertarAlumno(new Alumno[]{a1, a2});

        System.out.println("Contenido actual:");
        System.out.println(ga);

        ga.eliminarAlumno(a1);

        System.out.println("Tras eliminar a1");
        System.out.println(ga);

    }
}
