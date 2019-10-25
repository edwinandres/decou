/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.VCredito;
import datos.VFactura;
import datos.VListaFactura;
import datos.VProducto;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowStateListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import logica.PCategoria;
import logica.PCredito;
import logica.PEmpleado;
import logica.PFactura;
import logica.PGenerarFormato;
import logica.PListaFactura;
import logica.PListaFacturaCredito;
import logica.PManejoDocumento;
import logica.PProducto;
import logica.mipropiatabla;
import static presentacion.FrmAlertaCobro.diaInforme;
import static presentacion.FrmFactura.listatemporal;
import static presentacion.FrmProducto.txtidcategoria;

/**
 *
 * @author Edwin
 */
public class FrmFacturacion extends javax.swing.JInternalFrame {

    public static ArrayList<VListaFactura> listaArticulos = new ArrayList<>();
    public static ArrayList<VListaFactura> listatemporal = new ArrayList<>();
    public static String codigoarticulocredito;
    public static String diaseleccionado;
    public static int numerodeCuotas=1;
    public static boolean isVisible = true;
    static boolean esvisible = true;
    /**
     * Creates new form FrmAbonar
     */
    public FrmFacturacion() {      

        //setClosable(true);
        initComponents();
        
        
        txtultimafact.setVisible(false);
        txttotalfactura.setVisible(false);
        Calendar c2 = new GregorianCalendar();
        escogerfecha.setCalendar(c2);
        //tablacliente.setVisible(false);
        PFactura numFactura = new PFactura(); 
        lblnumerofact.setText(String.valueOf(numFactura.consultarNumeroFactura()));
        txtcaducidad.setText(asignarFechas());
        comboCuotas.setSelectedItem(1);
        txtnumerocuotas.setText(comboCuotas.getSelectedItem().toString());
        //comboCuotasActionPerformed(evt);
         jPanel8.setVisible(false);
         jPanel1.setVisible(false);//se oculta el panel de venta de contado
        desactivarCampos();
        ocultarColumnas();
    }
    
    void validarDatosCrediticios(){
        if(!txtnombreref1.getText().isEmpty()){
            
            
        }
    }
    
    public void actualizarCuotas(){
         DecimalFormat df;
        df= new DecimalFormat("#.##");
        double valorcuota = asignarValorCuota();
        double valorcuotaremi = asignarValorCuotaRemi();
      
       txtvalorcuota.setText(df.format(valorcuota));
       txtcuotaremi.setText(df.format(valorcuotaremi));
    }
    
    public static void limpiarCampos(){
//        listatemporal.clear();
//        listaArticulos.clear();
        FrmFactura.listatemporal.clear();
//        FrmCredito.listatemporal.clear();
        PFactura funciones = new PFactura();       
        TABLA.setModel(funciones.limpiarTabla());
        txtnitcliente.setText(null);
        txtnombrecliente.setText(null);
        txttelefonocliente.setText(null);
        txtacumulado.setText(null);
        
        //txtcaducidad.setText(null);
        txtconsideraciones.setText(null);
        //txtidcategoria.setText(null);
        txtnombreref1.setText(null);
        txtnombreref2.setText(null);
        txtnumerocuotas.setText(null);
        txttelefonoref1.setText(null);
        txttelefonoref2.setText(null);
        txttotalconinteres.setText(null);
        txttotalfactura.setText(null);
        txtvalorcuota.setText(null);
        txtimpuestos.setText(null);
        txtsubtotalcredito.setText(null);
        txtintereses.setText(null);
        txtsubtotalremi.setText(null);
        txtinteresesremi.setText(null);
        txttotalnetoremi.setText(null);
        txtcuotaremi.setText(null);
        
                
        //txtultimafact.setText(null);
        
    }
    
    public static void desactivarCampos(){
//        listatemporal.clear();
//        listaArticulos.clear();
        FrmFactura.listatemporal.clear();
//        FrmCredito.listatemporal.clear();
        PFactura funciones = new PFactura();       
        TABLA.setModel(funciones.limpiarTabla());
        txtnitcliente.setEnabled(false);        
        txtnombrecliente.setEnabled(false);
        txttelefonocliente.setEnabled(false);
        
        
        txtacumulado.setEnabled(false);
        
        txtcaducidad.setEnabled(false);
        txtconsideraciones.setEnabled(false);
        //txtidcategoria.setText(null);
        txtnombreref1.setEnabled(false);
        txtnombreref2.setEnabled(false);
        txtnumerocuotas.setEnabled(false);
        txttelefonoref1.setEnabled(false);
        txttelefonoref2.setEnabled(false);
        txttotalconinteres.setEnabled(false);
        txttotalfactura.setEnabled(false);
        txtvalorcuota.setEnabled(false);
        txtimpuestos.setEnabled(false);
        txtsubtotalcredito.setEnabled(false);
        txtintereses.setEnabled(false);
        txtsubtotalremi.setEnabled(false);
        txtinteresesremi.setEnabled(false);
        txttotalnetoremi.setEnabled(false);
        txtcuotaremi.setEnabled(false);
        btnagregar.setEnabled(false);        
        //txtultimafact.setText(null);
        btnguardar.setEnabled(false);
        btnremision.setEnabled(false);
        
    }
    
    public static void activarCampos(){
//        listatemporal.clear();
//        listaArticulos.clear();
        FrmFactura.listatemporal.clear();
//        FrmCredito.listatemporal.clear();
        PFactura funciones = new PFactura();       
        TABLA.setModel(funciones.limpiarTabla());
        txtnitcliente.setEnabled(true);
        txtnombrecliente.setEnabled(true);
        txttelefonocliente.setEnabled(true);
        txtacumulado.setEnabled(true);
        
        txtcaducidad.setEnabled(true);
        txtconsideraciones.setEnabled(true);
        //txtidcategoria.setText(null);
        txtnombreref1.setEnabled(true);
        txtnombreref2.setEnabled(true);
        txtnumerocuotas.setEnabled(true);
        txttelefonoref1.setEnabled(true);
        txttelefonoref2.setEnabled(true);
        txttotalconinteres.setEnabled(true);
        txttotalfactura.setEnabled(true);
        txtvalorcuota.setEnabled(true);
        txtimpuestos.setEnabled(true);
        txtsubtotalcredito.setEnabled(true);
        txtintereses.setEnabled(true);
        txtsubtotalremi.setEnabled(true);
        txtinteresesremi.setEnabled(true);
        txttotalnetoremi.setEnabled(true);
        txtcuotaremi.setEnabled(true);
        btnagregar.setEnabled(true);
        btnguardar.setEnabled(true);
        btnremision.setEnabled(true);   
        
        //txtultimafact.setText(null);
        
    }
    
