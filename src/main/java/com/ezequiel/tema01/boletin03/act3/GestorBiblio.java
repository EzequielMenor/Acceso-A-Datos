package com.ezequiel.tema01.boletin03.act3;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GestorBiblio {
    private String getElementoTexto(Element padre, String nombreEtiqueta){
        //Obtenemos nodos hijos con el nombre indicado
        NodeList nodeList = padre.getElementsByTagName(nombreEtiqueta);

        if (nodeList.getLength() > 0){
            //Obtenemos primer hijo encontrado
            Element elemento = (Element) nodeList.item(0);
            //Devuleve texto contiene el elemento
            return elemento.getTextContent();
        }
        return "";
    }

    private List<String> getListGeneros(Element libroElement){
        List<String> generos = new ArrayList<>();

        //Buscar "generos" dentro de libroElement
        NodeList contenedores = libroElement.getElementsByTagName("generos");

        if (contenedores.getLength() > 0){
            Element contenedorGeneros = (Element) contenedores.item(0);
            //Localiza todos los hijos de "generos"
            NodeList hijosGeneros = contenedorGeneros.getElementsByTagName("genero");

            for (int i = 0; i < hijosGeneros.getLength(); i++){
                Node nodoGenero = hijosGeneros.item(i);
                if (nodoGenero.getNodeType() == Node.ELEMENT_NODE){
                    Element generoElement = (Element) nodoGenero;

                    generos.add(generoElement.getTextContent());
                }
            }
        }
        return generos;
    }

    public List<Libro> parse(InputStream inputStream) throws Exception {
        List<Libro> libroList = new ArrayList<>();

        if (inputStream == null) throw new IllegalArgumentException("El archivo xml no pudo ser cargado");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document dom = builder.parse(inputStream);
        dom.getDocumentElement().normalize();

        NodeList items = dom.getElementsByTagName("libro");

        for (int i = 0; i < items.getLength(); i++){
            Node item = items.item(i);

            if (item.getNodeType() == Node.ELEMENT_NODE){
                Element libroElement = (Element) item;

                String titulo = getElementoTexto(libroElement, "titulo");

                List<String> generos = getListGeneros(libroElement);

                libroList.add(new Libro(titulo, generos));
            }
        }
        inputStream.close();
        return libroList;
    }

    public Map<String, Long> contarLibPorGenero(List<Libro> libros){
        //Convierte la List<Libro> de entrada en un flujo de datos.
        Map<String, Long> conteoPorGenero = libros.stream()
                //Toma la List<String> de generos de cada libro y las uno en un unico stream plano de String
                .flatMap(libro -> libro.getGeneros().stream())

                //Toma el Stream aplanado y lo convierte en una estructura final.
                //La estructura será un Map y se agrupará por la clave que definamos.
                .collect(Collectors.groupingBy(
                        //La clave el nombre de cada genero
                        genero -> genero,
                        //Conteo de cuantas veces aparce cada clave, o cada genero
                        Collectors.counting()
                ));
        return conteoPorGenero;
    }
}
