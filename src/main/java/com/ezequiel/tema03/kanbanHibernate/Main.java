package com.ezequiel.tema03.kanbanHibernate;

import java.util.Scanner;
import com.ezequiel.tema03.kanbanHibernate.models.Usuario;
import com.ezequiel.tema03.kanbanHibernate.ui.AuthMenu;
import com.ezequiel.tema03.kanbanHibernate.ui.UserMenu;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Usuario usuarioLogueado = null;

    while (true) {
      if (usuarioLogueado == null) {
        // --- INVITADO ---
        AuthMenu authMenu = new AuthMenu(sc);
        usuarioLogueado = authMenu.mostrarMenu(); // Si login OK, devuelve usuario
      } else {
        // --- USUARIO LOGUEADO ---
        UserMenu userMenu = new UserMenu(sc, usuarioLogueado);
        boolean logout = userMenu.mostrarMenu();
        if (logout) {
          usuarioLogueado = null;
          System.out.println("👋 Sesión cerrada.");
        }
      }
    }
  }
}