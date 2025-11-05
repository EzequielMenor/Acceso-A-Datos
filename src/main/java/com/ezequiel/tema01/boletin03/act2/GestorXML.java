package com.ezequiel.tema01.boletin03.act2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GestorXML {

    /**
     * Metodo para extrar contenido teto en una etiqueta hija especifica
     * @param padre
     * @param nombreEtiqueta
     * @return
     */
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

    public List<Empleado> parse(InputStream inputStream) throws Exception {
        List<Empleado> empleadoList = new ArrayList<>();

        if (inputStream == null) {
            throw new IllegalArgumentException("El archivo XML no puedo ser cargado");
        }

        //Obtenemos DocumentBuilderFactory y DocumentBuilder para parsear mediante DOM
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Obtenemos el Document parseando mediante DOM
        Document dom = builder.parse(inputStream);

        //Eliminamos nodos de texto redundantes
        dom.getDocumentElement().normalize();

        NodeList items = dom.getElementsByTagName("empleado");

        for (int i = 0; i < items.getLength(); i++) {
            Node item = items.item(i);

            //Verificamos Nodo sea un elemento
            if (item.getNodeType() == Node.ELEMENT_NODE){
                Element empleadoElement = (Element) item;

                //Obtenemos atributos
                String empleadoID = empleadoElement.getAttribute("id");

                String nombre = getElementoTexto(empleadoElement, "nombre");

                String salarioTxt = getElementoTexto(empleadoElement, "salario");
                double salario = Double.parseDouble(salarioTxt);

                //Crea el objeto
                empleadoList.add(new Empleado(empleadoID, nombre, salario));
            }

        }
        inputStream.close();
        return empleadoList;
    }


}
