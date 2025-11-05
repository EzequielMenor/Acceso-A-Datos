package com.ezequiel.tema02.boletin01.act2;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CiclistaDAO {
    List<Ciclista> findByIdEquipo(int id_equipo);
    List<Ciclista> findAll();
}
