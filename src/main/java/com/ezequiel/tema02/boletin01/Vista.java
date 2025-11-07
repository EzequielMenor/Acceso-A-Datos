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
            System.out.println("4. Velocidad media de un ciclista");
            System.out.println("5. Clasificación etapa");
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
                    controlador.listarEtapasResuimdas();
                    break;

                case 4:
                    System.out.println("--- Listado equipos ---");
                    controlador.listarEquipos();
                    System.out.print("Elige el id del equipo al que pertenece el ciclista: ");
                    int idEquipoCiclista = sc.nextInt();
                    System.out.println("Listado ciclistas");
                    controlador.ciclistasPorEquipo(idEquipoCiclista);
                    System.out.print("Elige el id del ciclista: ");
                    int idCiclista = sc.nextInt();
                    controlador.mostrarVelocidadMedia(idCiclista);
                    break;

                case 5:
                    System.out.println("--- Listado Etapas ---");
                    controlador.listarEtapas();
                    System.out.print("Elige el ID de una etapa: ");
                    int id_etapa = sc.nextInt();
                    System.out.println("--- Clasificación etapa " + id_etapa + " ---");
                    controlador.mostrarClasificacionEtapa(id_etapa);
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (opcion != 0);

    }
}
