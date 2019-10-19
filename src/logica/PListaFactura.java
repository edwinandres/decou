/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import datos.VListaFactura;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import presentacion.FrmFactura;
import static presentacion.FrmFactura.TABLA;
import static presentacion.FrmFactura.listaArticulos;
import static presentacion.FrmFactura.listatemporal;

/**
 *
 * @author Edwin
 */
public class PListaFactura {
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;
    public static double totalFactura=0;
    
     
    public DefaultTableModel mostrar(String buscar){
        DefaultTableModel modelo;
        String [] titulos = {"Codigo","Nombre","Cantidad", "Precio", "iva", "precioconiva", "total"};
        
        String [] registro = new String[7];
        int codigo = Integer.parseInt(buscar);
        totalregistros=0;
        modelo = new DefaultTableModel(null,titulos);
        sSQL = "select * from tblproducto where idProducto like '" + codigo + "'";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                registro[0] = rs.getString("idProducto");
                registro[1] = rs.getString("nombre");
                registro[2] = "3";
                registro[3] = rs.getString("precioVenta");
                registro[4] = rs.getString("iva"); 
                registro[5] = rs.getString("precioconIva");
                registro[6] = String.valueOf((Double.parseDouble(registro[5]))* (Double.parseDouble(registro[2])));
             
                modelo.addRow(registro);
                
            }
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    
    public void llenarLista(String codigo){
        //crea un arraylist con una lista de articulos listos para facturar
        //devuelve un item mas en la lista
        int cod=0;
        int cant=1;
        String nom="";
        double precio=0, iva=0, precioIva=0, tot=0;
        sSQL = "select * from tblproducto where idProducto like '" + codigo + "'";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                 cod= rs.getInt("idProducto");
                 nom= rs.getString("nombre");
                //int cant= rs.getInt("1");
                // cant =3;
                 precio=rs.getDouble("precioVenta");
                 iva= rs.getDouble("iva");
                // precioIva= rs.getDouble("precioconIva"); 
                precioIva= rs.getDouble("precioconIva");
                 tot= cant*precioIva;            
             
            }
            VListaFactura item = new VListaFactura(cod,nom,cant,precio,iva,precio*1.19,tot);
        
            listatemporal.add(item);
    
    } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            
        }
    }
    
    public void llenartabla(JTable tabla){        
        //LO UNICO QUE SE HACE AQUI, es vaciar todo el contenido del arraylist a la tabla
        
        //se crea una matriz de objetos de las dimensiones de la listaArticulos
        Object matriz[][]=new Object[listatemporal.size()][7];
        
        //se vacia en la matriz todo el contenido de la listaArticulos
        for (int i = 0; i < listatemporal.size(); i++) {
            matriz[i][0]=listatemporal.get(i).getCodigoArticulo();
            matriz[i][1]=listatemporal.get(i).getNombreArticulo();
            matriz[i][2]=listatemporal.get(i).getCantidad();
            matriz[i][3]=listatemporal.get(i).getPrecioArticulo();
            matriz[i][4]=listatemporal.get(i).getIva();
            matriz[i][5]=listatemporal.get(i).getPrecioVenta();
            matriz[i][6]=listatemporal.get(i).getTotalPorArticulo();
            
        //se dibuja dicha matriz en el jTable de los articulos
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            matriz,
            new String [] {
                "Identificacion", "Nombre", "Cantidad", "Precio", "iva", "precioneto","total"
            }
        )
             //hacer editable solo la tercer columna   
            {
                boolean [] canEdit= new boolean []{
                    false, false, true, false, false, false,false
                };
        
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex){   
                    return canEdit [columnIndex];
                }
            }
        );
        
        tabla.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyReleased(java.awt.event.KeyEvent evt){
                tablaKeyReleased(evt);
            }

                private void tablaKeyReleased(KeyEvent evt) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    actualizarCantidad(tabla);
                    llenartabla(tabla);
                    actualizarTotalFactura();
                   
                }
        
        });
        }           
    }
    
    public void actualizarCantidad(JTable tabla){  
        //lo unico que hace es multiplicar para cada articulo, su valor unitario por el total a comprar
    
        for (int i = 0; i < tabla.getRowCount(); i++) {
    //         System.out.println(tabla.getValueAt(i, 2));
    //        System.out.println(tabla.getRowCount()+" getrowcount");
    //        JOptionPane.showMessageDialog(tabla, "hola como mas"+tabla.getValueAt(i, 2));
            listatemporal.get(i).setCantidad(Integer.parseInt(tabla.getValueAt(i,2).toString()));
            listatemporal.get(i).setTotalPorArticulo(listatemporal.get(i).getCantidad()*listatemporal.get(i).getPrecioVenta());
         //  JOptionPane.showMessageDialog(tabla, listatemporal.get(i).getCantidad()+"cantidadddd");
        }
    }
     
    public void actualizarTotalFactura(){
        //simplemente devuelve el total que se genera al sumar el valor de todos los items a llevar
         totalFactura=0;
         double acum= 0;
         double totalsin=0;
         double totalporarticulosin=0;
        for(int i=0; i<listatemporal.size();i++){
            acum=acum+listatemporal.get(i).getTotalPorArticulo();
            totalFactura+=totalFactura + listatemporal.get(i).getTotalPorArticulo();
            //guardar dato sin iva
            totalporarticulosin= listatemporal.get(i).getCantidad()*listatemporal.get(i).getPrecioArticulo();
            totalsin=totalsin+totalporarticulosin;
             
        }
        FrmFactura.txtacumulado.setText(String.valueOf(acum));
        FrmFactura.txttotalsin.setText(String.valueOf(totalsin));
        acum=0;
        totalsin=0;
        
        FrmFactura.txttotalfactura.setText("");
        FrmFactura.txttotalfactura.setText(""+totalFactura);
        totalFactura=0;
    }
    
    public void mostrartotal(JTable TABLAA){
        //simplement despliega el total sobre un txt
        
        //listaArticulos.clear();
        System.out.println(TABLAA.getRowCount());
        totalFactura=0;
        if (TABLAA.getRowCount()>0){
        
        
            for(int i=0; i<= TABLAA.getRowCount();i++){
                
                totalFactura= totalFactura+ Double.parseDouble(TABLAA.getValueAt(i,7).toString());

//                int codigoArticulo=Integer.parseInt(TABLA.getValueAt(i, 0).toString());            
//                String nombreArticulo= (TABLA.getValueAt(i, 0).toString());
//                int cantidad = Integer.parseInt(TABLA.getValueAt(i, 0).toString());
//                double precioArticulo = Double.parseDouble(TABLA.getValueAt(i, 0).toString());
//                double iva = Double.parseDouble(TABLA.getValueAt(i, 0).toString());
//                double precioVenta = Double.parseDouble(TABLA.getValueAt(i, 0).toString());
//                double totalPorArticulo = Double.parseDouble(TABLA.getValueAt(i, 0).toString());      
//
//                VListaFactura lista = new VListaFactura(codigoArticulo,nombreArticulo,cantidad,precioArticulo,iva,precioVenta,totalPorArticulo);
//                listaArticulos.add(lista);
                FrmFactura.txttotalfactura.setText(""+totalFactura);
            }
            totalFactura=0;
        }
    }
     
}
