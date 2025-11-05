package com.ezequiel.tema01.boletin02.act4;

import java.util.Scanner;

public class Menu4 {
    public static void main(String[] args) throws Exception{
        try (Scanner sc = new Scanner(System.in)){
            Controller4 controlador = new Controller4();

            System.out.println("**********************");
            System.out.println("1. Validar Acceso");
            System.out.println("2. Registrar Usuario");
            System.out.println("**********************");
            System.out.print("Elige opción: ");
            int opcion = Integer.parseInt(sc.nextLine());

            if (opcion == 1){
                System.out.print("Introduce el usuario: ");
                String nombre = sc.nextLine();
                System.out.print("Introduce la contraseña: ");
                String passwd = sc.nextLine();

                if (controlador.validarAcceso(nombre, passwd)){
                    System.out.println("Acceso correcto para " + controlador.getUsuarioActual().getNombre());

                    boolean salir = false;
                    while (!salir){
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

                                if (controlador.cambiarPasswd(actual, nueva)){
                                    System.out.println("Contraseña modificada correctamente.");
                                }else {
                                    System.out.println("Error al modificar la contraseña.");
                                }
                                break;
                            case 2:
                                salir = true;
                                System.out.println("Sesion finalizada. Hasta pronto!");
                                break;
                            default:
                                System.out.println("Opción no válida.");
                        }
                    }
                } else {
                    System.out.println("Acceso denegado. Usuario o contraseña incorrectos");
                }
            } else if (opcion == 2){
                System.out.print("Introduce el nuevo usuario: ");
                String newUser = sc.nextLine();
                System.out.print("Introduce la contraseña (>8 caracteres, Mayús, minús, número, símbolo): ");
                String newPasswd = sc.nextLine();

                if(controlador.resgistrarUsuario(newUser, newPasswd)){
                    System.out.println("Usuario registrado correctamente");
                } else {
                    System.out.println("Error al registrar usuario");
                }
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
