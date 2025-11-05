package com.ezequiel.tema01.boletin03.act3;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class VistaAct3 {
    private static final String RutaXML = "/Datasets/biblioteca.xml";

    public static void main(String[] args) {
        GestorBiblio gestor = new GestorBiblio();

        try {
            InputStream is = VistaAct3.class.getResourceAsStream(RutaXML);
            if (is == null) throw new Exception("El recurso XML no fue encontrado");

            //Pide los datos al gestor
            List<Libro> listaLibro = gestor.parse(is);

            System.out.println("LISTADO DE TITULOS");
            System.out.println("------------------------------------------------------------------");

            if (listaLibro.isEmpty()){
                System.out.println("No se encontraron libros");
            } else {
                for (Libro libro : listaLibro){
                    System.out.println(libro.getTitulo());
                }
                System.out.println("------------------------------------------------------------------");
            }


            System.out.println("LIBROS POR GENERO");
            Map<String, Long> conteo = gestor.contarLibPorGenero(listaLibro);

            System.out.printf("%-20s | %-10s%n", "Genero", "Total Libros");
            System.out.println("------------------------------------------------------------------");

            for (Map.Entry<String, Long> entry : conteo.entrySet()){
                System.out.printf("%-20s | %-10s%n", entry.getKey(), entry.getValue());
            }
            System.out.println("------------------------------------------------------------------");

        } catch (Exception e) {
            System.err.println("Error, nos se pudo completar el listad");
            System.err.println("Causa: " + e.getMessage());
        }
    }
}
