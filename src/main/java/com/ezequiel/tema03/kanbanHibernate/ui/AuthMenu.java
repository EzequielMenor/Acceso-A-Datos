package com.ezequiel.tema03.kanbanHibernate.ui;

import java.util.Scanner;
import com.ezequiel.tema03.kanbanHibernate.models.Usuario;
import com.ezequiel.tema03.kanbanHibernate.service.AuthService;

public class AuthMenu {
  private AuthService authService;
  private Scanner sc;

  public AuthMenu(Scanner sc) {
    this.sc = sc;
    this.authService = new AuthService();
  }

  public Usuario mostrarMenu() {
    System.out.println("\n--- MENU INVITADO ---");
    System.out.println("1. Login");
    System.out.println("2. Registrarse");
    System.out.println("3. Salir");
    System.out.print("Elige opción: ");
    int op = sc.nextInt();
    sc.nextLine();

    switch (op) {
      case 1:
        return login();
      case 2:
        registrar();
        return null;
      case 3:
        System.exit(0);
        return null;
      default:
        return null;
    }
  }

  private Usuario login() {
    System.out.print("Email: ");
    String email = sc.nextLine();
    System.out.print("Pass: ");
    String pass = sc.nextLine();
    try {
      return authService.login(email, pass);
    } catch (Exception e) {
      System.out.println("❌ Error: " + e.getMessage());
      return null;
    }
  }

  private void registrar() {
    System.out.print("Email: ");
    String email = sc.nextLine();
    System.out.print("Pass: ");
    String pass = sc.nextLine();
    try {
      authService.registrar(email, pass);
      System.out.println("✅ Registrado OK");
    } catch (Exception e) {
      System.out.println("❌ Error: " + e.getMessage());
    }
  }
}