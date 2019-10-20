/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import java.awt.event.KeyEvent;
import datos.VAbono;
import datos.VCredito;
import datos.VListaAbono;
import datos.VListaCredito;
import datos.VListaFactura;
import datos.VProducto;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import presentacion.FrmAbonar;
import static presentacion.FrmFactura.listatemporal;

/**
 *
 * @author Edwin
 */
public class PAbono{
    
    //conexion y cadena de consulta
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;
    public static ArrayList<VListaCredito> listaCreditos = new ArrayList<>();
    public static ArrayList<VListaAbono> listaAbonos = new ArrayList<>();
    
    
    public DefaultTableModel mostrarListaCreditos(String buscar){
        DefaultTableModel modelo;        
        
        String [] titulos = {"IdCredito","Total credito","apellidos","nombre","Telefono"};        
        String [] registro = new String[5];        
        totalregistros=0;
        
        modelo = new DefaultTableModel(null,titulos)               
                
                //hace que las celdas no sean editables
            {
                boolean [] canEdit= new boolean []{
                    false, false, false, false, false
                };
        
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex){   
                    return canEdit [columnIndex];
                }
            };   
        
        //sSQL2 = "select * from tblproducto where nombre like '%" + buscar + "%'  order by idProducto desc";
        sSQL= "SELECT per.nombre, per.apellidos,per.telefono, cre.idCredito, ven.total from tblpersona per inner join tblventa ven "
                + "on ven.cedulaCliente= per.numeroDocumento INNER JOIN tblcredito cre "
                + "on cre.numFactura= ven.numeroFactura where ven.tipoVenta=\"credito\" AND ven.cedulaCliente="+ buscar;
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                registro[0] = rs.getString("idCredito");
                registro[1] = rs.getString("total");
                registro[2] = rs.getString("apellidos");
                registro[3] = rs.getString("nombre");
                registro[4] = rs.getString("telefono"); 
                
                FrmAbonar.txtbuscarnombre.setText(registro[3]+" "+registro[2]);
                
                totalregistros++;
                modelo.addRow(registro);
            }
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrarListaAbonos(String buscar){
        DefaultTableModel modelo;        
        
        String [] titulos = {"Idabono","Fecha","Total","Cliente"};        
        String [] registro = new String[4];        
        totalregistros=0;
        
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
        sSQL= "select cre.idCredito,abo.numCuota, abo.notaCobranza ,abo.fecha, abo.valorAbono " +
        "from tblpersona per inner join tblventa ven on ven.cedulaCliente= per.identificacion inner join tblcredito cre " +
        "on cre.numFactura=ven.numeroFactura inner join tblabono abo on abo.idCredito= cre.idCredito " +
        "where cre.idCredito="+ buscar;
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                registro[0] = rs.getString("apellidos");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("idCredito");
                registro[3] = rs.getString("total");
                //registro[4] = rs.getString("precioVenta");               
                
                
                totalregistros++;
                modelo.addRow(registro);
            }
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
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
                   
                   
                }
        
        });
        }           
    }
    
    public double llenarListaAbonos(String idabuscar){
        
        listaAbonos.clear();
        //variables con los que se llenara el arraylist de la clase listaabonos
        int idcredito=0;
        int idabono=0;        
        double valorpagado=0;
        Date fecha=null;
        double totalAbonado=0;
        
        //consulta sql que traera todos los datos requeridos para llenar la listaabonos
       sSQL= "SELECT abo.idAbono, abo.fecha, abo.valorAbono, abo.idCredito  from tblabono abo WHERE abo.idCredito ="+ idabuscar;
        
       //ejecucion de la consulta
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                //se cargan los valores consultados en variables
                idcredito= rs.getInt("idCredito");
                idabono= rs.getInt("idAbono");               
                valorpagado= rs.getDouble("valorAbono"); 
                totalAbonado+= valorpagado;
                fecha= rs.getDate("fecha");
                
                //se crea un objeto de tipo listaabono y se añade a la lista de abonos
                VListaAbono item = new VListaAbono(idcredito,idabono,valorpagado,fecha);        
                listaAbonos.add(item);
            }
            //return totalAbonado;
    
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            
        }
        return totalAbonado;
    
    }
    
    public void desplegarListaAbonos(JTable tabla){
        
        //se crea una matriz de objetos de las dimensiones de la listaArticulos
        Object matriz[][]=new Object[listaAbonos.size()][4];
        
        //se vacia en la matriz todo el contenido de la listaArticulos
        for (int i = 0; i < listaAbonos.size(); i++) {
            matriz[i][0]=listaAbonos.get(i).getIdCredito();
            matriz[i][1]=listaAbonos.get(i).getIdAbono();
            matriz[i][2]=listaAbonos.get(i).getValorPagado();
            matriz[i][3]=listaAbonos.get(i).getFecha();
            
           
            
        //se dibuja dicha matriz en el jTable de los articulos
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            matriz,
            new String [] {
                "No. credito", "Id abono","Abono","Fecha"
            }
        )
             //hacer editable solo la tercer columna   
            {
                boolean [] canEdit= new boolean []{
                    false, false,false, false
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
                   
                   
                }
        
        });
        }         
    }
    
    
    public boolean insertarAbono(VAbono datos) {
        
        /*cadena de consulta en la tabla persona*/
        sSQL = "insert into tblabono(idCredito,fecha,valorAbono,numCuota,notaCobranza)"
                + " values (?,?,?,?,?)";
        
       
        //datos esperados para insertar
         try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1,datos.getIdCredito());
            pst.setDate(2,datos.getFecha());
            pst.setDouble(3,datos.getValorAbono());
            pst.setInt(4,datos.getNumCuota());
            pst.setString(5,datos.getNotaCobranza());
            //pst.setString(6, datos.getOperatividad());            
                        
            
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
    
    public double consultarValorAcordado(String idcredito){
    
        double valorAcordado=0;
        sSQL="select cred.valorCuota from tblcredito cred where idcredito="+idcredito;
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                valorAcordado = rs.getDouble("valorCuota");
                
               
            }
            
            return valorAcordado;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return 0;
        }
    }
    
}
