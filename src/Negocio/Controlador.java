/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Modelo.Libro;
import Modelo.Tipo;
import Vista.Abastecer;
import Vista.Eliminar;
import Vista.Principal;
import Vista.Vender;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author santi
 */
public class Controlador implements ActionListener {

    private Principal p;
    private Abastecer a;
    private Vender v;
    private Eliminar e;
    private TiendaLibros tl;
    private Transaccion t;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private String fecha = sdf.format(new Date());
    private double dineroCaja = 1000000;

    public Controlador(Principal p, Abastecer a, Vender v, Eliminar e, TiendaLibros tl, Transaccion t) {
        super();
        this.p = p;
        this.a = a;
        this.v = v;
        this.e = e;
        this.tl = tl;
        this.t = t;
        actionListener(this);
    }

    public void actionListener(ActionListener e) {
        p.btnAbastecer.addActionListener(e);
        p.btnEliminar.addActionListener(e);
        p.btnVender.addActionListener(e);
        p.btnBuscarPorIsbn.addActionListener(e);
        p.btnLibroEconomico.addActionListener(e);
        p.btnBuscarPorTitulo.addActionListener(e);
        p.btnLibroMasVendido.addActionListener(e);
        p.btnLibroCaro.addActionListener(e);
        p.btnRegistrar.addActionListener(e);
        a.jButton1.addActionListener(e);
        
        v.jButton1.addActionListener(e);
        

        this.e.jButton1.addActionListener(e);
        

    }

