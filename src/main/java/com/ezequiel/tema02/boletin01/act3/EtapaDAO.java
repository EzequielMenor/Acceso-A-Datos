package com.ezequiel.tema02.boletin01.act3;

import java.util.List;

public interface EtapaDAO {
    List<ResumenEtapa> getResumenPorTipo();
    List<Etapa> findAll();
}
