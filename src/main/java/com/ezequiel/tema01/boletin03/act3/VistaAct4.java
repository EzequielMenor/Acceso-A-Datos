package com.ezequiel.tema01.boletin03.act3;

import com.ezequielmenor.tema01.act4.GestorPedidos;
import com.ezequielmenor.tema01.act4.Item;
import com.ezequielmenor.tema01.act4.Pedidos;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class VistaAct4 {
    private static final String RutaXML = "/Datasets/pedidos.xml";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorPedidos gestor = null;

        try {
            InputStream is = VistaAct4.class.getResourceAsStream(RutaXML);
            if (is == null) throw new Exception("El recurso XML no fue encontrado");

            gestor = new GestorPedidos(is);

            System.out.print("Introduzca el ID del pedido: ");
            String idBuscado = sc.nextLine().trim();

            List<Pedidos> resultados = gestor.buscarPedidoPorId(idBuscado);

            if (resultados.isEmpty()){
                System.out.println("Pedido con ID " + idBuscado + " no encontrado");
            } else {
                Pedidos pedido = resultados.get(0);

                System.out.println("Pedido " + pedido.getId() + " encontrado para " + pedido.getClienteNombre());
                System.out.println("--- ITEMS DEL PEDIDO ---");

                for (Item item : pedido.getItems()){
                    System.out.printf("- SKU: %-6s | %-30s | Cantidad: %d | Precio U.: %.2f€\n",
                            item.getSku(),
                            item.getDescripcion(),
                            item.getCantidad(),
                            item.getPrecioUnitario());
                }

                double totalCalculado = gestor.recalcularTotal(pedido);
                double totalXML = pedido.getTotal();

                System.out.println("--- COMPROBACIÓN DEL TOTOAL ---");
                System.out.printf("Total em XML: %.2f€\n", totalXML);
                System.out.printf("Total calculado: %.2f€\n", totalCalculado);

                if ( Math.abs(totalCalculado - totalXML) < 0.01){
                    System.out.println("REULTADO CORRECTO, coincide con el total del XML");
                } else {
                    System.out.println("RESULTADO INCORRECTO, no coincide con el total del XML");
                }
                System.out.println("----------------------------");
            }
        } catch (Exception e) {
            System.err.println("Error, nos se pudo completar el listado");
            System.err.println("Causa: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

}
