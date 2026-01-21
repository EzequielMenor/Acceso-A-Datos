package com.ezequiel.tema03.hibernatetest.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "curso")
public class Curso {
    @Id
    private final String id;
    @Column(nullable = false)
    private final String nombre;
    @OneToMany(mappedBy = "curso")
    private final List<Alumno> alumnos;

    public Curso() {
        this(null, null);
    }

    public Curso(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.alumnos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public boolean addAlumno(Alumno alumno) {
        return alumnos.add(alumno);
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Curso curso))
            return false;

        return id.equals(curso.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", alumnos='" + alumnos + '\'' +
                '}';
    }
}
