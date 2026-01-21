package com.ezequiel.tema03.kanbanHibernate.ui;

import java.util.Scanner;

import com.ezequiel.tema03.kanbanHibernate.TextTable;
import com.ezequiel.tema03.kanbanHibernate.models.*;
import com.ezequiel.tema03.kanbanHibernate.service.*;

public class UserMenu {
  private Scanner sc;
  private Usuario usuario;
  private TableroService tableroService;
  private TarjetaService tarjetaService;
  private EtiquetaService etiquetaService;

  public UserMenu(Scanner sc, Usuario usuario) {
    this.sc = sc;
    this.usuario = usuario;
    this.tableroService = new TableroService();
    this.tarjetaService = new TarjetaService();
    this.etiquetaService = new EtiquetaService();
  }

  public boolean mostrarMenu() {
    System.out.println("\n--- KANBAN DE " + usuario.getEmail() + " ---");
    System.out.println("1. Crear nuevo tablero");
    System.out.println("2. Ver mis tableros");
    System.out.println("3. Buscar Tarjetas por Etiqueta");
    System.out.println("4. Cerrar Sesión (Logout)");
    System.out.println("5. Salir programa");
    System.out.print("Elige una opción: ");

    int opcion = sc.nextInt();
    sc.nextLine();

    switch (opcion) {
      case 1:
        crearTablero();
        break;
      case 2:
        verTableros();
        break;
      case 3:
        buscarPorEtiqueta();
        break;
      case 4:
        return true; // Logout
      case 5:
        System.exit(0);
        break;
      default:
        System.out.println("Opción no válida.");
    }
    return false; // No logout
  }

  private void crearTablero() {
    System.out.print("Nombre del nuevo tablero: ");
    String nombre = sc.nextLine();
    tableroService.crearTablero(nombre, usuario);
    System.out.println("✅ Tablero creado.");
  }

  private void verTableros() {
    var misTableros = tableroService.obtenerTablerosDeUsuario(usuario);
    if (misTableros == null || misTableros.isEmpty()) {
      System.out.println("📭 No tienes tableros.");
      return;
    }

    System.out.println("\n--- GESTIÓN DE TABLEROS ---");
    TextTable table = new TextTable("ID", "NOMBRE");
    for (var t : misTableros) {
      table.addRow(String.valueOf(t.getId()), t.getNombre());
    }
    System.out.println(table.toString());

    System.out.println("Acciones:");
    System.out.println("1. Entrar en un tablero");
    System.out.println("2. Eliminar un tablero");
    System.out.println("3. Volver");
    System.out.print("Elige: ");
    int op = sc.nextInt();
    sc.nextLine();

    switch (op) {
      case 1:
        System.out.print("ID del tablero: ");
        int id = sc.nextInt();
        sc.nextLine();
        gestionarTablero(id);
        break;
      case 2:
        System.out.print("ID del tablero a BORRAR (¡Cuidado!): ");
        int idBorr = sc.nextInt();
        sc.nextLine();
        Tablero tBorr = tableroService.obtenerTableroPorId(idBorr);
        if (tBorr != null && tBorr.getUsuario().getId() == usuario.getId()) {
          tableroService.eliminarTablero(idBorr);
          System.out.println("🗑️ Tablero eliminado.");
        } else {
          System.out.println("❌ No encontrado o sin permiso.");
        }
        break;
      case 3:
        return;
    }
  }

  private void gestionarTablero(int idTablero) {
    Tablero tablero = tableroService.obtenerTableroPorId(idTablero);
    if (tablero == null || tablero.getUsuario().getId() != usuario.getId()) {
      System.out.println("❌ Tablero no encontrado o no te pertenece.");
      return;
    }

    // Delegamos al nuevo menú específico
    TableroMenu tableroMenu = new TableroMenu(sc, tablero);
    tableroMenu.mostrarMenu();
    // Al volver, no hacemos nada especial, simplemente se repinta userMenu
  }

  private void buscarPorEtiqueta() {
    System.out.println("\n--- BUSCAR POR ETIQUETA ---");
    var etiquetas = etiquetaService.listarEtiquetas();
    if (etiquetas == null || etiquetas.isEmpty()) {
      System.out.println("No hay etiquetas creadas.");
      return;
    }

    TextTable tableEt = new TextTable("ID", "TITULO");
    for (Etiqueta e : etiquetas) {
      tableEt.addRow(String.valueOf(e.getId()), e.getTitulo());
    }
    System.out.println(tableEt.toString());

    System.out.print("Introduce ID etiqueta: ");
    int idEt = sc.nextInt();
    sc.nextLine();

    var resultados = tarjetaService.listarPorEtiquetaUsuario(idEt, usuario.getId());

    if (resultados == null || resultados.isEmpty()) {
      System.out.println("📭 No tienes tarjetas con esa etiqueta.");
    } else {
      System.out.println("🔍 Resultados:");
      TextTable tableRes = new TextTable("TABLERO", "COLUMNA", "TARJETA");
      for (Tarjeta t : resultados) {
        // Navegamos hacia arriba para dar contexto: Tablero > Columna > Tarjeta
        tableRes.addRow(
            t.getColumna().getTablero().getNombre(),
            t.getColumna().getTitulo(),
            t.getTitulo());
      }
      System.out.println(tableRes.toString());
    }
  }
}
