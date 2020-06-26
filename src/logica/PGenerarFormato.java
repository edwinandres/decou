/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author Edwin
 */
public class PGenerarFormato {
    
    public String generarEncabezado(String fecha, String numeroFactura, String cliente, String nit, String telefono){
        String encabezado="";
        encabezado= fecha +"\r\n"+ "Factura numero: "+ numeroFactura+"\r\nDECOU\r\n"
                +"Telefono: 2772412\r\n"
                +"NIT: 1036654780\r\n"
                +"Direccion: cra 49 #41-53\r\n"
                +"\r\nCliente: "+cliente
                +"\r\nNit: "+nit
                +"\r\nTelefono: "+ telefono
                +"\r\n";
        return encabezado;
    }
    
    public String generarPiedePagina(){
        String piedePagina;
        piedePagina="No somos autoretenedores\r\nDespues de dos dias de su compra no se aceptan reclamos";
        
        return piedePagina;
    }
}
