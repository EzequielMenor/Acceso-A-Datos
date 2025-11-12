package com.ezequiel.tema02.boletin01.act1;

import com.ezequiel.tema02.boletin01.act9.ClasifEquipo;

import java.util.List;

public interface EquipoDAO {
    List<Equipo> findAll();

    //Ejercicio 9
    List<ClasifEquipo> getClasifEquipo();
}
