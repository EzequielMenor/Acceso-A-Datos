package com.ezequiel.tema02.boletin01.act2;

import java.util.List;

public interface CiclistaDAO {
    List<Ciclista> findByIdEquipo(int id_equipo);
    List<Ciclista> findAll();
    double getVelocidadMedia(int id_ciclista);
}
