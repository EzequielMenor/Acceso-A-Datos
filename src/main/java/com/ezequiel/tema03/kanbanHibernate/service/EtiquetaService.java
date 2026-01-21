package com.ezequiel.tema03.kanbanHibernate.service;

import java.util.List;
import com.ezequiel.tema03.kanbanHibernate.models.Etiqueta;
import com.ezequiel.tema03.kanbanHibernate.models.Tarjeta;
import com.ezequiel.tema03.kanbanHibernate.repository.EtiquetaRepository;
import com.ezequiel.tema03.kanbanHibernate.repository.TarjetaRepository;

public class EtiquetaService {

  private EtiquetaRepository etiquetaRepository;
  private TarjetaRepository tarjetaRepository;

  public EtiquetaService() {
    this.etiquetaRepository = new EtiquetaRepository();
    this.tarjetaRepository = new TarjetaRepository(); // Para guardar la relacion
  }

  public void crearEtiqueta(String titulo) {
    Etiqueta etiqueta = new Etiqueta(titulo);
    etiquetaRepository.guardar(etiqueta);
  }

  public List<Etiqueta> listarEtiquetas() {
    return etiquetaRepository.listarTodas();
  }

  public Etiqueta buscarPorId(int id) {
    return etiquetaRepository.buscarPorId(id);
  }

  public void asignarEtiqueta(Tarjeta tarjeta, Etiqueta etiqueta) {
    // La entidad propietaria de la relacion ManyToMany es Tarjeta (donde tiene el
    // @JoinTable)
    if (!tarjeta.getEtiquetas().contains(etiqueta)) {
      tarjeta.getEtiquetas().add(etiqueta);
      tarjetaRepository.actualizar(tarjeta);
    }
  }

  public void quitarEtiqueta(Tarjeta tarjeta, Etiqueta etiqueta) {
    if (tarjeta.getEtiquetas().remove(etiqueta)) {
      tarjetaRepository.actualizar(tarjeta);
    }
  }
}
