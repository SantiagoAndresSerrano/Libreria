/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Modelo.Tipo;

/**
 *
 * @author santi
 */
public class Transaccion {
    Tipo tipo;
    int cantidad;
    String fecha;

    public Transaccion(Tipo tipo, int cantidad, String fecha) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public Transaccion()
    {
        
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Transaccion{" + "tipo=" + tipo + ", cantidad=" + cantidad + ", fecha=" + fecha + '}';
    }
    


}
