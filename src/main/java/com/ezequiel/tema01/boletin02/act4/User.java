package com.ezequiel.tema01.boletin02.act4;

public class User {
    private String nombre;
    private String hashedPasswd;

    //Constructor vacio para la libreria Gson
    public User() {
    }

    public User(String nombre, String hashedPasswd){
        this.nombre = nombre;
        this.hashedPasswd = hashedPasswd;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public String getHashedPasswd() {
        return hashedPasswd;
    }

    //Setter
    public void setHashedPasswd(String hashedPasswd){
        this.hashedPasswd = hashedPasswd;
    }
}
