package com.ezequiel.tema02.boletin01.act2;

import com.ezequiel.tema02.boletin01.act6.ClasificacionMontana;
import com.ezequiel.tema02.boletin01.act7.ClasificacionRegularidad;

import java.util.List;

public interface CiclistaDAO {
    List<Ciclista> findByIdEquipo(int id_equipo);
    List<Ciclista> findAll();

    //Ejercicio 4
    double getVelocidadMedia(int id_ciclista);

    //Ejercicio 6
    List<ClasificacionMontana> getClasificacionMontana();

    //Ejercicio 7
    List<ClasificacionRegularidad> getClasifRegularidad();
}
