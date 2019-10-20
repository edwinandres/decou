/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import datos.VFactura;
import datos.VProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import presentacion.FrmCredito;
import presentacion.FrmFactura;

/**
 *
 * @author Edwin
 */
public class PFactura {
    //conexion y cadena de consulta
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    private String sSQL3="";
    public Integer totalregistros;
    public static Integer numeroUltimaFactura;
    
    
    public DefaultTableModel mostrar(String buscar){
        DefaultTableModel modelo;
        String [] titulos = {"Codigo","Nombre de el aticulo"};
        
        String [] registro = new String[2];
        
        totalregistros=0;
        modelo = new DefaultTableModel(null,titulos);
        sSQL = "select * from tblproducto where nombre like '%" + buscar + "%'  order by idProducto desc";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                registro[0] = rs.getString("idProducto");
                registro[1] = rs.getString("nombre");
                
                totalregistros++;
                modelo.addRow(registro);
            }
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    
    public boolean insertar(VFactura datos) {
        
        /*cadena de consulta en la tabla persona*/
        sSQL = "insert into tblventa(idEmpleado, total, fecha, cedulaCliente, tipoVenta)"
                + " values (?,?,?,?,?)";     
       
        //datos esperados para insertar
         try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
           
            pst.setInt(1,datos.getIdEmpleado());
            pst.setDouble(2,datos.getTotal());
            pst.setDate(3,datos.getFecha());
            pst.setString(4,datos.getCedulaCliente());
            pst.setString(5,datos.getTipoVenta());                         
            
            int n = pst.executeUpdate();
            
            if(n!=0){
                //buscamos el ultimo numero de factura ingresado para guardar los detalles de venta de esta factura
                sSQL2="select * from tblventa order by numeroFactura desc limit 1";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sSQL2);
             
                //si encuentra el registro, lleve el numero de factura al campo
                while(rs.next()){
                     numeroUltimaFactura =Integer.parseInt( rs.getString("numeroFactura"));  
                     //FrmFactura.txtultimafact.setText("");
                     //FrmFactura.txtultimafact.setText(" "+ numeroUltimaFactura);
                }
                
                //prepare la consulta de insercion
                sSQL3="insert into tbldetalleventa(numFactura,codigoArticulo,cantidad)" 
                    + " values (?,?,?)";                    
                PreparedStatement pst3 = cn.prepareStatement(sSQL3);
                
                //variable control para validar que se guarden el numero de registros que almacena
                //el array de detalleventa
                int cuantos=0;
                
                for (int i=0; i<FrmFactura.listatemporal.size();i++){
                    
                    pst3.setInt(1, numeroUltimaFactura);
                    pst3.setInt(2, FrmFactura.listatemporal.get(i).getCodigoArticulo());
                    pst3.setInt(3, FrmFactura.listatemporal.get(i).getCantidad());
                    
                    cuantos++;
                    
                    int n3= pst3.executeUpdate();
                }
                
                //si se guardaron todos los registros devuelva un true
                 if(cuantos == FrmFactura.listatemporal.size()){
                     return true;
                 }
                 else{
                     return false;
                 }
                
            }
            else{
                return false;
            }
                    
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
     public boolean insertarRemision(VFactura datos) {
        
        /*cadena de consulta en la tabla persona*/
        sSQL = "insert into tblventa(idEmpleado, total, fecha, cedulaCliente, tipoVenta)"
                + " values (?,?,?,?,?)";     
       
        //datos esperados para insertar
         try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
           
            pst.setInt(1,datos.getIdEmpleado());
            pst.setDouble(2,datos.getTotal());
            pst.setDate(3,datos.getFecha());
            pst.setString(4,datos.getCedulaCliente());
            pst.setString(5,datos.getTipoVenta());                         
            
            int n = pst.executeUpdate();
            
            if(n!=0){
                //buscamos el ultimo numero de factura ingresado para guardar los detalles de venta de esta factura
                sSQL2="select * from tblventa order by numeroFactura desc limit 1";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sSQL2);
             
                //si encuentra el registro, lleve el numero de factura al campo
                while(rs.next()){
                     numeroUltimaFactura =Integer.parseInt( rs.getString("numeroFactura"));  
                     //FrmFactura.txtultimafact.setText("");
                     //FrmFactura.txtultimafact.setText(" "+ numeroUltimaFactura);
                }
                
                //prepare la consulta de insercion
                sSQL3="insert into tbldetalleventa(numFactura,codigoArticulo,cantidad)" 
                    + " values (?,?,?)";                    
                PreparedStatement pst3 = cn.prepareStatement(sSQL3);
                
                //variable control para validar que se guarden el numero de registros que almacena
                //el array de detalleventa
                int cuantos=0;
                
                for (int i=0; i<FrmFactura.listatemporal.size();i++){
                    
                    pst3.setInt(1, numeroUltimaFactura);
                    pst3.setInt(2, FrmFactura.listatemporal.get(i).getCodigoArticulo());
                    pst3.setInt(3, FrmFactura.listatemporal.get(i).getCantidad());
                    
                    cuantos++;
                    
                    int n3= pst3.executeUpdate();
                }
                
                //si se guardaron todos los registros devuelva un true
                 if(cuantos == FrmFactura.listatemporal.size()){
                     return true;
                 }
                 else{
                     return false;
                 }
                
            }
            else{
                return false;
            }
                    
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    public DefaultTableModel mostrarCliente(String buscar){
        DefaultTableModel modelo;
        String [] titulos = {"Nombre","Apellidos","Telefono","Direccion","Correo","Historial"};
        
        String [] registro = new String[6];
        
        totalregistros=0;
        modelo = new DefaultTableModel(null,titulos);
        sSQL = "select cli.codigoPersona , cli.historial , per.apellidos, per.nombre, per.telefono, per.direccion, per.correo "
                +"from tblcliente cli inner join tblpersona per on per.codigoPersona= cli.codigoPersona "
                +"where per.numeroDocumento="+ buscar;
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                registro[0] = rs.getString("nombre");
                registro[1] = rs.getString("apellidos");
                registro[2] = rs.getString("telefono");
                registro[3] = rs.getString("direccion");
                registro[4] = rs.getString("correo"); 
                registro[5] = rs.getString("historial"); 
                
                
                totalregistros++;
                modelo.addRow(registro);
            }
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    
     public DefaultTableModel limpiarTabla(){
        DefaultTableModel modelo;
        String [] titulos = {"Identificacion","Nombre","Cantidad","Precio","Iva","PrecioNeto","Total"};
        
        String [] registro = new String[7];
        
        totalregistros=0;
        modelo = new DefaultTableModel(null,titulos);
//        sSQL = "select * from tblproducto where nombre like '%" + buscar + "%'  order by idProducto desc";
//        
//        try {
//            Statement st = cn.createStatement();
//            ResultSet rs = st.executeQuery(sSQL);
//            
//            while(rs.next()){
//                registro[0] = rs.getString("idProducto");
//                registro[1] = rs.getString("nombre");
//                
//                totalregistros++;
//                modelo.addRow(registro);
                   
//            }
            
            return modelo;
//            
//        } catch (Exception e) {
//            JOptionPane.showConfirmDialog(null, e);
//            return null;
//        }
    }
     
    public int consultarNumeroFactura(){
        
        //consulta el numero de la ultima factura en la base de datos y devuelve (numerodeultimafactura+1)
        //esto para visualizar con anterioridad el numero con el cual quedara la factura
        int numeroFactura=0;
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
           
                //buscamos el ultimo numero de factura ingresado para guardar los detalles de venta de esta factura
                sSQL="select * from tblventa order by numeroFactura desc limit 1";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sSQL);
             
                //si encuentra el registro, lleve el numero de factura al campo
                while(rs.next()){
                     numeroFactura =Integer.parseInt( rs.getString("numeroFactura"));                       
                }          
                    
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);           
        }
        
        return numeroFactura+1;
    }
    
}
