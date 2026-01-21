package com.ezequiel.tema03.kanbanHibernate.service;

import com.ezequiel.tema03.kanbanHibernate.models.Columna;
import com.ezequiel.tema03.kanbanHibernate.models.Tarjeta;
import com.ezequiel.tema03.kanbanHibernate.repository.TarjetaRepository;

public class TarjetaService {
  private TarjetaRepository tarjetaRepository;

  public TarjetaService() {
    this.tarjetaRepository = new TarjetaRepository();
  }

  public void crearTarjeta(String titulo, String descripcion, Columna columna) {
    // Validación simple
    if (titulo == null || titulo.trim().isEmpty()) {
      System.out.println("⚠️ Título obligatorio");
      return;
    }
    Tarjeta tarjeta = new Tarjeta(titulo, descripcion, columna);
    tarjetaRepository.guardar(tarjeta);
  }

  public void moverTarjeta(Tarjeta tarjeta, Columna nuevaColumna) {
    if (tarjeta.getColumna().getTablero().getId() != nuevaColumna.getTablero().getId()) {
      System.out.println("⛔ ERROR: ¡No puedes mover tarjetas entre tableros distintos!");
      return;
    }
    tarjeta.setColumna(nuevaColumna);
    tarjetaRepository.actualizar(tarjeta);
  }

  public Tarjeta obtenerTarjeta(int id) {
    return tarjetaRepository.buscarPorId(id);
  }

  public void editarTarjeta(Tarjeta tarjeta, String nuevoTitulo, String nuevaDesc) {
    if (nuevoTitulo != null && !nuevoTitulo.isEmpty())
      tarjeta.setTitulo(nuevoTitulo);
    if (nuevaDesc != null)
      tarjeta.setDescripcion(nuevaDesc);
    tarjetaRepository.actualizar(tarjeta);
  }

  public void eliminarTarjeta(int id) {
    Tarjeta t = obtenerTarjeta(id);
    if (t != null) {
      tarjetaRepository.eliminar(t);
    }
  }

  public java.util.List<Tarjeta> listarPorEtiquetaUsuario(int etiquetaId, int usuarioId) {
    return tarjetaRepository.buscarPorEtiquetaYUsuario(etiquetaId, usuarioId);
  }
}
