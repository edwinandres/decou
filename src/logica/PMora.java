/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import datos.VMora;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Edwin
 */
public class PMora {
    
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;
    public int contar;
    
    public DefaultTableModel mostrar(String buscar){
        //Este procedimiento muestra un listado de todos los productos sobre una tabla
        
        DefaultTableModel modelo;
        String [] titulos = {"IdCredito","Total Credito","Nombre","Cedula","Idmora"};        
        String [] registro = new String[5];
        
        totalregistros=0;
        modelo = new DefaultTableModel(null,titulos)
            {
                boolean [] canEdit= new boolean []{
                    false, false, false,false, false
                };
        
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex){   
                    return canEdit [columnIndex];
                }
            };
        sSQL = "SELECT DISTINCT mora.idMora, cre.idcredito, concat_ws(' ', per.nombre, per.apellidos) as nombre , ven.total, per.numeroDocumento "
                + "from tblcredito cre inner join tblventa ven on cre.numFactura = ven.numeroFactura "
                + "inner join tblpersona per on ven.cedulaCliente = per.numeroDocumento "
                + "inner join tblmora mora on mora.idCredito = cre.idcredito where mora.operatividad=\"S\"";
        
//        sSQL = "SELECT DISTINCT cre.idcredito, concat_ws(' ', per.nombre, per.apellidos) as nombre , ven.total, per.numeroDocumento "
//                + "from tblcredito cre inner join tblventa ven on cre.numFactura = ven.numeroFactura "
//                + "inner join tblpersona per on ven.cedulaCliente = per.numeroDocumento "
//                + "inner join tblmora mora on mora.idCredito = cre.idcredito where mora.operatividad=\"S\"";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                registro[0] = rs.getString("idcredito");
                registro[1] = rs.getString("total");
                registro[2] = rs.getString("nombre");
                registro[3] = rs.getString("numeroDocumento");
                registro[4] = rs.getString("idMora"); 
//                registro[5] = rs.getString("descripcion");
//                registro[6] = rs.getString("stock");
//                registro[7] = rs.getString("operatividad");
                
                totalregistros++;
                modelo.addRow(registro);
            }
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarCuotasConMora(String buscar){
        //Este procedimiento muestra un listado de todos los productos sobre una tabla
        
        DefaultTableModel modelo;
        String [] titulos = {"Numero Cuota","Fecha","Valor","ValorAbonado","idmora"};        
        String [] registro = new String[5];
        
        totalregistros=0;
        modelo = new DefaultTableModel(null,titulos)
            {
                boolean [] canEdit= new boolean []{
                    false, false, false, false, false
                };
        
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex){   
                    return canEdit [columnIndex];
                }
            };
        
        
        
        sSQL = "select DISTINCT mora.idMora,mora.numeroCuota, mora.fecha, mora.totalCuota, mora.idCredito,mora.totalabonado from tblabono abo "
                + "inner join tblcredito cre on abo.idCredito =cre.idcredito "
                + "inner join tblmora mora on mora.idCredito=cre.idcredito "
                + "where mora.idMora="+buscar+" and mora.operatividad=\"S\" and abo.valorAbono=0";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                registro[0] = rs.getString("numeroCuota");
                registro[1] = rs.getString("fecha");
                registro[2] = rs.getString("totalCuota");
                registro[3] = rs.getString("totalabonado");
                registro[4] = rs.getString("idMora");
               //registro[3] = rs.getString("numeroDocumento");
//                registro[4] = rs.getString("precioconIva"); 
//                registro[5] = rs.getString("descripcion");
//                registro[6] = rs.getString("stock");
//                registro[7] = rs.getString("operatividad");
                
                totalregistros++;
                modelo.addRow(registro);
            }
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    
    public boolean insertar(VMora datos) {
        
        /*cadena de consulta en la tabla persona*/
        sSQL = "insert into tblmora(idCredito,numeroCuota,operatividad,totalcuota,totalabonado,adeuda,fecha)"
                + " values (?,?,?,?,?,?,?)";
        
//        /*cadena de consulta en la tabla cliente*/
//        sSQL2 = "insert into tblcliente(codigoPersona, historial, sector)"
//                + " values ((select codigoPersona from tblpersona order by codigoPersona desc limit 1),?,?)";
//        
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);            

            pst.setInt(1, datos.getIdCredito());
            pst.setInt(2, datos.getNumeroCuota());
            pst.setString(3, datos.getOperatividad());
            pst.setDouble(4, datos.getTotalcuota());
            pst.setDouble(5, datos.getTotalabonado());
            pst.setDouble(6, datos.getAdeuda());
            pst.setDate(7, datos.getFecha());
//            pst.setString(7, datos.getCorreo());
//            pst.setString(8, datos.getOperatividad());   
            
//           
//
//            pst2.setString(1, datos.getHistorial());
//            pst2.setString(2, datos.getSector());
            
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
     
    public boolean actualizar(VMora datos) {
        
        /*cadena de consulta en la tabla persona*/
        sSQL = "update tblmora set totalabonado=?, adeuda=? where idmora=?";       
       
        
         try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setDouble(1,datos.getTotalabonado());
            pst.setDouble(2,datos.getAdeuda());
            pst.setDouble(3,datos.getIdMora());
           // pst.setDouble(4,datos.getIdMora());
//            pst.setDouble(5,datos.getIva());
//            pst.setDouble(6,datos.getPrecioconIva());
//            pst.setString(7,datos.getDescripcion());
//            pst.setInt(8,datos.getStock());
//            pst.setString(9,datos.getOperatividad());
//            pst.setInt(10,datos.getIdProducto());
            
                        
            
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
    
    public boolean eliminar(VMora datos){
        //Realmente no elimina ningun dato, solo se pone estado de no operativo
        sSQL = "update tblmora set operatividad=? where idmora=?";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1,"N");
            pst.setInt(2,datos.getIdMora()); //Se envia una N  a este campo pues se acordo que este es el 
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
    
   
    
    public static void reiniciarJTable(javax.swing.JTable Tabla){
        DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
        while(modelo.getRowCount()>0)modelo.removeRow(0);
 
        TableColumnModel modCol = Tabla.getColumnModel();
        while(modCol.getColumnCount()>0)modCol.removeColumn(modCol.getColumn(0));
    }
    
}
