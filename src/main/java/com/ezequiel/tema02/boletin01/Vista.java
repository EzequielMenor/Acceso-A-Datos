package com.ezequiel.tema02.boletin01;

import java.util.Scanner;

public class Vista {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Controlador controlador = new Controlador();

        System.out.println("--- MENU SQL ---");
        System.out.println("1. Listar equipos");
        System.out.println("2. Listar ciclistas");
        System.out.print("Opción: ");
        int opcion = sc.nextInt();

        switch (opcion){
            case 1:
                controlador.listarEquipos();
                break;
            case 2:
                controlador.listarEquipos();
                System.out.print("Elige el ID un equipo para ver sus ciclistas(0 muestra todos los ciclistas): ");
                int idEquipo = sc.nextInt();
                if (idEquipo != 0){
                    controlador.ciclistasPorEquipo(idEquipo);
                } else {
                    controlador.listarCiclistas();
                }
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }
}
