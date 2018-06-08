/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.model;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author jonaspeuqno
 */
public class Pedido {
    private int id;
    private LocalDate data;
    private int cliente;
    private double valor;

    public Pedido(int id, LocalDate data, int cliente, double valor) {
        this.id = id;
        this.data = data;
        this.cliente = cliente;
        this.valor = valor;
    }

    public Pedido() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }    

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", data=" + data + ", cliente=" + cliente + ", valor=" + valor + '}';
    }
    
    
}