    boolean verificarCamposContado(){

    return !(txtsubtotalremi.getText().isEmpty()
//            || txtcaducidad.getText().isEmpty() 
//            || txtconsideraciones.getText().isEmpty()
//            || txtidcategoria.getText().isEmpty() 
            || txtnitcliente.getText().isEmpty()
            || txtnombrecliente.getText().isEmpty()
//            || txtnombreref1.getText().isEmpty()
//            || txtnombreref2.getText().isEmpty()
            || txttelefonocliente.getText().isEmpty());
//            || txttelefonoref1.getText().isEmpty()
//            || txttelefonoref2.getText().isEmpty());
//            || txttotalfactura.getText().isEmpty()
            //|| txtultimafact.getText().isEmpty()            
           // || txtnumerocuotas.getText().isEmpty());
            //no se valida que el campo descripcion este diligenciado pues se permite que el producto
            //no cuente con una descripcion

    }  
    
    boolean verificarCampos(){

    return !(txtsubtotalcredito.getText().isEmpty()
            || txtintereses.getText().isEmpty() 
            || txttotalconinteres.getText().isEmpty()
            || txtvalorcuota.getText().isEmpty() 
            || txtnitcliente.getText().isEmpty()
            || txtnombrecliente.getText().isEmpty()
            || txtnombreref1.getText().isEmpty()
            || txtnombreref2.getText().isEmpty()
            || txttelefonocliente.getText().isEmpty()
            || txttelefonoref1.getText().isEmpty()
            || txtcaducidad.getText().isEmpty()
            || txttelefonoref2.getText().isEmpty());
//            || txttotalfactura.getText().isEmpty()
            //|| txtultimafact.getText().isEmpty()            
           // || txtnumerocuotas.getText().isEmpty());
            //no se valida que el campo descripcion este diligenciado pues se permite que el producto
            //no cuente con una descripcion

    }  
    
    public void limpiarTabla(){
        DefaultTableModel tb = (DefaultTableModel) TABLA.getModel();
        int a = TABLA.getRowCount()-1;
        for (int i = a; i >= 0; i--) {           
        tb.removeRow(tb.getRowCount()-1);
        }
    }
    
    
//    void cargarDatosCliente(){
//        txtnombrecliente.setText(tablacliente.getValueAt(0, 0).toString()+" "+ tablacliente.getValueAt(0,1).toString());
//        txttelefonocliente.setText(tablacliente.getValueAt(0, 2).toString());
//    }
//    
//    void buscarCliente(){
//        //buscar el cliente en la base de datos por su nit
//        try {
//            DefaultTableModel modelo;
//            PCredito funciones = new PCredito();
//            modelo = funciones.mostrarCliente(txtnitcliente.getText());
//            tablacliente.setModel(modelo);
//            tablacliente.setVisible(false);
//            //ocultarColumnas();
//            //lbltotalregistros.setText("Total de registros" + Integer.toString(funciones.totalregistros));
//
//        } catch (Exception e) {
//            JOptionPane.showConfirmDialog(rootPane, e);
//        }
//        //si el cliente no es encontrado, se le muestra un mensaje al usuario para que lo registre
//        //de lo contrario se cargan los datos dentro las cajas de texto
//        if(tablacliente.getRowCount()==0){
//            JOptionPane.showMessageDialog(null, "El cliente no se encuentra registrado en nuestra base de datos\n "
//                    + "si desea efectuar un credito para esta persona\n vaya al formulario de clientes y registre sus datos");
//        }else{
//            cargarDatosCliente();
//        }
//    }

