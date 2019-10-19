/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Edwin
 */
public class PInformes {
    
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    
    public DefaultTableModel mostrarListaCreditos(String buscar){
        DefaultTableModel modelo;        
        
        String [] titulos = {"IdCredito","Cedula","Cliente","Fecha"};        
        String [] registro = new String[4];        
        
        
        modelo = new DefaultTableModel(null,titulos)               
                
                //hace que las celdas no sean editables
            {
                boolean [] canEdit= new boolean []{
                    false, false, false,false
                };
        
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex){   
                    return canEdit [columnIndex];
                }
            };   
        
        //sSQL2 = "select * from tblproducto where nombre like '%" + buscar + "%'  order by idProducto desc";
        sSQL= "select cre.idCredito, ven.cedulaCliente, concat_ws(' ,', nombre, apellidos) as persona, cre.caducidad "
                + "FROM tblcredito cre inner join tblventa ven on ven.numeroFactura= cre.numFactura "
                + "INNER JOIN tblpersona per on per.numeroDocumento= ven.cedulaCliente "
                + " where cre.caducidad like '%" + buscar + "%'  order by caducidad desc";
        
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                registro[0] = rs.getString("idCredito");
                registro[1] = rs.getString("cedulaCliente");
                registro[2] = rs.getString("persona");
                registro[3] = rs.getString("caducidad");
                //registro[4] = rs.getString("precioVenta");               
                
                
                modelo.addRow(registro);
            }
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    
     public  String[] cargarDatosEspecificos(String codigo){  //fijate que al tipo de dato de retorno
       
        //el codigo que pide esta funcion es el codigo de un producto en especifico del cual la funcion
        //traera todos los datos relacionados para mostrarlos sobre una plantilla(cajas de texto en el formulario)
        //que podra ser modificada por el usuario
        //todos los datos encontrados se registran en un array de string el cual sera devuelto a la linea donde se invoco
        
        String [] datos = new String[12];
        sSQL="SELECT cre.idCredito,cre.caducidad, cre.consideraciones, ven.total, ven.cedulaCliente , concat_ws(' ,', nombre, apellidos) as persona,"
                + " ven.numeroFactura,cre.referencia1, cre.referencia2, cre.numeroCuotas, SUM(abo.valorAbono) as Totalabonado, COUNT(abo.numCuota) as"
                + " CuotasPagadas from tblcredito cre INNER JOIN tblventa ven ON ven.numeroFactura=cre.numFactura"
                + " INNER JOIN tblpersona per ON per.numeroDocumento=ven.cedulaCliente INNER JOIN tblabono abo"
                + " on abo.idCredito= cre.idCredito where cre.idCredito =" +codigo;      
       
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                
                datos[0] = rs.getString("idCredito");  
                datos[1] = rs.getString("persona");
                datos[2] = rs.getString("total");                
                datos[3] = rs.getString("Totalabonado");
                if (datos[3]==null){ datos[3]="0"; }//si no hay abonos, el null genera error, el cero no                
                datos[4] = rs.getString("CuotasPagadas");
                int cuotasporpagar= Integer.parseInt(rs.getString("numeroCuotas"))-Integer.parseInt(datos[4]);
                datos[5] = rs.getString("consideraciones");               
                datos[6] = rs.getString("caducidad");               
                double saldorestante= Double.parseDouble(datos[2])-Double.parseDouble(datos[3]);               
                datos[7] = saldorestante+"";   
                datos[8] = rs.getString("numeroFactura");
                datos[9] = cuotasporpagar+"";
                datos[10] = rs.getString("referencia1");
                datos[11] = rs.getString("referencia2");
                
            }
            
            return datos;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
        
        
    }
}
