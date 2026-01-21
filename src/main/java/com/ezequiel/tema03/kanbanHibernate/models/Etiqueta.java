package com.ezequiel.tema03.kanbanHibernate.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "etiquetas")
public class Etiqueta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false, unique = true)
  private String titulo;

  // "mappedBy" le dice a Hibernate: "La otra clase (Tarjeta) es la que manda en
  // esta relación"
  @ManyToMany(mappedBy = "etiquetas")
  private List<Tarjeta> tarjetas = new ArrayList<>();

  public Etiqueta() {
  }

  public Etiqueta(String titulo) {
    this.titulo = titulo;
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

  public List<Tarjeta> getTarjetas() {
    return tarjetas;
  }

  public void setTarjetas(List<Tarjeta> tarjetas) {
    this.tarjetas = tarjetas;
  }

  @Override
  public String toString() {
    return "Etiqueta{" + "id=" + id + ", titulo='" + titulo + '\'' + '}';
  }
}
