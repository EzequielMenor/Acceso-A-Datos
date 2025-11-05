package com.ezequiel.tema01.boletin03.act2;

import java.io.InputStream;
import java.util.List;

public class VistaAct1 {
    private static final String RutaXML = "/Datasets/empleados.xml";

    public static void main(String[] args) {
        //Crea instancia
        GestorXML gestor = new GestorXML();

        try{
            InputStream is = VistaAct1.class.getResourceAsStream(RutaXML);

            if (is == null){
                throw new Exception("El recurso XML no fue encontrado");
            }

            //Pide los datos al gestor
            List<Empleado> listaEmpleado = gestor.parse(is);

            //Recorre la lista e imprime
            for (Empleado empleado : listaEmpleado) {
                System.out.println(empleado);
                System.out.println("");

            }

        } catch (Exception e) {
            System.err.println("Error, nos se pudo completar el listad");
            System.err.println("Causa: " + e.getMessage());
        }
    }
}