    void ocultarColumnas() {
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
    
    
//    public void mostrarlista(String buscar) {
//
//        try {
//            DefaultTableModel modelo;
//            PListaFacturaCredito funciones = new PListaFacturaCredito();
//            modelo = funciones.mostrar(codigoarticulocredito);
//            //TABLA.setModel(modelo);
//            
//           
//
//        } catch (Exception e) {
//            JOptionPane.showConfirmDialog(rootPane, e);
//        }
//    }
    
    void guardarCredito(){
        
        PListaFacturaCredito total = new PListaFacturaCredito();        
        total.actualizarTotalFactura();
        
        //validar que no esten vacios 
        VFactura factura  = new VFactura();// instancia para crear articulo factura que se envia a tblfactura
        PFactura procedimientos = new PFactura(); //procedimientos crud para la factura
        
        VCredito credito = new VCredito();// instancia para crear articulo credito que se envia a tblcredito
        PCredito funciones = new PCredito();//procedimientos crud para la tblcredito
        
        //datos de la factura
        factura.setIdEmpleado(1);
        //double totalneto= (Double.parseDouble(txttotalconinteres.getText()));
        //double totaltotal = Double.
        //factura.setTotal(totalneto);
        factura.setTotal(Double.parseDouble(txttotalconinteres.getText()));
        
                //obtenemos fecha 
                Calendar cal = Calendar.getInstance();
                int dia = (cal.get(Calendar.DATE));
                int mes = (cal.get(Calendar.MONTH));
                int annio = (cal.get(Calendar.YEAR)-1900);
                
        factura.setFecha(new Date(annio,mes,dia));
        factura.setCedulaCliente(txtnitcliente.getText());
        factura.setTipoVenta("credito");
        //factura.setOperatividad("S");  //Operatividad indica si el registro esta activo o se "elimino" del sistema
       
        
        
        credito.setNumeroCuotas(Integer.parseInt((String) comboCuotas.getSelectedItem()));
        double valorcuota = (Double.parseDouble(txttotalconinteres.getText()))/(Integer.parseInt((String) comboCuotas.getSelectedItem()));
        credito.setValorCuota(valorcuota);
        credito.setEstado("activo");
        credito.setReferencia1((txtnombreref1.getText()+" tel: "+ txttelefonoref1.getText()));
        credito.setReferencia2((txtnombreref2.getText()+" tel: "+ txttelefonoref2.getText()));
        credito.setCaducidad(txtcaducidad.getText());
        credito.setConsideraciones(txtconsideraciones.getText());
       
        
        //JOptionPane.showMessageDialog(null,"ensayo 1" );
        if (procedimientos.insertar(factura)) {//si logro insertar factura
            //JOptionPane.showMessageDialog(null,"ensayo 3");
            if (funciones.insertar(credito)){//si logro insertar credito
               // JOptionPane.showMessageDialog(this,"ensayo 2" );
                JOptionPane.showMessageDialog(null, "La factura ha sido guardada exitosamente");
                
            }             
        }
    }
    
    void guardarRemision(){
        
        PListaFacturaCredito total = new PListaFacturaCredito();        
        total.actualizarTotalFactura();
        
        //validar que no esten vacios 
        VFactura factura  = new VFactura();// instancia para crear articulo factura que se envia a tblfactura
        PFactura procedimientos = new PFactura(); //procedimientos crud para la factura
        
        VCredito credito = new VCredito();// instancia para crear articulo credito que se envia a tblcredito
        PCredito funciones = new PCredito();//procedimientos crud para la tblcredito
        
        //datos de la factura
        int codigoempleado;
        if (PEmpleado.usuario[0]==null){codigoempleado=1;} else{codigoempleado=Integer.parseInt(PEmpleado.usuario[0]);}
       
        factura.setIdEmpleado(codigoempleado);  
        //double totalneto= (Double.parseDouble(txttotalconinteres.getText()));
        //double totaltotal = Double.
        //factura.setTotal(totalneto);
        factura.setTotal(Double.parseDouble(txtsubtotalremi.getText()));
        
                //obtenemos fecha 
                Calendar cal = Calendar.getInstance();
                int dia = (cal.get(Calendar.DATE));
                int mes = (cal.get(Calendar.MONTH));
                int annio = (cal.get(Calendar.YEAR)-1900);
                
        factura.setFecha(new Date(annio,mes,dia));
        factura.setCedulaCliente(txtnitcliente.getText());
        factura.setTipoVenta("credito");
        //factura.setOperatividad("S");  //Operatividad indica si el registro esta activo o se "elimino" del sistema
       
        
        
        credito.setNumeroCuotas(Integer.parseInt((String) comboCuotas.getSelectedItem()));
        double valorcuota = (Double.parseDouble(txtsubtotalremi.getText()))/(Integer.parseInt((String) comboCuotas.getSelectedItem()));
        credito.setValorCuota(valorcuota);
        credito.setEstado("activo");
        credito.setReferencia1((txtnombreref1.getText()+" tel: "+ txttelefonoref1.getText()));
        credito.setReferencia2((txtnombreref2.getText()+" tel: "+ txttelefonoref2.getText()));
        credito.setCaducidad(txtcaducidad.getText());
        credito.setConsideraciones(txtconsideraciones.getText());
       
        
        //JOptionPane.showMessageDialog(null,"ensayo 1" );
        if (procedimientos.insertar(factura)) {//si logro insertar factura
            //JOptionPane.showMessageDialog(null,"ensayo 3");
            if (funciones.insertar(credito)){//si logro insertar credito
               // JOptionPane.showMessageDialog(this,"ensayo 2" );
                JOptionPane.showMessageDialog(null, "La factura ha sido guardada exitosamente");
                
            }             
        }
    }
    
    void guardarVentadeContado(){
        
        PListaFacturaCredito total = new PListaFacturaCredito();        
        total.actualizarTotalFactura();
        
        //validar que no esten vacios 
        VFactura factura  = new VFactura();// instancia para crear articulo factura que se envia a tblfactura
        PFactura procedimientos = new PFactura(); //procedimientos crud para la factura
        
//        VCredito credito = new VCredito();// instancia para crear articulo credito que se envia a tblcredito
//        PCredito funciones = new PCredito();//procedimientos crud para la tblcredito
        
        //datos de la factura
        int codigoempleado;
        if (PEmpleado.usuario[0]==null){codigoempleado=1;} else{codigoempleado=Integer.parseInt(PEmpleado.usuario[0]);}
       
        factura.setIdEmpleado(codigoempleado);  
        //double totalneto= (Double.parseDouble(txttotalconinteres.getText()));
        //double totaltotal = Double.
        //factura.setTotal(totalneto);
        factura.setTotal(Double.parseDouble(txtsubtotalremi.getText()));
        
                //obtenemos fecha 
                Calendar cal = Calendar.getInstance();
                int dia = (cal.get(Calendar.DATE));
                int mes = (cal.get(Calendar.MONTH));
                int annio = (cal.get(Calendar.YEAR)-1900);
                
        factura.setFecha(new Date(annio,mes,dia));
        factura.setCedulaCliente(txtnitcliente.getText());
        factura.setTipoVenta("contado");
        //factura.setOperatividad("S");  //Operatividad indica si el registro esta activo o se "elimino" del sistema
       
        
        
//        credito.setNumeroCuotas(Integer.parseInt((String) comboCuotas.getSelectedItem()));
//        double valorcuota = (Double.parseDouble(txtsubtotalremi.getText()))/(Integer.parseInt((String) comboCuotas.getSelectedItem()));
//        credito.setValorCuota(valorcuota);
//        credito.setEstado("activo");
//        credito.setReferencia1((txtnombreref1.getText()+" tel: "+ txttelefonoref1.getText()));
//        credito.setReferencia2((txtnombreref2.getText()+" tel: "+ txttelefonoref2.getText()));
//        credito.setCaducidad(txtcaducidad.getText());
//        credito.setConsideraciones(txtconsideraciones.getText());
       
        
        //JOptionPane.showMessageDialog(null,"ensayo 1" );
        if (procedimientos.insertar(factura)) {//si logro insertar factura
            //JOptionPane.showMessageDialog(null,"ensayo 3");
            JOptionPane.showMessageDialog(null, "La factura ha sido guardada exitosamente");
                        
        }
    }
    
    private String crearResumenFactura(){
        
        String formatoTotales = "%1$50s %2$13s ";
        String totales="\r\n";
        String intereses = String.valueOf(txtintereses.getText());
        //String impuestos= String.valueOf(Double.parseDouble(txttotalconinteres.getText())-Double.parseDouble(txtacumulado.getText()));
        String impuestos = "0";
        totales= totales+" "+ String.format(formatoTotales,"SUBTOTAL: ",txtsubtotalremi.getText()+"\r\n");
        totales= totales+ String.format(formatoTotales,"IMPUESTOS: ",impuestos+"\r\n");
        totales= totales+ String.format(formatoTotales,"INTERESES: ",intereses+"\r\n");
        totales= totales+ String.format(formatoTotales,"TOTAL: ",txttotalconinteres.getText()+"\r\n");
        return totales;
    }
    
    private String crearResumenCreditoRemision(){
        
        String formatoTotales = "%1$40s %2$13s ";
        String totales="\r\n";
        //String impuestos= String.valueOf(Double.parseDouble(txttotalconinteres.getText())-Double.parseDouble(txtacumulado.getText()));
        String intereses = String.valueOf(txtinteresesremi.getText());
        Double interes = Double.parseDouble(txtinteresesremi.getText());
        Double subtotal = Double.parseDouble(txtsubtotalremi.getText());
        Double total= interes+subtotal;
        String totalneto= String.valueOf(total);
        totales= totales+" "+ String.format(formatoTotales,"SUBTOTAL: ",txtsubtotalremi.getText()+"\r\n");
        //totales= totales+ String.format(formatoTotales,"IMPUESTOS: ",impuestos+"\n");
        totales= totales+ String.format(formatoTotales,"INTERESES: ",intereses+"\r\n");
        totales= totales+ String.format(formatoTotales,"TOTAL: ",totalneto+"\r\n");
        return totales;
    }
    
    void crearArchivoTxtCredito(){
        
        String formatoPanel = "%1$-34s %2$-10s %3$20s %4$25s";
        String formatoArchivoTxt= "%1$-30s %2$-10s %3$-10s %4$10s";
            String lineaPanelDeImpresion; 
            String lineaArchivoTxt;
            String contenido="";
            String tituloTabla= String.format(formatoArchivoTxt,"DESCRIPCION","CANTIDAD","PRECIO","TOTAL");
            //JOptionPane.showMessageDialog(null, FrmFactura.listatemporal.get(0).getCodigoArticulo());
            for (int i = 0; i < FrmFactura.listatemporal.size(); i++) {
                double totalporarticulo= (FrmFactura.listatemporal.get(i).getCantidad())*(FrmFactura.listatemporal.get(i).getPrecioArticulo());
             
//JOptionPane.showMessageDialog(null, FrmFactura.listatemporal.get(i).getCodigoArticulo()+" totalpor articulo "+totalporarticulo);
               lineaPanelDeImpresion = String.format(formatoPanel
                       ,FrmFactura.listatemporal.get(i).getNombreArticulo()
                       ,FrmFactura.listatemporal.get(i).getCantidad()
                       ,FrmFactura.listatemporal.get(i).getPrecioArticulo()
                       ,totalporarticulo                 
               
               );
               
               lineaArchivoTxt = String.format(formatoArchivoTxt
                       ,FrmFactura.listatemporal.get(i).getNombreArticulo()
                       ,FrmFactura.listatemporal.get(i).getCantidad()
                       ,FrmFactura.listatemporal.get(i).getPrecioArticulo()                       
                       ,totalporarticulo                 
               
               );
               
               FrmImpresionCredito.jTextArea1.append(lineaPanelDeImpresion + "\r\n");
               contenido=contenido+ "\r\n"+lineaArchivoTxt;
               //JOptionPane.showMessageDialog(null,contenido);
            }
        
             Calendar cal = Calendar.getInstance();
                int dia = (cal.get(Calendar.DATE));
                int mes = (cal.get(Calendar.MONTH));
                int annio = (cal.get(Calendar.YEAR)-1900);
                
       
            PGenerarFormato formato = new PGenerarFormato();
            
            String encabezado= formato.generarEncabezado(dia+"/"+(mes+1)+"/"+annio, 
                    lblnumerofact.getText(),
                    txtnombrecliente.getText(), 
                    txtnitcliente.getText(), 
                    txttelefonocliente.getText());
            String resumenfactura= crearResumenFactura();
            String piedepagina = formato.generarPiedePagina();
            PManejoDocumento funciones = new PManejoDocumento();
            funciones.crearDocumentoTxt(lblnumerofact.getText(),encabezado+"\r\n\n"+tituloTabla+"\r\n\n"+contenido+"\r\n\n"+resumenfactura+"\r\n\n"+piedepagina);
    }
    
    private void visualizarFactura(){
        
        FrmImpresionCredito form = new FrmImpresionCredito();
        form.setVisible(true);       
        

        //  Cargar el resto de los datos de la factura que no pertenecen al arraylist
        // datos del cliente, y totales de la factura
        FrmImpresionCredito.lblnitcliente.setText(txtnitcliente.getText());
        FrmImpresionCredito.lblnombrecliente.setText(txtnombrecliente.getText());
        FrmImpresionCredito.lbltelefonocliente.setText(txttelefonocliente.getText());
        FrmImpresionCredito.lblnumerofactura.setText(lblnumerofact.getText());

        //calcular los valores a mostrar
        //double impuestos=0;
        double subtotal=0;
        for (int i = 0; i < FrmFactura.listatemporal.size(); i++) {
            //impuestos= impuestos + FrmFactura.listatemporal.get(i).getIva(); 
            subtotal= subtotal+ FrmFactura.listatemporal.get(i).getTotalPorArticulo();

        }
        double interes= (subtotal*0.10);
        double totalneto= subtotal+interes;

        //mostrar valores sobre los label
        FrmImpresionCredito.lblsubtotal.setText(String.valueOf(subtotal));
        FrmImpresionCredito.lblinterescredito.setText(String.valueOf(interes));            
        FrmImpresionCredito.lbltotalneto.setText(String.valueOf(totalneto));
        FrmImpresionCredito.lblimpuestos.setText(String.valueOf("0"));        

        
    }
     
    void crearArchivoTxtRemision(){
        
        String formatoPanel = "%1$-34s %2$10s %3$20s %4$20s ";
        String formatoArchivoTxt= "%1$-30s %2$-10s %3$-10s %4$10s   ";
            String lineaPanelDeImpresion; 
            String lineaArchivoTxt;
            String contenido="";
            String tituloTabla= String.format(formatoArchivoTxt,"DESCRIPCION","CANTIDAD","PRECIO","TOTAL");
            //JOptionPane.showMessageDialog(null, FrmFactura.listatemporal.get(0).getCodigoArticulo());
            for (int i = 0; i < FrmFactura.listatemporal.size(); i++) {

             //JOptionPane.showMessageDialog(null, FrmFactura.listatemporal.get(i).getCodigoArticulo());
               lineaPanelDeImpresion = String.format(formatoPanel
                       ,FrmFactura.listatemporal.get(i).getNombreArticulo()
                       ,FrmFactura.listatemporal.get(i).getCantidad()
                       ,FrmFactura.listatemporal.get(i).getPrecioArticulo()
                       ,FrmFactura.listatemporal.get(i).getCantidad()*FrmFactura.listatemporal.get(i).getPrecioArticulo()
               
               );
               
               lineaArchivoTxt = String.format(formatoArchivoTxt
                       ,FrmFactura.listatemporal.get(i).getNombreArticulo()
                       ,FrmFactura.listatemporal.get(i).getCantidad()
                       ,FrmFactura.listatemporal.get(i).getPrecioArticulo()
                       ,FrmFactura.listatemporal.get(i).getCantidad()*FrmFactura.listatemporal.get(i).getPrecioArticulo()                 
               
               );
               
               FrmImpresionFactura.jTextArea1.append(lineaPanelDeImpresion + "\r\n");
               contenido=contenido+ "\r\n"+lineaArchivoTxt;
               //JOptionPane.showMessageDialog(null,contenido);
            }
        
             Calendar cal = Calendar.getInstance();
                int dia = (cal.get(Calendar.DATE));
                int mes = (cal.get(Calendar.MONTH));
                int annio = (cal.get(Calendar.YEAR)-1900);
                
       
            PGenerarFormato formato = new PGenerarFormato();
            
            String encabezado= formato.generarEncabezado(dia+"/"+(mes+1)+"/"+annio, 
                    lblnumerofact.getText(),
                    txtnombrecliente.getText(), 
                    txtnitcliente.getText(), 
                    txttelefonocliente.getText());
            String resumenfactura= crearResumenCreditoRemision();
            String piedepagina = formato.generarPiedePagina();
            PManejoDocumento funciones = new PManejoDocumento();            
            funciones.crearDocumentoTxt(lblnumerofact.getText(),encabezado+"\r\n\n"+tituloTabla+"\r\n\n"+contenido+"\r\n\n"+resumenfactura+"\r\n\n"+piedepagina);
    }
    
    private void visualizarRemision(){
        
        FrmImpresionCreditoRemi form = new FrmImpresionCreditoRemi();
        form.setVisible(true);
        
        //el siguiente  extracto captura los item que estan en tabla(el arraylist) de formulario factura 
        // y los acomoda ordenadamente sobre un jtextarea
            //String format = "%1$-3s %2$-30s %3$-10s %4$10s %5$10s %6$10s %7$10s";
//            String format = "%1$-3s %2$-30s %3$-10s %4$15s %5$15s %6$15s ";
//            String someLine; 
//            String contenido="";
//            //JOptionPane.showMessageDialog(null, FrmFactura.listatemporal.get(0).getCodigoArticulo());
//            for (int i = 0; i < FrmFactura.listatemporal.size(); i++) {
//
//                
//                int cantidad= listatemporal.get(i).getCantidad();
//                double precio= listatemporal.get(i).getPrecioArticulo();
//                double totalporarticulo = cantidad*precio;
//                
//             //JOptionPane.showMessageDialog(null, FrmFactura.listatemporal.get(i).getCodigoArticulo());
//               someLine = String.format(format,FrmFactura.listatemporal.get(i).getCodigoArticulo()
//                       ,FrmFactura.listatemporal.get(i).getNombreArticulo()
//                       ,FrmFactura.listatemporal.get(i).getCantidad()
//                       ,FrmFactura.listatemporal.get(i).getPrecioArticulo()
//                       ,"no aplica"
//                       ,FrmFactura.listatemporal.get(i).getTotalPorArticulo()                 
//               
//               );
//               
//               FrmImpresionCredito.jTextArea1.append(someLine + "\n");
//               contenido=contenido+ "\n"+someLine;
//               JOptionPane.showMessageDialog(null,contenido);
//            }
            
            //  Cargar el resto de los datos de la factura que no pertenecen al arraylist
            // datos del cliente, y totales de la factura
            FrmImpresionCreditoRemi.lblnitcliente.setText(txtnitcliente.getText());
            FrmImpresionCreditoRemi.lblnombrecliente.setText(txtnombrecliente.getText());
            FrmImpresionCreditoRemi.lbltelefonocliente.setText(txttelefonocliente.getText());
            FrmImpresionCreditoRemi.lblnumerofactura.setText(lblnumerofact.getText());
            
            
            double impuestos=0;
            double subtotal=0;
            for (int i = 0; i < FrmFactura.listatemporal.size(); i++) {
                //impuestos= impuestos + FrmFactura.listatemporal.get(i).getIva(); 
                subtotal= subtotal+ FrmFactura.listatemporal.get(i).getPrecioArticulo();
                        
            }
            double interes= (subtotal*0.10);
            double totalneto= subtotal+interes;
            FrmImpresionCreditoRemi.lblinterescredito.setText(String.valueOf(interes));
            
            FrmImpresionCreditoRemi.lbltotalneto.setText(String.valueOf(totalneto));
            FrmImpresionCreditoRemi.lblimpuestos.setText("0");
            FrmImpresionCreditoRemi.lblsubtotal.setText(String.valueOf(subtotal));
            //FrmImpresion.lblnumerofactura.setText(PFactura.numeroUltimaFactura.toString());

//            Calendar cal = Calendar.getInstance();
//                int dia = (cal.get(Calendar.DATE));
//                int mes = (cal.get(Calendar.MONTH));
//                int annio = (cal.get(Calendar.YEAR)-1900);
//                
//       
//            PGenerarFormato formato = new PGenerarFormato();
//            String contenido2= formato.generarEncabezado(dia+"/"+mes+"/"+annio, 
//                    lblnumerofact.getText(),
//                    txtnombrecliente.getText(), 
//                    txtnitcliente.getText(), 
//                    txttelefonocliente.getText());
//            
//            PManejoDocumento funciones = new PManejoDocumento();
//            funciones.crearDocumentoTxt("C"+lblnumerofact.getText(),contenido2+contenido);

        
    }
    
    private void visualizarFacturadeContado(){
        
        FrmImpresionFactura form = new FrmImpresionFactura();
        form.setVisible(true);
        
        
            //  Cargar el resto de los datos de la factura que no pertenecen al arraylist
            // datos del cliente, y totales de la factura
            FrmImpresionFactura.lblnitcliente.setText(txtnitcliente.getText());
            //FrmImpresionCreditoRemi.lblnitcliente.setText(txtnitcliente.getText());
            FrmImpresionFactura.lblnombrecliente.setText(txtnombrecliente.getText());
            FrmImpresionFactura.lbltelefonocliente.setText(txttelefonocliente.getText());
            FrmImpresionFactura.lblnumerofactura.setText(lblnumerofact.getText());
            
            
            //double impuestos=0;
            double subtotal=0;
            for (int i = 0; i < FrmFactura.listatemporal.size(); i++) {
                //impuestos= impuestos + FrmFactura.listatemporal.get(i).getIva(); 
                subtotal= subtotal+ FrmFactura.listatemporal.get(i).getPrecioArticulo();
                        
            }
            double interes= (subtotal*0.10);
            double totalneto= subtotal+interes;
            //FrmImpresionCreditoRemi.lblinterescredito.setText(String.valueOf(interes));
            
            FrmImpresionFactura.lbltotalneto.setText(txtsubtotalremi.getText());
            FrmImpresionFactura.lblimpuestos.setText("0");
            FrmImpresionFactura.lblsubtotal.setText(txtsubtotalremi.getText());
            //FrmImpresion.lblnumerofactura.setText(PFactura.numeroUltimaFactura.toString());

//            Calendar cal = Calendar.getInstance();
//                int dia = (cal.get(Calendar.DATE));
//                int mes = (cal.get(Calendar.MONTH));
//                int annio = (cal.get(Calendar.YEAR)-1900);
//                
//       
//            PGenerarFormato formato = new PGenerarFormato();
//            String contenido2= formato.generarEncabezado(dia+"/"+mes+"/"+annio, 
//                    lblnumerofact.getText(),
//                    txtnombrecliente.getText(), 
//                    txtnitcliente.getText(), 
//                    txttelefonocliente.getText());
//            
//            PManejoDocumento funciones = new PManejoDocumento();
//            funciones.crearDocumentoTxt("C"+lblnumerofact.getText(),contenido2+contenido);

        
    }
   

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtnombrecliente = new javax.swing.JTextField();
        txtnitcliente = new javax.swing.JTextField();
        txttelefonocliente = new javax.swing.JTextField();
        btnbuscarcliente = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnagregar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TABLA = new javax.swing.JTable();
        lblnumerofact = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtnombreref1 = new javax.swing.JTextField();
        txttelefonoref1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtnombreref2 = new javax.swing.JTextField();
        txttelefonoref2 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        combodias = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        comboFechas = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtcaducidad = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        comboCuotas = new javax.swing.JComboBox<>();
        txtnumerocuotas = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtconsideraciones = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnremision = new javax.swing.JButton();
        txtsubtotalremi = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnguardar = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        txtsubtotalcredito = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtintereses = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtvalorcuota = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txttotalconinteres = new javax.swing.JTextField();
        txttotalfactura = new javax.swing.JTextField();
        txtultimafact = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        escogerfecha = new com.toedter.calendar.JDateChooser();
        btnNuevo = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtinteresesremi = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txttotalnetoremi = new javax.swing.JTextField();
        txtcuotaremi = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtimpuestos = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtacumulado = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion comercial"));

        jLabel4.setText("Nombre Cliente");

        jLabel5.setText("Nit o cedula");

        jLabel6.setText("Telefono");

        btnbuscarcliente.setText("Buscar");
        btnbuscarcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarclienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txttelefonocliente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtnombrecliente, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtnitcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnbuscarcliente)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtnitcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscarcliente))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtnombrecliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txttelefonocliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Articulos a facturar"));

        jLabel1.setText("Añadir item a la factura");

        btnagregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/files/plus-button.png"))); // NOI18N
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
            }
        });

        TABLA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Identificacion", "Nombre", "Cantidad", "Precio", "Iva", "PrecioNeto", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TABLA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TABLAMousePressed(evt);
            }
        });
        TABLA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TABLAKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TABLAKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TABLAKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(TABLA);

        lblnumerofact.setText("jLabel16");

        jLabel3.setText("Factura No:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(lblnumerofact)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(40, 40, 40)
                        .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblnumerofact)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3))
                    .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Referencias"));

        jLabel8.setText("Referencia 1(Nombre y telefono)");

        jLabel9.setText("Referencia 2(Nombre y telefono)");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttelefonoref2, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                    .addComponent(txtnombreref1)
                    .addComponent(txttelefonoref1)
                    .addComponent(txtnombreref2)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(19, 19, 19))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtnombreref1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txttelefonoref1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtnombreref2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttelefonoref2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Plazos"));

        jLabel15.setText("Seleccione dia");

        combodias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));
        combodias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combodiasActionPerformed(evt);
            }
        });

        jLabel10.setText("Periodicidad");

        comboFechas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8", "15", "30" }));
        comboFechas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboFechasMousePressed(evt);
            }
        });
        comboFechas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFechasActionPerformed(evt);
            }
        });
        comboFechas.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                comboFechasPropertyChange(evt);
            }
        });

        jLabel13.setText("Dias asignados");

        txtcaducidad.setEditable(false);

        jLabel11.setText("Numero cuotas");

        comboCuotas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "5", "6", "10", "12", "20", "24", "36" }));
        comboCuotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCuotasActionPerformed(evt);
            }
        });

        txtnumerocuotas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnumerocuotasKeyReleased(evt);
            }
        });

        txtconsideraciones.setColumns(20);
        txtconsideraciones.setRows(5);
        jScrollPane1.setViewportView(txtconsideraciones);

        jLabel23.setText("Observaciones");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel15)
                            .addComponent(jLabel10))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboFechas, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combodias, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(comboCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtnumerocuotas, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(27, 27, 27)
                        .addComponent(txtcaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(combodias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(comboFechas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtcaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnumerocuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Contado"));

        btnremision.setText("Efectuar venta de contado");
        btnremision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnremisionActionPerformed(evt);
            }
        });

        jLabel17.setText("Total");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel17)
                .addGap(83, 83, 83)
                .addComponent(txtsubtotalremi, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnremision)
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsubtotalremi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addComponent(btnremision)
                .addGap(88, 88, 88))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Credito"));

        btnguardar.setText("Efectuar venta a credito");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        jLabel20.setText("Subtotal");

        jLabel22.setText("Intereses 10%");

        jLabel14.setText("Valor cuota");

        txtvalorcuota.setEditable(false);

        jLabel7.setText("Total Factura");

        txttotalconinteres.setEditable(false);
        txttotalconinteres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttotalconinteresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtultimafact)
                            .addComponent(txttotalfactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addComponent(btnguardar))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel20)
                            .addComponent(jLabel7)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtvalorcuota, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttotalconinteres, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsubtotalcredito, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                            .addComponent(txtintereses))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtsubtotalcredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtintereses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txttotalconinteres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtvalorcuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnguardar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(txttotalfactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtultimafact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Controles"));

        escogerfecha.setDateFormatString("dd/MM/yyyy");
        escogerfecha.setEnabled(false);
        escogerfecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                escogerfechaPropertyChange(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        jLabel2.setText("Fecha");

        jButton1.setText("Credito/Contado");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(escogerfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo)
                    .addComponent(btnsalir))
                .addGap(20, 20, 20))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnNuevo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(escogerfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsalir)
                    .addComponent(jButton1))
                .addGap(38, 38, 38))
        );

        jLabel18.setText("Interes credito");

        jLabel19.setText("Total neto");

        jLabel16.setText("Valor cuota");

        jLabel21.setText("Impuestos");

        txtimpuestos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtimpuestosActionPerformed(evt);
            }
        });

        jLabel12.setText("Total con impuestos");

        txtacumulado.setEnabled(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(63, 63, 63))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtacumulado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtimpuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txttotalnetoremi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtinteresesremi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcuotaremi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtacumulado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addGap(47, 47, 47))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtimpuestos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtinteresesremi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttotalnetoremi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcuotaremi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
        listaArticulos.clear();
        listatemporal.clear();
        FrmFactura.listatemporal.clear();
        this.dispose();
        
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
        // TODO add your handling code here:
        //listaArticulos.clear();
        //llenarlista();
        
        FrmVistaArticuloCredito form = new FrmVistaArticuloCredito();
        form.toFront();
        form.setVisible(true);
        //mostrarlista(codigoarticulo);
        actualizarCuotas();
       
    }//GEN-LAST:event_btnagregarActionPerformed

    private void TABLAMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TABLAMousePressed
        // TODO add your handling code here:
        
       
    }//GEN-LAST:event_TABLAMousePressed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        // TODO add your handling code here:
        
       
       
    }//GEN-LAST:event_formFocusGained

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
       
    }//GEN-LAST:event_formWindowGainedFocus

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowStateChanged

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // TODO add your handling code here:
        //llenararreglo();
    }//GEN-LAST:event_formMouseMoved

    private void TABLAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TABLAKeyReleased
