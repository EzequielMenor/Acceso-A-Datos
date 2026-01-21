package com.ezequiel.tema03.kanbanHibernate.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "columnas")
public class Columna {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private String titulo;

  @ManyToOne
  @JoinColumn(name = "tablero_id", nullable = false)
  private Tablero tablero;

  @OneToMany(mappedBy = "columna", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<Tarjeta> tarjetas = new ArrayList<>();

  public Columna() {
  }

  public Columna(String titulo, Tablero tablero) {
    this.titulo = titulo;
    this.tablero = tablero;
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

  public Tablero getTablero() {
    return tablero;
  }

  public void setTablero(Tablero tablero) {
    this.tablero = tablero;
  }

  public List<Tarjeta> getTarjetas() {
    return tarjetas;
  }

  public void setTarjetas(List<Tarjeta> tarjetas) {
    this.tarjetas = tarjetas;
  }

  public void addTarjeta(Tarjeta tarjeta) {
    tarjetas.add(tarjeta);
    tarjeta.setColumna(this);
  }

  @Override
  public String toString() {
    return "Columna{" + "id=" + id + ", titulo='" + titulo + '\'' + '}';
  }
}
