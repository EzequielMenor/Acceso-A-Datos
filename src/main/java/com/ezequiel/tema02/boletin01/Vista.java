package com.ezequiel.tema02.boletin01;

import com.ezequiel.tema02.boletin01.act1.Equipo;
import com.ezequiel.tema02.boletin01.act1.EquipoDAO;
import com.ezequiel.tema02.boletin01.act1.EquipoDAOImpl;

import java.util.List;
import java.util.Scanner;

public class Vista {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ConexionBD Controller = new ConexionBD();

        System.out.println("--- MENU SQL ---");
        System.out.println("1. Listar equipos");
        System.out.println("2. Listar ciclistas");
        System.out.print("Opción: ");
        int opcion = sc.nextInt();

        switch (opcion){
            case 1:
                Controller.listarEquipos();
                break;
            case 2:
                Controller.listarEquipos();
                System.out.print("Elige el ID un equipo para ver sus ciclistas(0 muestra todos los ciclistas): ");
                int idEquipo = sc.nextInt();
                if (idEquipo != 0){
                    Controller.ciclistasPorEquipo(idEquipo);
                } else {
                    Controller.listarCiclistas();
                }
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }
}