//        // TODO add your handling code here:
//        System.out.println("tabla.getValueAt(i, 2)");
//        PListaFactura lista = new PListaFactura();
//        lista.actualizarCantidad(TABLA);
//        lista.llenartabla(TABLA);
//        System.out.println(TABLA.getValueAt(0, 2));
//JOptionPane.showMessageDialog(this, "edwin roman");

//        PListaFactura lista = new PListaFactura();
//        lista.actualizarCantidad(TABLA);
//        lista.llenartabla(TABLA);
        
    }//GEN-LAST:event_TABLAKeyReleased

    private void TABLAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TABLAKeyPressed
        
    }//GEN-LAST:event_TABLAKeyPressed

    private void TABLAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TABLAKeyTyped
       
    }//GEN-LAST:event_TABLAKeyTyped

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        
        if(verificarCampos()){
            
            guardarCredito();
            visualizarFactura();
            crearArchivoTxtCredito();
            desactivarCampos();
            ocultarColumnas();
            
        }else{
            JOptionPane.showMessageDialog(null,"Imposible guardar transaccion, faltan algunos datos");
        }
       
        
        
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnbuscarclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarclienteActionPerformed
        // TODO add your handling code here:
          
        FrmVistaClienteCredito form = new FrmVistaClienteCredito();
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_btnbuscarclienteActionPerformed

    private void escogerfechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_escogerfechaPropertyChange
      
        escogerfecha.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
            public void propertyChange(PropertyChangeEvent e) {

                diaseleccionado = Integer.toString(escogerfecha.getCalendar().get(Calendar.DAY_OF_MONTH)); 
            }
        });
    }//GEN-LAST:event_escogerfechaPropertyChange

    private void comboFechasPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_comboFechasPropertyChange
        // TODO add your handling code here:
      
    }//GEN-LAST:event_comboFechasPropertyChange

    private void comboFechasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboFechasMousePressed
        // TODO add your handling code here:
        txtcaducidad.setText(asignarFechas());
    }//GEN-LAST:event_comboFechasMousePressed

    private void comboFechasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFechasActionPerformed
        // TODO add your handling code here:
        txtcaducidad.setText(asignarFechas());
    }//GEN-LAST:event_comboFechasActionPerformed

    private void txtnumerocuotasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumerocuotasKeyReleased
        // TODO add your handling code here:
      
    }//GEN-LAST:event_txtnumerocuotasKeyReleased

    private void comboCuotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCuotasActionPerformed
       
        //invoca una funcion que calcula el valor de la cuota, y asigna el valor al txt
        DecimalFormat df;
        df= new DecimalFormat("#.##");
        double valorcuota = asignarValorCuota();
        double valorcuotaremi = asignarValorCuotaRemi();
      
       txtvalorcuota.setText(df.format(valorcuota));
       txtcuotaremi.setText(df.format(valorcuotaremi));
    }//GEN-LAST:event_comboCuotasActionPerformed

    private void combodiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combodiasActionPerformed
        // TODO add your handling code here:
         txtcaducidad.setText(asignarFechas());
    }//GEN-LAST:event_combodiasActionPerformed

    private void btnremisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnremisionActionPerformed
        // TODO add your handling code here:
        if(txtnitcliente.getText().isEmpty()){
            txtnitcliente.setText("111");        
            txtnombrecliente.setText("Cliente generico");
            txttelefonocliente.setText("999");
        }
        if(verificarCamposContado()){
            
            //guardarRemision();
            guardarVentadeContado();
            visualizarFacturadeContado();
            crearArchivoTxtRemision();
            desactivarCampos();
            ocultarColumnas();
            
        }else{
            JOptionPane.showMessageDialog(null,"Imposible guardar transaccion, faltan algunos datos");
        }
    }//GEN-LAST:event_btnremisionActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        activarCampos();
        limpiarCampos();
        FrmFactura.listatemporal.clear();
        PFactura numFactura = new PFactura(); 
        lblnumerofact.setText(String.valueOf(numFactura.consultarNumeroFactura()));
        ocultarColumnas();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtimpuestosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtimpuestosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtimpuestosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //FrmFacturacion form = new FrmFacturacion();
        if(esvisible ){
                    
        jPanel4.setVisible(false);
        jPanel5.setVisible(false);
        jPanel6.setVisible(false);
        jPanel1.setVisible(true);
        esvisible=false;
        
        
        }else  if(!esvisible){
            
            jPanel1.setVisible(false);
            jPanel4.setVisible(true);
            jPanel5.setVisible(true);
            jPanel6.setVisible(true);
            esvisible=true;
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txttotalconinteresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttotalconinteresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttotalconinteresActionPerformed

    public double asignarValorCuota(){
        //devuelve el valor de la cuota, basado en el total de la factura dividido en el numero de cuotas elegido
        double valorCuota=0;
        
        if(!txttotalconinteres.getText().isEmpty()){
            numerodeCuotas= Integer.parseInt(comboCuotas.getSelectedItem().toString());
            valorCuota= Double.parseDouble(txttotalconinteres.getText())/numerodeCuotas;
        }        
        return valorCuota;
    }
    
    public  double asignarValorCuotaRemi(){
        //devuelve el valor de la cuota, basado en el total de la factura dividido en el numero de cuotas elegido
       double valorcuota=0;
        
        if(!txttotalconinteres.getText().isEmpty()){
            numerodeCuotas= Integer.parseInt(comboCuotas.getSelectedItem().toString());
            double subtotal = Double.parseDouble(txtsubtotalremi.getText());
            double intereses = subtotal*0.10;
            double totalconinteres = subtotal + intereses;
            valorcuota= totalconinteres/ numerodeCuotas;
            
            txtinteresesremi.setText(String.valueOf(intereses));
            txttotalnetoremi.setText(String.valueOf(totalconinteres));
            
           
        }        
        return valorcuota;
    }
    
    public String asignarFechas(){
        //devuelve una cadena de texto asi: -a-b-c-d- o -a-b- o -a- donde las letras representan dias del mes
        //con simetria entre ellas
        //para devolver dicha cadena, usa fecha actual y una periodicidad que selecciona el usuario : 8, 15 o 30
        String fechas="";
        int num1, num2, num3, num4;
        int periodicidad= comboFechas.getSelectedIndex();
        //int periodo = Integer.parseInt(comboFechas.getSelectedItem().toString());
        
         //obtenemos fecha 
                Calendar cal = Calendar.getInstance();
                int dia = (cal.get(Calendar.DATE));
                int mes = (cal.get(Calendar.MONTH));
                int annio = (cal.get(Calendar.YEAR)-1900);
                //int diaS= Integer.parseInt(diaseleccionado);
                if(dia==31){dia=1;}//se cuentan todos los meses como de 30 dias, si el credito se realiza un 31
                                    //se tomara como fecha de credito el 1 del mes siguiente
        dia = Integer.parseInt(combodias.getSelectedItem().toString());
        switch(periodicidad){
            case 0:// en caso de que el credito se pague cada ocho dias, se sacan cuatro fechas al mes
                num1= dia;
                num2=dia+7;
                num3= dia+14;
                num4= dia+21;
                if(num2>30){num2= num2-30;}
                if(num3>30){num3= num3-30;}
                if(num4>30){num4= num4-30;}
                fechas= "-"+String.valueOf(dia)+"-"+String.valueOf(num2)+"-"+String.valueOf(num3)+"-"+String.valueOf(num4)+"-";
                break;
            case 1://en caso de que el pago sea quincenal se sacan dos fechas al mes
                num1=dia;
                num2=dia+15;
                if(num2>30){num2= num2-30;}
                fechas="-"+String.valueOf(dia)+"-"+String.valueOf(num2)+"-";
                break;
            case 2:
                num1=dia;
                fechas="-"+String.valueOf(dia)+"-";
        }             
        
        
        return fechas;
    }
    
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmFacturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmFacturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmFacturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmFacturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmFacturacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable TABLA;
    private javax.swing.JButton btnNuevo;
    public static javax.swing.JButton btnagregar;
    private javax.swing.JButton btnbuscarcliente;
    public static javax.swing.JButton btnguardar;
    public static javax.swing.JButton btnremision;
    private javax.swing.JButton btnsalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboCuotas;
    private javax.swing.JComboBox<String> comboFechas;
    private javax.swing.JComboBox<String> combodias;
    private com.toedter.calendar.JDateChooser escogerfecha;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JLabel lblnumerofact;
    public static javax.swing.JTextField txtacumulado;
    public static javax.swing.JTextField txtcaducidad;
    public static javax.swing.JTextArea txtconsideraciones;
    public static javax.swing.JTextField txtcuotaremi;
    public static javax.swing.JTextField txtimpuestos;
    public static javax.swing.JTextField txtintereses;
    public static javax.swing.JTextField txtinteresesremi;
    public static javax.swing.JTextField txtnitcliente;
    public static javax.swing.JTextField txtnombrecliente;
    public static javax.swing.JTextField txtnombreref1;
    public static javax.swing.JTextField txtnombreref2;
    public static javax.swing.JTextField txtnumerocuotas;
    public static javax.swing.JTextField txtsubtotalcredito;
    public static javax.swing.JTextField txtsubtotalremi;
    public static javax.swing.JTextField txttelefonocliente;
    public static javax.swing.JTextField txttelefonoref1;
    public static javax.swing.JTextField txttelefonoref2;
    public static javax.swing.JTextField txttotalconinteres;
    public static javax.swing.JTextField txttotalfactura;
    public static javax.swing.JTextField txttotalnetoremi;
    public static javax.swing.JTextField txtultimafact;
    public static javax.swing.JTextField txtvalorcuota;
    // End of variables declaration//GEN-END:variables

//    private void addWindowStateListener(WindowStateListener windowStateListener) {
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    private void addWindowFocusListener(WindowFocusListener windowFocusListener) {
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