    public void limpiarRegistrar() {
        p.jtPrecioCompra.setText("");
        p.jtIsbn.setText("");
        p.jtTitulo.setText("");
        p.jtPrecioVenta.setText("");

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {

            if (ae.getActionCommand().contentEquals("Registrar ")) 
            {

                String isbn = p.jtIsbn.getText();
                
                if ((tl.buscarPorIsbn(isbn) != null)) 
                {
                    throw new Exception("ISBN REPETIDO");
                }

                String titulo = p.jtTitulo.getText();
                double precioC = Double.parseDouble(p.jtPrecioCompra.getText());
                double precioV = Double.parseDouble(p.jtPrecioVenta.getText());
                int cantidad = 0;

                tl.registrarLibro(isbn, titulo, precioC, precioV, cantidad);
                p.areaEliminarVender.setText(tl.recorrerLibros());

            }
            if (ae.getActionCommand().contentEquals("Eliminar")) 
            {
                limpiarRegistrar();
                e.setVisible(true);
            }

         
             if (ae.getActionCommand().contentEquals("Vender")) {
                limpiarRegistrar();
                v.setVisible(true);
            }

            if (ae.getActionCommand().contentEquals("Abastecer")) {
                limpiarRegistrar();
                a.setVisible(true);

            }

            if (ae.getActionCommand().contentEquals("Eliminar libro")) 
            {
                if (e.jtIsbn.getText().equals("")) 
                {
                    throw new Exception("No lleno el campo");
                }

                if (tl.buscarPorIsbn(e.jtIsbn.getText()) == null)
                {

                    throw new Exception("No se encuentra el isbn");

                } else 
                {
                    tl.elimnarLibro(e.jtIsbn.getText());

                }
                p.areaEliminarVender.setText(tl.recorrerLibros());
                
                e.setVisible(false);
                e.jtIsbn.setText("");
                
            }
            
            if (ae.getActionCommand().contentEquals("Abatecer libro")) 
            {
                int cantidadAComprar = Integer.parseInt(a.jtCantidad.getText());
                Libro libroQueAbastesco = tl.buscarPorIsbn(a.jtIsbn.getText());

                if (a.jtIsbn.getText().equals("")) 
                {
                    
                    throw new Exception("No lleno el campo ISBN");
                }

                if (libroQueAbastesco == null)
                {
                    a.jtIsbn.setText("");
                    JOptionPane.showMessageDialog(null, "No existe el isbn que ingresó");
                } 
                 else
                {
                    if ((dineroCaja == 0) || (cantidadAComprar * libroQueAbastesco.getPrecioCompra()) > dineroCaja) 
                    {
                        
                        a.jtCantidad.setText("");
                        a.jtIsbn.setText("");
                        a.setVisible(false);
                        throw new Exception("No hay suficiente dinero en caja para realizar el abastecimiento ");
                    }

                    tl.abastecer(fecha, a.jtIsbn.getText(), Integer.parseInt(a.jtCantidad.getText()));
                    System.out.println("Se abastecio el libro " + tl.buscarPorIsbn(a.jtIsbn.getText()).getTitulo());
                    this.dineroCaja = dineroCaja - ((tl.buscarPorIsbn(a.jtIsbn.getText()).getPrecioCompra()) * Integer.parseInt(a.jtCantidad.getText()));
                    String dinero = BigDecimal.valueOf(dineroCaja).toPlainString();
                    if (dineroCaja > 1000000) {
                        p.dinero.setOpaque(true);
                        p.dinero.setText(dinero);
                        p.dinero.setBackground(Color.GREEN);

                    }
                    if (dineroCaja < 1000000) {
                        p.dinero.setOpaque(true);
                        p.dinero.setText(dinero);
                        p.dinero.setBackground(Color.ORANGE);

                    }

                    p.areaEliminarVender.setText(tl.recorrerLibros());
                    p.jTextArea1.setText(tl.transaccionesTotales());
                    
                    a.setVisible(false);
                    a.jtCantidad.setText("");
                    a.jtIsbn.setText("");
                }
                
            }

            if (ae.getActionCommand().contentEquals("Vender libro")) 
            {

                if (v.jtIsbn.getText().equals("")) {

                    throw new Exception("No lleno el campo ISBN");
                }

                Libro libroQueVendo;
                libroQueVendo = tl.buscarPorIsbn(v.jtIsbn.getText());

                if (libroQueVendo == null) {
                    v.jtIsbn.setText("");
                    throw new Exception("Libro no encontrado");

                }
                int cantidadDelLibroABuscar = libroQueVendo.getCantidadActual();

                if (cantidadDelLibroABuscar > 0 && Integer.parseInt(v.jtCantidad.getText()) <= cantidadDelLibroABuscar) {
                    libroQueVendo.setCantidadActual(cantidadDelLibroABuscar - Integer.parseInt(v.jtCantidad.getText()));
                    tl.vender(fecha, v.jtIsbn.getText(), Integer.parseInt(v.jtCantidad.getText()));

                    System.out.println("Se vendio el libro " + libroQueVendo.getTitulo());
                    p.areaEliminarVender.setText(tl.recorrerLibros());
                    this.dineroCaja = dineroCaja + ((tl.buscarPorIsbn(v.jtIsbn.getText()).getPrecioVenta()) * Integer.parseInt(v.jtCantidad.getText()));
                    String dinero = BigDecimal.valueOf(dineroCaja).toPlainString();
                    if (dineroCaja > 1000000) {
                        p.dinero.setOpaque(true);
                        p.dinero.setText(dinero);
                        p.dinero.setBackground(Color.GREEN);
                    } else if (dineroCaja < 1000000) {
                        p.dinero.setOpaque(true);
                        p.dinero.setText(dinero);
                        p.dinero.setBackground(Color.ORANGE);
                    }

                    p.jTextArea1.setText(tl.transaccionesTotales());
                    v.setVisible(false);
                    v.jtCantidad.setText("");
                    v.jtIsbn.setText("");

                } else 
                {
                    v.setVisible(false);
                    v.jtCantidad.setText("");
                    v.jtIsbn.setText("");
                    JOptionPane.showMessageDialog(null, "No puedes comprar sufientes unidades del libro " + libroQueVendo.getTitulo() + " ");
                }
                    
                
            }
           
            
            if (ae.getActionCommand().contentEquals("Buscar por ISBN")) ////
            {

                if (p.jtIsbn1.getText().equals("")) {
                    throw new Exception("Porfavor, escriba un ISBN");
                }

                if (tl.buscarPorIsbn(p.jtIsbn1.getText()) == null) {
                    throw new Exception("El ISBN que ingresó no existe");
                }

                Libro libroQueBusco = tl.buscarPorIsbn(p.jtIsbn1.getText());
                JOptionPane.showMessageDialog(null, "El libro es " + libroQueBusco.getTitulo() + "\n Informacion Del libro=" + libroQueBusco.toString());
            }
            
            
            
            
            if (ae.getActionCommand().contentEquals("Buscar por titulo")) { ////
                if ("".equals(p.jtTitulo1.getText())) {
                    throw new Exception("Porfavor, escriba un titulo");
                }

                if (tl.buscarPorTitulo(p.jtTitulo1.getText()) == null) {
                    throw new Exception("El título que ingresó no existe");
                }

                Libro libroQueBusco = tl.buscarPorTitulo(p.jtTitulo1.getText());
                JOptionPane.showMessageDialog(null, "El libro es " + libroQueBusco.getTitulo() + "\n Informacion Del libro=" + libroQueBusco.toString());
            }
            
            
            
            if (ae.getActionCommand().contentEquals("Libro mas economico")) {
                if (tl.buscarLibroMasEconomico() != null) {
                    JOptionPane.showMessageDialog(null, "El libro mas economico es " + tl.buscarLibroMasEconomico().getTitulo()+" su precio de venta es "+tl.buscarLibroMasEconomico().getPrecioVenta());
                }

               else{

                throw new Exception("No hay libros");
                }
            }
            
            
            
            if (ae.getActionCommand().contentEquals("Libro mas costoso")) {
                if (tl.buscarLibroMasCostoso() != null) {
                    JOptionPane.showMessageDialog(null, "El libro mas costoso es " + tl.buscarLibroMasCostoso().getTitulo()+" su precio de venta es "+tl.buscarLibroMasCostoso().getPrecioVenta());
                }else{

                throw new Exception("No hay libros");
                }
            }
            
            
            
            if (ae.getActionCommand().contentEquals("Libro mas vendido")) {
                if (tl.buscarMasVendido() != null) {
                    JOptionPane.showMessageDialog(null, "El libro mas vendido fue "+tl.buscarMasVendido().getTitulo()+" con "+tl.buscarMasVendido().getNumeroVentas()+" ventas");
                }

                else{

                throw new Exception("No hay libros");
                }
            }
            

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
