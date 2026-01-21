package com.ezequiel.tema03.kanbanHibernate.ui;

import java.util.Scanner;

import com.ezequiel.tema03.kanbanHibernate.TextTable;
import com.ezequiel.tema03.kanbanHibernate.models.*;
import com.ezequiel.tema03.kanbanHibernate.service.*;

public class TableroMenu {
  private Scanner sc;
  private Tablero tablero;
  private TableroService tableroService;
  private ColumnaService columnaService;
  private TarjetaService tarjetaService;
  private EtiquetaService etiquetaService;

  public TableroMenu(Scanner sc, Tablero tablero) {
    this.sc = sc;
    this.tablero = tablero;
    this.tableroService = new TableroService();
    this.columnaService = new ColumnaService();
    this.tarjetaService = new TarjetaService();
    this.etiquetaService = new EtiquetaService();
  }

  public void mostrarMenu() {
    boolean salir = false;
    do {
      // Refrescamos el tablero para ver cambios
      tablero = tableroService.obtenerTableroPorId(tablero.getId());
      if (tablero == null)
        return; // Si se borró, salimos

      System.out.println("\n=== TABLERO: " + tablero.getNombre().toUpperCase() + " ===");
      pintarTablero();

      System.out.println("\n--- GESTIÓN ---");
      System.out.println("1. Columnas (Crear, Renombrar, Borrar)");
      System.out.println("2. Tarjetas (Crear, Editar, Mover, Borrar)");
      System.out.println("3. Etiquetas (Crear, Asignar)");
      System.out.println("4. Volver atrás");
      System.out.print("Elige: ");

      int op = 0;
      try {
        op = sc.nextInt();
      } catch (Exception e) {
        sc.nextLine();
      }
      sc.nextLine();

      switch (op) {
        case 1:
          menuColumnas();
          break;
        case 2:
          menuTarjetas();
          break;
        case 3:
          menuEtiquetas();
          break;
        case 4:
          salir = true;
          break;
        default:
          System.out.println("Opción incorrecta");
      }
    } while (!salir);
  }

  private void pintarTablero() {
    if (tablero.getColumnas().isEmpty())
      System.out.println("(Tablero vacío)");
    for (Columna c : tablero.getColumnas()) {
      System.out.println(" ╔════[" + c.getId() + "] " + c.getTitulo().toUpperCase() + "════╗");
      for (Tarjeta t : c.getTarjetas()) {
        String et = "";
        for (Etiqueta e : t.getEtiquetas())
          et += " #" + e.getTitulo();
        System.out.println(" ║  🃏 [" + t.getId() + "] " + t.getTitulo() + et);
      }
      System.out.println(" ╚════════════════════╝");
    }
  }

  // --- SUBMENÚ COLUMNAS ---
  private void menuColumnas() {
    System.out.println("\n--- COLUMNAS ---");
    TextTable table = new TextTable("ID", "TITULO");
    for (Columna c : tablero.getColumnas()) {
      table.addRow(String.valueOf(c.getId()), c.getTitulo());
    }
    System.out.println(table.toString());

    System.out.println("1. Crear Columna");
    System.out.println("2. Renombrar Columna");
    System.out.println("3. Eliminar Columna");
    System.out.println("4. Volver");

    int op = 0;
    try {
      op = sc.nextInt();
    } catch (Exception e) {
      sc.nextLine();
    }
    sc.nextLine();

    switch (op) {
      case 1:
        System.out.print("Título: ");
        columnaService.crearColumna(sc.nextLine(), tablero);
        break;
      case 2:
        System.out.print("ID Columna: ");
        int idRen = sc.nextInt();
        sc.nextLine();
        Columna colRen = columnaService.buscarColumna(idRen);
        if (colRen != null) {
          System.out.print("Nuevo Título: ");
          columnaService.renombrarColumna(colRen, sc.nextLine());
        }
        break;
      case 3:
        System.out.print("ID Columna a borrar: ");
        columnaService.eliminarColumna(sc.nextInt());
        sc.nextLine();
        break;
    }
  }

