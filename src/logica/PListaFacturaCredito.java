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
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import presentacion.FrmCredito;
import presentacion.FrmFactura;
import static presentacion.FrmFactura.TABLA;
import static presentacion.FrmFactura.listaArticulos;
import static presentacion.FrmFactura.listatemporal;
import presentacion.FrmFacturacion;
import static presentacion.FrmFacturacion.txtcuotaremi;
import static presentacion.FrmFacturacion.txtvalorcuota;

/**
 *
 * @author Edwin
 */
public class PListaFacturaCredito {
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;
    public static double totalFactura=0;
    public static double totalConInteres=0;
    
     
    public DefaultTableModel mostrar(String buscar){
        DefaultTableModel modelo;
        //String [] titulos = {"Codigo","Nombre","Cantidad", "Precio", "iva", "precioconiva", "total"};
        String [] titulos = {"r","r","r", "r", "r", "r", "r"};
        
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
                
                totalregistros++;
                System.out.println(totalregistros+"totalregistros");
                System.out.println(registro[0]);
                System.out.println(registro[1]);
                System.out.println(registro[2]);
                System.out.println(registro[3]);
                System.out.println(registro[4]);
                System.out.println(registro[5]);
                System.out.println(registro[6]);
                modelo.addRow(registro);
                
            }
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    
    public void llenarLista(String codigo){
        int cod=0;
        int cant=1;
        String nom="";
        double precio=0 ,iva=0, precioIva=0, tot=0;
        sSQL = "select * from tblproducto where idProducto like '" + codigo + "'";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                 cod= rs.getInt("idProducto");
                 nom= rs.getString("nombre");                
                 precio=rs.getDouble("precioVenta");
                 //iva = rs.getDouble("iva");
                 
                // precioIva= rs.getDouble("precioconIva"); 
                precioIva= rs.getDouble("precioconIva");
                 tot= cant*precio;             
           
                
                
            
           
             
            }
            VListaFactura item = new VListaFactura(cod,nom,cant,precio,iva,precioIva,tot);
        
            listatemporal.add(item);
    
    } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            
        }
    }
    
    public void llenartabla(JTable tabla){        
        
        
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
                    ocultarColumnasTabla(tabla);
                   
                }
        
        });
        }           
    }
    
    public void ocultarColumnasTabla(JTable TABLA) {
        TABLA.getColumnModel().getColumn(0).setMaxWidth(0);
        TABLA.getColumnModel().getColumn(0).setMinWidth(0);
        TABLA.getColumnModel().getColumn(0).setPreferredWidth(0);
        
//        TABLA.getColumnModel().getColumn(1).setMaxWidth(0);
//        TABLA.getColumnModel().getColumn(1).setMinWidth(0);
//        TABLA.getColumnModel().getColumn(1).setPreferredWidth(0);
        
        TABLA.getColumnModel().getColumn(4).setMaxWidth(0);
        TABLA.getColumnModel().getColumn(4).setMinWidth(0);
        TABLA.getColumnModel().getColumn(4).setPreferredWidth(0);
        
        TABLA.getColumnModel().getColumn(5).setMaxWidth(0);
        TABLA.getColumnModel().getColumn(5).setMinWidth(0);
        TABLA.getColumnModel().getColumn(5).setPreferredWidth(0);//TABLA.getColumnModel().getColumn(0).setCellEditor(mipropiatabla.class.);
        //TABLA.getColumnModel().getColumn(0).
    }
    
    public void actualizarCantidad(JTable tabla){        

       for (int i = 0; i < tabla.getRowCount(); i++) {
   //         System.out.println(tabla.getValueAt(i, 2));
   //        System.out.println(tabla.getRowCount()+" getrowcount");
   //        JOptionPane.showMessageDialog(tabla, "hola como mas"+tabla.getValueAt(i, 2));
           listatemporal.get(i).setCantidad(Integer.parseInt(tabla.getValueAt(i,2).toString()));
           listatemporal.get(i).setTotalPorArticulo(listatemporal.get(i).getCantidad()*listatemporal.get(i).getPrecioArticulo());
        //  JOptionPane.showMessageDialog(tabla, listatemporal.get(i).getCantidad()+"cantidadddd");
       }
    }
     
    public void actualizarTotalFactura(){
         
        DecimalFormat df;
        df= new DecimalFormat("#.##");
        
      
       
        totalFactura=0;         
        double acum= 0;
        double totalsin=0;  ///este es el subtotal
        double totalporarticulosin=0;
        double impuestos = 0;
        
        for(int i=0; i<listatemporal.size();i++){
            acum=acum+listatemporal.get(i).getTotalPorArticulo();
            totalFactura+=totalFactura + listatemporal.get(i).getTotalPorArticulo();           
            //guardar dato sin iva
            totalporarticulosin= listatemporal.get(i).getCantidad()*listatemporal.get(i).getPrecioArticulo();
            totalsin=totalsin+totalporarticulosin;
            impuestos= impuestos + listatemporal.get(i).getIva();
        }
        
        //muestra total y total mas el 10 por ciento de credito
        FrmFacturacion.txtacumulado.setText(String.valueOf(acum));
        
        double totalNeto= acum*1.10;
        double totalNetoF=(double)Math.round(totalNeto * 100d) / 100d;
        FrmFacturacion.txttotalconinteres.setText((double)Math.round(totalNeto * 100d) / 100d+"");
        //========================================================
        FrmFacturacion.txtsubtotalremi.setText(String.valueOf(totalsin));
        FrmFacturacion.txtsubtotalcredito.setText(String.valueOf(totalsin));
        FrmFacturacion.txtintereses.setText(String.valueOf(totalsin*0.10));
        double totalNetoFacturacion= totalsin+(totalsin*0.10);
        double totalconintereses = totalsin*1.10;
        FrmFacturacion.txttotalconinteres.setText(df.format(totalconintereses));
        
        //FrmFacturacion.txtimpuestos.setText(String.valueOf(impuestos));
        
        double interesremi= totalsin*0.10;
        FrmFacturacion.txttotalnetoremi.setText(String.valueOf(totalsin+interesremi));
        FrmFacturacion.txtinteresesremi.setText(String.valueOf(interesremi));
        FrmFacturacion.txtcuotaremi.setText(String.valueOf((totalsin+interesremi)/FrmFacturacion.numerodeCuotas));
        FrmFacturacion.txtvalorcuota.setText(String.valueOf((totalNetoFacturacion)/FrmFacturacion.numerodeCuotas));
        acum=0;
        totalsin=0;
       
        //acum=0;     
        totalFactura=0;
        
        
        
    }
    
     public void mostrartotal(JTable TABLAA){
       
        totalFactura=0;
        
        if (TABLAA.getRowCount()>0){
        
        
            for(int i=0; i<= TABLAA.getRowCount();i++){
                
                totalFactura= totalFactura+ Double.parseDouble(TABLAA.getValueAt(i,7).toString());
             
            }
            
            
            
            totalFactura=0;
            
        }
    }
     
}
