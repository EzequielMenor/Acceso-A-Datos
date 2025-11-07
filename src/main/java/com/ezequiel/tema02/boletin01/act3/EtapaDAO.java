package com.ezequiel.tema02.boletin01.act3;

import com.ezequiel.tema02.boletin01.act5.ClasifEtapa;

import java.util.List;

public interface EtapaDAO {
    List<ResumenEtapa> getResumenPorTipo();
    List<Etapa> findAll();
    List<ClasifEtapa> getClasifEtapa (int idEtapa);
}