  // --- SUBMENÚ TARJETAS ---
  private void menuTarjetas() {
    System.out.println("\n--- TARJETAS ---");
    System.out.println("1. Crear Tarjeta");
    System.out.println("2. Editar Tarjeta");
    System.out.println("3. Mover Tarjeta");
    System.out.println("4. Eliminar Tarjeta");
    System.out.println("5. Volver");

    int op = 0;
    try {
      op = sc.nextInt();
    } catch (Exception e) {
      sc.nextLine();
    }
    sc.nextLine();

    switch (op) {
      case 1:
        if (tablero.getColumnas().isEmpty())
          return;

        System.out.println("--- Columnas Disponibles ---");
        TextTable tCol = new TextTable("ID", "NOMBRE");
        for (Columna c : tablero.getColumnas()) {
          tCol.addRow(String.valueOf(c.getId()), c.getTitulo());
        }
        System.out.println(tCol.toString());

        System.out.print("ID Columna destino: ");
        int idDest = 0;
        try {
          idDest = sc.nextInt();
        } catch (Exception e) {
          sc.nextLine();
        }
        sc.nextLine();

        Columna col = columnaService.buscarColumna(idDest);
        if (col != null) {
          System.out.print("Título: ");
          String tit = sc.nextLine();
          System.out.print("Descripción: ");
          tarjetaService.crearTarjeta(tit, sc.nextLine(), col);
        } else {
          System.out.println("❌ Columna no encontrada.");
        }
        break;
      case 2:
        System.out.print("ID Tarjeta: ");
        Tarjeta tEdit = tarjetaService.obtenerTarjeta(sc.nextInt());
        sc.nextLine();
        if (tEdit != null) {
          System.out.print("Nuevo Título (Enter para mantener): ");
          String nTit = sc.nextLine();
          System.out.print("Nueva Desc (Enter para mantener): ");
          String nDesc = sc.nextLine();
          tarjetaService.editarTarjeta(tEdit, nTit.isEmpty() ? null : nTit, nDesc.isEmpty() ? null : nDesc);
        }
        break;
      case 3:
        System.out.print("ID Tarjeta: ");
        Tarjeta tMov = tarjetaService.obtenerTarjeta(sc.nextInt());
        sc.nextLine();
        System.out.print("ID Nueva Columna: ");
        Columna cDest = columnaService.buscarColumna(sc.nextInt());
        sc.nextLine();
        if (tMov != null && cDest != null) {
          tarjetaService.moverTarjeta(tMov, cDest);
        }
        break;
      case 4:
        System.out.print("ID Tarjeta: ");
        tarjetaService.eliminarTarjeta(sc.nextInt());
        sc.nextLine();
        break;
    }
  }

  // --- SUBMENÚ ETIQUETAS ---
  private void menuEtiquetas() {
    System.out.println("\n--- ETIQUETAS ---");
    TextTable table = new TextTable("ID", "TITULO");
    for (Etiqueta e : etiquetaService.listarEtiquetas()) {
      table.addRow(String.valueOf(e.getId()), e.getTitulo());
    }
    System.out.println(table.toString());

    System.out.println("1. Crear Nueva Etiqueta Global");
    System.out.println("2. Ver Todas las Etiquetas (Actualizar lista)");
    System.out.println("3. Asignar Etiqueta a Tarjeta");
    System.out.println("4. Quitar Etiqueta de Tarjeta");
    System.out.println("5. Volver");

    int op = 0;
    try {
      op = sc.nextInt();
    } catch (Exception e) {
      sc.nextLine();
    }
    sc.nextLine();

    switch (op) {
      case 1:
        System.out.print("Nombre Etiqueta (ej: Urgente): ");
        etiquetaService.crearEtiqueta(sc.nextLine());
        break;
      case 2:
        // Refresh loop
        break;
      case 3:
        System.out.print("ID Tarjeta: ");
        Tarjeta tAsig = tarjetaService.obtenerTarjeta(sc.nextInt());
        System.out.print("ID Etiqueta: ");
        Etiqueta eAsig = etiquetaService.buscarPorId(sc.nextInt());
        sc.nextLine();
        if (tAsig != null && eAsig != null)
          etiquetaService.asignarEtiqueta(tAsig, eAsig);
        break;
      case 4:
        System.out.print("ID Tarjeta: ");
        Tarjeta tQuit = tarjetaService.obtenerTarjeta(sc.nextInt());
        System.out.print("ID Etiqueta: ");
        Etiqueta eQuit = etiquetaService.buscarPorId(sc.nextInt());
        sc.nextLine();
        if (tQuit != null && eQuit != null)
          etiquetaService.quitarEtiqueta(tQuit, eQuit);
        break;
    }
  }
}
