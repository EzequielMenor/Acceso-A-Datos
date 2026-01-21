package com.ezequiel.tema03.kanbanHibernate.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "tarjetas")
public class Tarjeta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private String titulo;

  @Column(nullable = false)
  private String descripcion;

  @ManyToOne
  @JoinColumn(name = "columna_id", nullable = false)
  private Columna columna;

  // RELACIÓN MUCHOS A MUCHOS CON ETIQUETAS
  // 'cascade = MERGE' permite guardar la tarjeta y relacionar etiquetas
  // existentes sin borrarlas.
  @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
  @JoinTable(name = "tarjetas_etiquetas", // Nombre de la tabla intermedia que crea Hibernate
      joinColumns = @JoinColumn(name = "tarjeta_id"), inverseJoinColumns = @JoinColumn(name = "etiqueta_id"))
  private List<Etiqueta> etiquetas = new ArrayList<>();

  public Tarjeta() {
  }

  public Tarjeta(String titulo, String descripcion, Columna columna) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.columna = columna;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Columna getColumna() {
    return columna;
  }

  public void setColumna(Columna columna) {
    this.columna = columna;
  }

  public List<Etiqueta> getEtiquetas() {
    return etiquetas;
  }

  public void setEtiquetas(List<Etiqueta> etiquetas) {
    this.etiquetas = etiquetas;
  }

  @Override
  public String toString() {
    return "Tarjeta{" + "id=" + id + ", titulo='" + titulo + '\'' + '}';
  }
}
