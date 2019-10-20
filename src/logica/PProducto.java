/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import datos.VCategoria;
import datos.VProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import presentacion.FrmProducto;

/**
 *
 * @author Edwin
 */
public class PProducto {
    
    //conexion y cadena de consulta
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;
    
    
    public DefaultTableModel mostrar(String buscar){
        //Este procedimiento muestra un listado de todos los productos sobre una tabla
        
        DefaultTableModel modelo;
        String [] titulos = {"Codigo","Nombre"};        
        String [] registro = new String[2];
        
        totalregistros=0;
        modelo = new DefaultTableModel(null,titulos)
            {
                boolean [] canEdit= new boolean []{
                    false, false, true, false, false, false,false
                };
        
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex){   
                    return canEdit [columnIndex];
                }
            };
        
        sSQL = "select * from tblproducto  where nombre like '%" + buscar + "%'  order by idProducto desc";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                registro[0] = rs.getString("idProducto");
                registro[1] = rs.getString("nombre");
//                registro[2] = rs.getString("precioCompra");
//                registro[3] = rs.getString("iva");
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
    
    public  String[] cargarDatosEspecificos(String codigo){  //fijate que al tipo de dato de retorno
       
        //el codigo que pide esta funcion es el codigo de un producto en especifico del cual la funcion
        //traera todos los datos relacionados para mostrarlos sobre una plantilla(cajas de texto en el formulario)
        //que podra ser modificada por el usuario
        //todos los datos encontrados se registran en un array de string el cual sera devuelto a la linea donde se invoco
        
        String [] datos = new String[10];
        sSQL="select  prod.idProducto, prod.idCategoria, prod.nombre, prod.precioCompra, prod.precioVenta,"
                + " prod.iva, prod.precioconIva, prod.descripcion, prod.stock, cat.nombre as categoria"
                + " from tblproducto prod inner join tblcategoria cat"
                + " on cat.idCategoria = prod.idCategoria where idProducto = " +codigo;      
       
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                datos[0] = rs.getString("idProducto");
                datos[1] = rs.getString("idCategoria");
                datos[2] = rs.getString("nombre");
                datos[3] = rs.getString("precioCompra");
                datos[4] = rs.getString("precioVenta");
                datos[5] = rs.getString("iva");
                datos[6] = rs.getString("precioconIva");
                datos[7] = rs.getString("descripcion");
                datos[8] = rs.getString("categoria");  
                datos[9] = rs.getString("stock");
                
            }
            
            return datos;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
        
        
    }
    
    public boolean insertar(VProducto datos) {
        
        /*cadena de consulta en la tabla persona*/
        sSQL = "insert into tblproducto(idCategoria, nombre, precioCompra, precioVenta, iva, precioconIva, descripcion, stock, operatividad)"
                + " values (?,?,?,?,?,?,?,?,?)";
        
       
        //datos esperados para insertar
         try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1,datos.getIdCategoria());
            pst.setString(2,datos.getNombre());
            pst.setDouble(3,datos.getPrecioCompra());
            pst.setDouble(4,datos.getPrecioVenta());
            pst.setDouble(5,datos.getIva());
            pst.setDouble(6, datos.getPrecioconIva());
            pst.setString(7,datos.getDescripcion());
            pst.setInt(8,datos.getStock());
            pst.setString(9,datos.getOperatividad());
                        
            
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
    
    public boolean actualizar(VProducto datos) {
        
        /*cadena de consulta en la tabla persona*/
        sSQL = "update tblproducto set idCategoria=?, nombre=?, precioCompra=?, precioVenta=?, iva=?,"
                + " precioconIva=?, descripcion=?, stock=?, operatividad=? where idProducto=?";       
       
        
         try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1,datos.getIdCategoria());
            pst.setString(2,datos.getNombre());
            pst.setDouble(3,datos.getPrecioCompra());
            pst.setDouble(4,datos.getPrecioVenta());
            pst.setDouble(5,datos.getIva());
            pst.setDouble(6,datos.getPrecioconIva());
            pst.setString(7,datos.getDescripcion());
            pst.setInt(8,datos.getStock());
            pst.setString(9,datos.getOperatividad());
            pst.setInt(10,datos.getIdProducto());
            
                        
            
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
    
    public boolean eliminar(VProducto datos){
        //Realmente no elimina ningun dato, solo se pone estado de no operativo
        sSQL = "update tblproducto set operatividad=? where idProducto=?";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1,"N");
            pst.setInt(2,datos.getIdProducto()); //Se envia una N  a este campo pues se acordo que este es el 
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
    
    //posiblemente este metodo no se implemente
    public void llenarListaProducto(String codigo){
        int idcategoria, idproducto,stock;
        String nombreproducto,operatividad,descripcion;
        double preciocompra, iva, precioconiva;
        sSQL = "select pro.idProducto, pro.nombre, pro.precioCompra, pro.precioVenta, pro.iva, pro.precioconIva,pro.descripcion,pro.stock," 
                + " cat.nombre as categoria, cat.idCategoria from tblproducto pro inner join tblcategoria cat"
                + " on pro.idCategoria = cat.idCategoria where pro.idProducto = "+ codigo;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
               // FrmProducto.listProducto.get()
               FrmProducto.txtdescripcion.setText(rs.getString("descripcion"));
               FrmProducto.txtidcategoria.setText(String.valueOf(rs.getInt("idCategoria")));
               FrmProducto.txtiva.setText(String.valueOf(rs.getDouble("iva")));
               
               FrmProducto.txtnombrearticulo.setText(String.valueOf(rs.getString("nombre")));
               FrmProducto.txtnombrecategoria.setText(String.valueOf(rs.getString("categoria")));
               FrmProducto.txtpreciocompra.setText(String.valueOf(rs.getDouble("precioCompra")));
               FrmProducto.txtprecioventa.setText(String.valueOf(rs.getDouble("precioVenta")));
               FrmProducto.txtstock.setText(String.valueOf(rs.getString("stock")));
//                idproducto = rs.getInt("idProducto");
//                nombreproducto = rs.getString("nombre");
//                preciocompra = rs.getDouble("precioCompra");
//                iva = rs.getDouble("iva");
//                precioconiva = rs.getDouble("precioconIva"); 
//                descripcion = rs.getString("descripcion");
//                stock = rs.getInt("stock");
//                operatividad = rs.getString("operatividad");
//                FrmProducto.txtnombrecategoria.setText(rs.getString("categoria"));
//                totalregistros++;
//                
//                FrmProducto.listProducto.add();
//                modelo.addRow(registro);
            }
            
           
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            
        }
        
    }
    
}
