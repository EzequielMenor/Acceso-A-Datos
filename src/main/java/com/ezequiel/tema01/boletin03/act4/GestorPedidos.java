package com.ezequiel.tema01.boletin03.act4;

import com.ezequielmenor.tema01.act2.Empleado2;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GestorPedidos {
    private Document Dom;

    public GestorPedidos(InputStream inputStream) throws Exception {
        if (inputStream == null) {
            throw new IllegalArgumentException("El archivo XML no puedo ser cargado");
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Dom = builder.parse(inputStream);
        Dom.getDocumentElement().normalize();
    }

    /**
     * Busca el pedido por ID en el DOM guardado y parsea el objeto Pedidos completo.
     */
    public List<Pedidos> buscarPedidoPorId(String idPedido){
        List<Pedidos> resultados = new ArrayList<>();
        NodeList pedidosNodos = Dom.getElementsByTagName("pedido");

        for (int i = 0; i < pedidosNodos.getLength(); i++){
            Node nodo = pedidosNodos.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE){
                Element pedidoElement = (Element) nodo;
                String pedidoId = pedidoElement.getAttribute("id");

                // Encontrar el pedido por ID
                if (pedidoId.equals(idPedido)){
                    try{

                        // ⚠️ CORRECCIÓN: INVERTIR LA LÓGICA TERNARIA
                        Node fechaNode = pedidoElement.getElementsByTagName("fecha").item(0);
                        String fecha = (fechaNode != null) ? fechaNode.getTextContent() : ""; // CORRECTO

                        // ⚠️ CORRECCIÓN: INVERTIR LA LÓGICA TERNARIA
                        Node totalNode = pedidoElement.getElementsByTagName("total").item(0);
                        double total = (totalNode != null) ? Double.parseDouble(totalNode.getTextContent()) : 0.0; // CORRECTO

                        // Extraccion cliente
                        Element clientElement = (Element) pedidoElement.getElementsByTagName("cliente").item(0);
                        String nombre = "";
                        String mail = "";

                        if (clientElement != null) {
                            // ⚠️ CORRECCIÓN: INVERTIR LA LÓGICA TERNARIA
                            Node nombreNode = clientElement.getElementsByTagName("nombre").item(0);
                            nombre = (nombreNode != null) ? nombreNode.getTextContent() : ""; // CORRECTO

                            // ⚠️ CORRECCIÓN: INVERTIR LA LÓGICA TERNARIA
                            Node mailNode = clientElement.getElementsByTagName("email").item(0);
                            mail = (mailNode != null) ? mailNode.getTextContent() : "";
                        }

                        List<Item> items = new ArrayList<>();
                        NodeList itemsContenedor = pedidoElement.getElementsByTagName("items");

                        if (itemsContenedor.getLength() > 0){
                            Element contenedor = (Element) itemsContenedor.item(0);
                            NodeList itemNodos = contenedor.getElementsByTagName("item");

                            for (int j = 0; j < itemNodos.getLength(); j++){
                                Node itemNodo = itemNodos.item(j);
                                if (itemNodo.getNodeType() == Node.ELEMENT_NODE){
                                    Element itemElement = (Element) itemNodo;

                                    String sku = itemElement.getElementsByTagName("sku").item(0).getTextContent();
                                    String descripcion = itemElement.getElementsByTagName("descripcion").item(0).getTextContent();

                                    int cantidad = Integer.parseInt(itemElement.getElementsByTagName("cantidad").item(0).getTextContent());
                                    double precioUnitario = Double.parseDouble(itemElement.getElementsByTagName("precioUnitario").item(0).getTextContent());

                                    items.add(new Item(sku, descripcion, cantidad, precioUnitario));
                                }
                            }
                        }

                        //Crear el objeto Pedidos
                        Pedidos pedidoEncontrado = new Pedidos(pedidoId, fecha, nombre, mail, items, total);

                        //Almacena y devuelve el resultado
                        resultados.add(pedidoEncontrado);
                        return resultados;
                    } catch (Exception e) {
                        System.err.println("Error al parsear el pedido con ID "+ idPedido + ": " + e.getMessage());
                        return new ArrayList<>();
                    }
                }
            }
        }
        return resultados;
    }

    /**
     * Recalcula el total del último pedido encontrado (Punto b).
     */
    public double recalcularTotal(Pedidos pedidos){
        double total = 0.0;
        List<Item> items = pedidos.getItems();

        for (Item item : items){
            total += item.getCantidad() * item.getPrecioUnitario();
        }
        return total;
    }

}
