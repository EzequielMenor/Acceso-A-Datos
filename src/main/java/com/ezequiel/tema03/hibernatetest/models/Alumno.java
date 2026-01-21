package com.ezequiel.tema03.hibernatetest.models;

import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "alumno")
public class Alumno {
    @Id
    private final String nia;
    @Column(nullable = false)
    private final String nombre;

    @ManyToAny
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToMany(mappedBy = "alumnos")
    private Set<Asignatura> asignaturas;

    public Alumno() {
        this(null, null, null);
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
        curso.getAlumnos().add(this);
    }

    public Alumno(String nia, String nombre, Curso curso) {
        this.nia = nia;
        this.nombre = nombre;
        setCurso(curso);
    }

    public String getNia() {
        return nia;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Alumno alumno))
            return false;

        return nia.equals(alumno.nia);
    }

    @Override
    public int hashCode() {
        return nia.hashCode();
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nia='" + nia + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
