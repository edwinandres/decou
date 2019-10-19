/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import datos.VCategoria;
import datos.VCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Edwin
 */
public class PCategoria {
    
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;
    
     
    public DefaultTableModel mostrar(String buscar){
        DefaultTableModel modelo;
        String [] titulos = {"Codigo","Nombre de la categoria"};
        
        String [] registro = new String[2];
        
        totalregistros=0;
        modelo = new DefaultTableModel(null,titulos)
            {
                boolean [] canEdit= new boolean []{
                    false, false
                };
        
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex){   
                    return canEdit [columnIndex];
                }
            };
        sSQL = "select idCategoria, nombre from tblcategoria where nombre like '%" + buscar + "%'  and operatividad='S' order by idCategoria desc";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                registro[0] = rs.getString("idCategoria");
                registro[1] = rs.getString("nombre");
                /*registro[2] = rs.getString("descripcion");
                registro[3] = rs.getString("unidadMedida");
                registro[4] = rs.getString("precioVenta");               
                */
                totalregistros++;
                modelo.addRow(registro);
            }
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }   
    
    public  String[] cargarDatosEspecificos(String codigo){  //fijate que al tipo de dato de retorno
       
        String [] datos = new String[3];
        sSQL="select idCategoria, nombre, descripcion from tblcategoria where idCategoria="+codigo;
      
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                datos[0] = rs.getString("idCategoria");
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("descripcion");
                
            }
            
            return datos;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
        
        
    }
    
    public boolean insertar(VCategoria datos) {
        
        /*cadena de consulta en la tabla persona*/
        sSQL = "insert into tblcategoria(nombre, descripcion, operatividad)"
                + " values (?,?,?)";
        
       
        
         try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1,datos.getNombre());
            pst.setString(2,datos.getDescripcion());
            pst.setString(3,datos.getOperatividad());
                        
            
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
    
    public boolean actualizar(VCategoria datos) {
        
        /*cadena de consulta en la tabla persona*/
        sSQL = "update tblcategoria set nombre=?, descripcion=? where idCategoria=?";       
       
        
         try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1,datos.getNombre());
            pst.setString(2,datos.getDescripcion());
            pst.setInt(3,datos.getIdCategoria());
                        
            
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
    
    public boolean eliminar(VCategoria datos){
        //Realmente no elimina ningun dato, solo se pone estado de no operativo
        sSQL = "update tblcategoria set operatividad=? where idCategoria=?";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1,"N");
            pst.setInt(2,datos.getIdCategoria()); //Se envia una N  a este campo pues se acordo que este es el 
                                  //simbolo para detonar una categoria No operativa           
                        
            
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
