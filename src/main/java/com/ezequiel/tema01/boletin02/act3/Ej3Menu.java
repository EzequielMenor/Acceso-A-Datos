package com.ezequiel.tema01.boletin02.act3;

import java.util.Scanner;

public class Ej3Menu {
    public static void main(String[] args) throws Exception {
        try {
            Scanner sc = new Scanner(System.in);
            Controller3 controlador = new Controller3();

            System.out.println("**********************");
            System.out.println("1. Validar Acceso");
            System.out.println("**********************");
            System.out.print("Elige opción: ");
            int opcion = Integer.parseInt(sc.nextLine());

            if (opcion == 1) {
                System.out.print("Introduce la contraseña: ");
                String pass = sc.nextLine();

                if (controlador.validarAcceso(pass)) {
                    System.out.println("Acceso correcto.");

                    boolean salir = false;
                    while (!salir) {
                        System.out.println("**********************");
                        System.out.println("1. Modificar Contraseña");
                        System.out.println("2. Salir");
                        System.out.println("**********************");
                        System.out.print("Elige opción: ");
                        int op = Integer.parseInt(sc.nextLine());

                        switch (op) {
                            case 1:
                                System.out.print("Contraseña actual: ");
                                String actual = sc.nextLine();
                                System.out.print("Nueva contraseña: ");
                                String nueva = sc.nextLine();

                                if (controlador.cambiarContrasena(actual, nueva)) {
                                    System.out.println("Contraseña modificada correctamente.");
                                }else {
                                    System.out.println("Error al modificar la contraseña.");
                                }
                                break;
                            case 2:
                                salir = true;
                                break;
                            default:
                                System.out.println("Opción no válida.");
                        }
                    }

                } else {
                    System.out.println("Acceso denegado.");
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
