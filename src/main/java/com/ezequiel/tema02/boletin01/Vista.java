package com.ezequiel.tema02.boletin01;

import java.util.Scanner;

public class Vista {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Controlador controlador = new Controlador();
        int opcion;
        do {


            System.out.println("--- MENU SQL ---");
            System.out.println("1. Listar equipos");
            System.out.println("2. Listar ciclistas");
            System.out.println("3. Listar etapas");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                case 1:
                    System.out.println("--- Equipos Registrados ---");
                    controlador.listarEquipos();
                    break;
                case 2:
                    System.out.println("--- Equipos Registrados ---");
                    controlador.listarEquipos();
                    System.out.print("Elige el ID un equipo para ver sus ciclistas(0 muestra todos los ciclistas): ");
                    int idEquipo = sc.nextInt();
                    if (idEquipo != 0) {
                        System.out.println("--- Ciclistas del equipo " + idEquipo + " ---");
                        controlador.ciclistasPorEquipo(idEquipo);
                    } else {
                        System.out.println("--- Ciclistas Registrados ---");
                        controlador.listarCiclistas();
                    }
                    break;
                case 3:
                    System.out.println("--- Etapas Regitradas ---");
                    controlador.listarEtapas();
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (opcion != 0);

    }
}
