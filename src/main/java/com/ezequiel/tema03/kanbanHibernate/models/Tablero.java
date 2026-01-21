package com.ezequiel.tema03.kanbanHibernate.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "tableros")
public class Tablero {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private String nombre;

  @ManyToOne
  @JoinColumn(name = "usuario_id", nullable = false)
  private Usuario usuario;

  @OneToMany(mappedBy = "tablero", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<Columna> columnas = new ArrayList<>();

  public Tablero() {
  }

  public Tablero(String nombre, Usuario usuario) {
    this.nombre = nombre;
    this.usuario = usuario;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public List<Columna> getColumnas() {
    return columnas;
  }

  public void setColumnas(List<Columna> columnas) {
    this.columnas = columnas;
  }

  // Método helper para añadir columnas fácilmente y mantener la coherencia
  // bidireccional
  public void addColumna(Columna columna) {
    columnas.add(columna);
    columna.setTablero(this);
  }

  public void removeColumna(Columna columna) {
    columnas.remove(columna);
    columna.setTablero(null);
  }

  @Override
  public String toString() {
    return "Tablero{" + "id=" + id + ", nombre='" + nombre + '\'' + '}';
  }
}
