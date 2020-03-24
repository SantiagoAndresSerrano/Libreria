
package Modelo;

import Negocio.Transaccion;
import java.util.ArrayList;

/**
 *
 * @author santi
 */
public class Libro 
{
    
    private String isbn,titulo;
    private double precioCompra;
    private double precioVenta;
    private int cantidadActual;
    private ArrayList<Transaccion> transacciones;

    public Libro(String isbn, String titulo, double precioCompra, double precioVenta, int cantidadActual) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.cantidadActual = cantidadActual;
        this.transacciones=new ArrayList<>();
    }

    public Libro() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getCantidadActual() {
        return cantidadActual;
    }
    
    public boolean vender(int cantidad,String fecha)
    {
        System.out.println("Cantidad Actual de libros "+cantidadActual+" Cantidad que se desea comprar"+cantidad);
      
        
           this.transacciones.add(new Transaccion(Tipo.VENTA,cantidad,fecha));
           
           
           return true;
        
       
    
    }
    
    public void abastecer(int cantidad,String fecha)
    {
        this.transacciones.add(new Transaccion(Tipo.ABASTECER,cantidad,fecha));
        this.cantidadActual=cantidadActual+cantidad;
    }
    
    public void setCantidadActual(int cantidadActual) {
        this.cantidadActual = cantidadActual;
    }
    
    
    public ArrayList<Transaccion> getTransacciones()
    {
    return this.transacciones;  
    }
    
    public int getNumeroVentas()
    {
        int cantidadVenta=0;
        for (int i = 0; i < this.transacciones.size(); i++)
        {
            if(this.transacciones.get(i).getTipo()==Tipo.VENTA)
            {
                cantidadVenta+=transacciones.get(i).getCantidad();
            }
        }
    return cantidadVenta;
    }
    
    public String recorrerTransacciones()
    {
        String datos="";
        
        for (int i = 0; i < this.transacciones.size(); i++) {
            datos="Libro "+this.titulo+"= "+transacciones.toString();
        }
        
        return datos;
    }
    
    @Override
    public String toString() {
        return "Libro{" + "isbn=" + isbn + ", titulo=" + titulo + ", precioCompra=" + precioCompra + ", precioVenta=" + precioVenta + ", cantidadActual=" + cantidadActual + '}';
    }
    
    

}
