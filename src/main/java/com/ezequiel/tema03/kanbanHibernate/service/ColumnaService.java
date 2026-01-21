package com.ezequiel.tema03.kanbanHibernate.service;

import com.ezequiel.tema03.kanbanHibernate.models.Columna;
import com.ezequiel.tema03.kanbanHibernate.models.Tablero;
import com.ezequiel.tema03.kanbanHibernate.repository.ColumnaRepository;

public class ColumnaService {
  private ColumnaRepository columnaRepository;

  public ColumnaService() {
    this.columnaRepository = new ColumnaRepository();
  }

  public void crearColumna(String titulo, Tablero tablero) {
    Columna columna = new Columna(titulo, tablero);
    columnaRepository.guardar(columna);
  }

  public Columna buscarColumna(int id) {
    return columnaRepository.buscarPorId(id);
  }

  // ESTOS SON LOS QUE FALTABAN 👇
  public void renombrarColumna(Columna columna, String nuevoTitulo) {
    columna.setTitulo(nuevoTitulo);
    columnaRepository.actualizar(columna);
  }

  public void eliminarColumna(int id) {
    Columna c = buscarColumna(id);
    if (c != null) {
      columnaRepository.eliminar(c);
    }
  }
}