/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Modelo.Libro;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author santi
 */
public class TiendaLibros 
{
    private double caja;
    private ArrayList<Libro> libros=new ArrayList<>();;
    public TiendaLibros() 
    {
        
    }

    public double getCaja() {
        return caja;
    }

    public void setCaja(double caja) {
        this.caja = caja;
    }
    
   public String transaccionesTotales()
   {
   String datos="";
   
    for (int i = 0; i < libros.size(); i++)
    {
       datos+="\n"+libros.get(i).recorrerTransacciones();
    }
   
   return datos;
   }
    
    public ArrayList<Libro> getCatalogo()
    {   
        return this.libros;
    }
    
    public Libro buscarPorNombre(String nombre) throws Exception
    {
        if(this.libros==null)
            throw new Exception("no hay libro registrados");
        
        for (int i = 0; i < libros.size(); i++) {
            if(nombre.equals(libros.get(i).getTitulo()))
            {
                return libros.get(i);
            }
        }
        return null;
    }
    
    public Libro buscarPorTitulo(String titulo) throws Exception
    {
    if(this.libros==null)
            throw new Exception("no hay libros registrados");
        
        for (int i = 0; i < libros.size(); i++) {
            if(titulo.equals(libros.get(i).getTitulo()))
                return libros.get(i);
        }
        return null;
    
    
    }
    
    public Libro buscarMasVendido()
    {
        Libro libroMayor=libros.get(0);
        Libro libroActual=new Libro();
        for (int i = 0; i <libros.size(); i++) 
        {
            libroActual=libros.get(i);
            if(libroActual.getNumeroVentas() > libroMayor.getNumeroVentas())
            {
                libroMayor=libroActual;
            }
        }
    
        return libroMayor;
    }
    
    
    
    public Libro buscarPorIsbn(String isbn) throws Exception
    {
        if(this.libros==null)
            throw new Exception("no hay libros registrados");
        
        for (int i = 0; i < libros.size(); i++) {
            if(isbn.equals(libros.get(i).getIsbn()))
            {
                return libros.get(i);
            }
        }
        return null;
    
    }
    
    public void registrarLibro(String isbn,String nombre, double precioVenta, double precioCompra, int cantidad)
    {
       
        Libro l=new Libro(isbn,nombre,precioVenta,precioCompra,cantidad);
        this.libros.add(l);
        
        
    
    }
    
    public boolean elimnarLibro(String dato)
    {
        for (int i = 0; i < libros.size(); i++)
        {
            if(dato.equals(libros.get(i).getIsbn()))
            {   System.out.println(libros.get(i).getTitulo()+" Eliminado");
                libros.remove(i);
                
                return true;
            }
        }
        
        
        
    return false;
    }
    
    
    
    
    public Libro buscarLibroMasCostoso()
    {
        
        Libro libroCostoso=libros.get(0);
        
        for (int i = 0; i < libros.size(); i++)
        {
            if(libros.get(i).getPrecioVenta()>libroCostoso.getPrecioVenta())
            {
                libroCostoso=libros.get(i);
            }
            
        }
        return libroCostoso;
    }
    
    public Libro buscarLibroMasEconomico()
    {
    
     Libro libroBarato=libros.get(0);
        
        for (int i = 0; i < libros.size(); i++)
        {
            if(libros.get(i).getPrecioVenta()<libroBarato.getPrecioVenta())
            {
                libroBarato=libros.get(i);
            }
            
        }
        return libroBarato;
    }
    
    public boolean abastecer(String fecha,String isbn,int cantidad) throws Exception
    {
        System.out.println("Abastecer");
        if(this.buscarPorIsbn(isbn)==null)
            JOptionPane.showMessageDialog(null, "No existe isbn");
        
        else{
                this.buscarPorIsbn(isbn).abastecer(cantidad, fecha);
                
                System.out.println("Se abastecio el libro"+this.buscarPorIsbn(isbn).getTitulo()+
                        " con "+cantidad+" libros, ahora tiene "+this.buscarPorIsbn(isbn).getCantidadActual()+" libro(s)");
                return true;
        }
        
        
    return false;
    }
    
    public String recorrerLibros()
    {
        String dato="";
        
        for (int i = 0; i < libros.size(); i++) {
            dato+="\n"+libros.get(i).toString();
        }
    
    return dato;
    }
    
    public boolean vender(String fecha,String isbn,int cantidad) throws Exception
    {
        if(this.buscarPorIsbn(isbn)==null)
            JOptionPane.showMessageDialog(null, "No existe isbn");
        
        else{
                this.buscarPorIsbn(isbn).vender(cantidad, fecha);
        System.out.println("Se vendio el libro"+this.buscarPorIsbn(isbn).getTitulo()+
                        " con "+cantidad+" libros, ahora tiene "+this.buscarPorIsbn(isbn).getCantidadActual()+" libro(s)");
                return true;
        }
    
    return false;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
