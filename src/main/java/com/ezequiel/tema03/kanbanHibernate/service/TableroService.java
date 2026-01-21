package com.ezequiel.tema03.kanbanHibernate.service;

import java.util.List;

import com.ezequiel.tema03.kanbanHibernate.models.Columna;
import com.ezequiel.tema03.kanbanHibernate.models.Tablero;
import com.ezequiel.tema03.kanbanHibernate.models.Usuario;
import com.ezequiel.tema03.kanbanHibernate.repository.TableroRepository;

public class TableroService {
  private TableroRepository tableroRepository;

  public TableroService() {
    this.tableroRepository = new TableroRepository();
  }

  public void crearTablero(String nombre, Usuario usuario) {
    Tablero tablero = new Tablero(nombre, usuario);
    // Añadimos las columnas default
    tablero.addColumna(new Columna("TODO", tablero));
    tablero.addColumna(new Columna("DOING", tablero));
    tablero.addColumna(new Columna("DONE", tablero));

    tableroRepository.guardar(tablero);
  }

  public List<Tablero> obtenerTablerosDeUsuario(Usuario usuario) {
    return tableroRepository.listarPorUsuario(usuario);
  }

  public Tablero obtenerTableroPorId(int id) {
    return tableroRepository.buscarPorId(id);
  }

  public void eliminarTablero(int idTablero) {
    Tablero t = obtenerTableroPorId(idTablero);
    if (t != null) {
      tableroRepository.eliminar(t);
    }
  }
}