/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import datos.VCredito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import presentacion.FrmFactura;

/**
 *
 * @author Edwin
 */
public class PCredito {
    //conexion y cadena de consulta
        private Conexion mysql = new Conexion();
        private Connection cn = mysql.conectar();
        private String sSQL = "";
        private String sSQL2 = "";
        private String sSQL3="";
        public Integer totalregistros;
       // public Integer numeroUltimaFactura;
        
    public boolean insertar(VCredito datos) {
        
        
        //cadena de insercion en la tabla credito, se invoca luego de guardar tblventa y tbldetalleventa
        sSQL = "insert into tblcredito(numFactura, numeroCuotas, valorCuota, estado, referencia1, referencia2, caducidad, consideraciones)"
                + " values ((select numeroFactura from tblventa order by numeroFactura desc limit 1),?,?,?,?,?,?,?)";
       
               
        //datos esperados para insertar
         try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
           
            //pst.setInt(1,PFactura.numeroUltimaFactura);
            pst.setInt(1,datos.getNumeroCuotas());
            pst.setDouble(2,datos.getValorCuota());
            pst.setString(3,datos.getEstado());
            pst.setString(4,datos.getReferencia1());
            pst.setString(5,datos.getReferencia2());
            pst.setString(6,datos.getCaducidad());
            pst.setString(7,datos.getConsideraciones());                        
            
            //efectuar insercion
            int n = pst.executeUpdate();
            
            if(n!=0){
                return true;                
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
        sSQL = "select cli.identificacion , cli.historial , per.apellidos, per.nombre, per.telefono, per.direccion, per.correo "
                +"from tblcliente cli inner join tblpersona per on per.identificacion= cli.identificacion "
                +"where cli.identificacion="+ buscar;
        
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
    
    public boolean actualizar(VCredito datos) {
        
        /*cadena de consulta en la tabla persona*/
        sSQL = "update tblcredito set estado=? where idcredito=?";       
       
        
         try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1,datos.getEstado());
            pst.setInt(2,datos.getIdCredito());
           
                        
            
            int n = pst.executeUpdate();
            
            if(n!=0){
                return true;                
            }
            else{
                return false;
            }
                    
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
}
