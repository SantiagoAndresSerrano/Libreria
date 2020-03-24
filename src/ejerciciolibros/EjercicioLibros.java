/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciolibros;

import Negocio.Controlador;
import Negocio.TiendaLibros;
import Negocio.Transaccion;
import Vista.Abastecer;
import Vista.Eliminar;
import Vista.Principal;
import Vista.Vender;

/**
 *
 * @author santi
 */
public class EjercicioLibros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    Principal p=new Principal();
    Abastecer a=new Abastecer();
    Vender v=new Vender();
    Eliminar e=new Eliminar();
    TiendaLibros tl=new TiendaLibros();
    Transaccion t=new Transaccion();
    
    
    Controlador c=new Controlador(p,a,v,e,tl,t);
    }
    
}
