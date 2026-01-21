package com.ezequiel.tema03.hibernatetest.models;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Asignatura")
public class Asignatura {
  @Id
  private final String id;
  @Column(nullable = false)
  private final String nombre;

  @ManyToAny
  @JoinTable(name = "alumno_asignatura", joinColumns = @JoinColumn(name = "alumno_nia", nullable = false), inverseJoinColumns = @JoinColumn(name = "asignatura_id", nullable = false))
  private final Set<Alumno> alumno;

  public Asignatura() {
    this(null, null);
  }

  public Asignatura(String id, String nombre) {
    this.id = id;
    this.nombre = nombre;
    this.alumno = new HashSet<>();
  }

  public String getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public Set<Alumno> getAlumno() {
    return alumno;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
    result = prime * result + ((alumno == null) ? 0 : alumno.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Asignatura other = (Asignatura) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (nombre == null) {
      if (other.nombre != null)
        return false;
    } else if (!nombre.equals(other.nombre))
      return false;
    if (alumno == null) {
      if (other.alumno != null)
        return false;
    } else if (!alumno.equals(other.alumno))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Asignatura [id=" + id + ", nombre=" + nombre + ", alumno=" + alumno + "]";
  }

}
