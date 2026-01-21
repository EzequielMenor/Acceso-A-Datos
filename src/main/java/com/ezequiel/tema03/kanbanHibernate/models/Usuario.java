package com.ezequiel.tema03.kanbanHibernate.models;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String passwd;

  public Usuario() {
  }

  public Usuario(String email, String passwd) {
    this.email = email;
    this.passwd = passwd;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  @Override
  public String toString() {
    return "Usuario{" + "id=" + id + ", email='" + email + '\'' + '}';
  }
}
