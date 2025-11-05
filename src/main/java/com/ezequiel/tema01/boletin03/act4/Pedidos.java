package com.ezequiel.tema01.boletin03.act4;

import java.util.List;

public class Pedidos {
    private String id;
    private String fecha;
    private String clienteNombre;
    private String clienteMail;
    private List<Item> items;
    private double total;

    public Pedidos(String id, String fecha, String clienteNombre, String clienteMail, List<Item> items, double total) {
        this.id = id;
        this.fecha = fecha;
        this.clienteNombre = clienteNombre;
        this.clienteMail = clienteMail;
        this.items = items;
        this.total = total;
    }

    public String getId() {
        return id;
    }
    public String getFecha() {
        return fecha;
    }
    public String getClienteNombre() {
        return clienteNombre;
    }
    public String getClienteMail() {
        return clienteMail;
    }
    public List<Item> getItems() {
        return items;
    }
    public double getTotal() {
        return total;
    }
}
