/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import logica.PManejoDocumento;
import datos.VFactura;
import datos.VListaFactura;
import datos.VProducto;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowStateListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.PEmpleado;
import logica.PFactura;
import logica.PGenerarFormato;
import logica.PListaFactura;
import logica.PProducto;
import logica.PValidacionNumerica;
import logica.mipropiatabla;
import static presentacion.FrmProducto.txtidcategoria;

/**
 *
 * @author Edwin
 */
public class FrmFactura extends javax.swing.JInternalFrame {

    public static ArrayList<VListaFactura> listaArticulos = new ArrayList<>();
    public static ArrayList<VListaFactura> listatemporal = new ArrayList<>();
    public static String codigoarticulo;
    public static String nit, cliente, telefono;
    PValidacionNumerica validacion = new PValidacionNumerica();
    
    Object matrizespaciada[][]=new Object[listatemporal.size()][7]; 
    /**
     * Creates new form FrmAbonar
     */
    public FrmFactura() {
        
       

        initComponents();
        //ocultarColumnas();
        txtultimafact.setVisible(false);
        txttotalfactura.setVisible(false);
        Calendar c2 = new GregorianCalendar();
        jDateChooser1.setCalendar(c2);
        PFactura numFactura = new PFactura();        
        lblnumerofact.setText(String.valueOf(numFactura.consultarNumeroFactura()));
        desactivarCampos();
        
//        Calendar micalendario = new GregorianCalendar();
//        jDateChooser1.setCalendar(micalendario);
//       //jDateChooser1.setDate(date);
        
       
        
    }
    
   
    
    public String llenarEspacios(String cadena, int espacios){
        String texto=cadena;
        if (texto.length()< espacios){
            int faltante = espacios-texto.length();
            for(int i=0; i<=espacios;i++){
                texto= texto+" ";
            }            
        }
        if(texto.length()> espacios){
            texto = texto.substring(0, (espacios-1));
        }
        JOptionPane.showMessageDialog(null, "el tamaño es "+ texto.length());
        
        return texto;
    }
    
    public String llenarEspacios(double cadena, int espacios){
        String texto= String.valueOf(cadena);
        
        if (texto.length()< espacios){
            int faltante = espacios-texto.length();
            for(int i=0; i<=espacios;i++){
                texto= texto+" ";
            }            
        }
        if(texto.length()> espacios){
            texto = texto.substring(0, (espacios-1));
        }
        
        JOptionPane.showMessageDialog(null, "el tamaño es "+ texto.length());
        return texto;
    }   
    
    void mostrarDevuelta(){
        
        //toma los valores de totalconinteres y el valor con que cancelan, y muestra cuanto debe devolverse
        Double cancelacon= Double.parseDouble(txtcancelacon.getText());
        Double totalneto= Double.parseDouble(txtacumulado.getText());        
        txtdevuelta.setText(String.valueOf(cancelacon-totalneto));
        
    }
    
    public static void limpiarCampos(){
        txtnitcliente.setText(null);
        txtnombrecliente.setText(null);
        txttelefonocliente.setText(null);
        txtacumulado.setText(null);
        txttotalfactura.setText(null);
        PFactura funciones = new PFactura();
        TABLA.setModel(funciones.limpiarTabla());
        txtcancelacon.setText(null);
        txtdevuelta.setText(null);
        txttotalsin.setText(null);
    }
    
    public static void desactivarCampos(){
        txtnitcliente.setEnabled(false);
        txtnombrecliente.setEnabled(false);
        txttelefonocliente.setEnabled(false);
        txtacumulado.setEnabled(false);
        txttotalfactura.setEnabled(false);
        PFactura funciones = new PFactura();
        TABLA.setModel(funciones.limpiarTabla());
        txtcancelacon.setEnabled(false);
        txtdevuelta.setEnabled(false);
        txttotalsin.setEnabled(false);
        btnagregar.setEnabled(false);
        btnguardar.setEnabled(false);
        btnremision.setEnabled(false);
    }
    
    public static void activarCampos(){
        txtnitcliente.setEnabled(true);
        txtnombrecliente.setEnabled(true);
        txttelefonocliente.setEnabled(true);
        txtacumulado.setEnabled(true);
        txttotalfactura.setEnabled(true);
        PFactura funciones = new PFactura();
        TABLA.setModel(funciones.limpiarTabla());
        txtcancelacon.setEnabled(true);
        txtdevuelta.setEnabled(true);
        txttotalsin.setEnabled(true);
        btnagregar.setEnabled(true);
        btnguardar.setEnabled(true);
        btnremision.setEnabled(true);
    }

    void ocultarColumnas() {
        TABLA.getColumnModel().getColumn(0).setMaxWidth(0);
        TABLA.getColumnModel().getColumn(0).setMinWidth(0);
        TABLA.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        TABLA.getColumnModel().getColumn(4).setMaxWidth(0);
        TABLA.getColumnModel().getColumn(4).setMinWidth(0);
        TABLA.getColumnModel().getColumn(4).setPreferredWidth(0);
    }
    
    boolean verificarCampos(){
        
        return !(txtacumulado.getText().isEmpty());
                //si no hay un total acumulado es porque no hay items a vender        
    }
    
    private void visualizarFactura(){
        
        FrmImpresionFactura form = new FrmImpresionFactura();
        form.setVisible(true);
        
        //el siguiente  extracto captura los item que estan en tabla(el arraylist) de formulario factura 
        // y los acomoda ordenadamente sobre un jtextarea
            //String format = "%1$-3s %2$-30s %3$-10s %4$10s %5$10s %6$10s %7$10s";
//            String format = "%1$-3s %2$-30s %3$-10s %4$15s %5$15s %6$15s ";
//            String someLine; 
//            String contenido="";
//            for (int i = 0; i < listatemporal.size(); i++) {
//
//             
//               someLine = String.format(format,listatemporal.get(i).getCodigoArticulo()
//                       ,listatemporal.get(i).getNombreArticulo()
//                       ,listatemporal.get(i).getCantidad()
//                       ,listatemporal.get(i).getPrecioArticulo()
//                       ,listatemporal.get(i).getIva()
//                       ,listatemporal.get(i).getTotalPorArticulo()                 
//               
//               );
//               
//               FrmImpresionRemision.jTextArea1.append(someLine + "\n");
//               contenido=contenido+ "\n"+someLine;
//            }
            
            //  Cargar el resto de los datos de la factura que no pertenecen al arraylist
            // datos del cliente, y totales de la factura
            FrmImpresionFactura.lblnitcliente.setText(txtnitcliente.getText());
            FrmImpresionFactura.lblnombrecliente.setText(txtnombrecliente.getText());
            FrmImpresionFactura.lbltelefonocliente.setText(txttelefonocliente.getText());
            FrmImpresionFactura.lblnumerofactura.setText(lblnumerofact.getText());
            
            
            double impuestos=0;
            for (int i = 0; i < listatemporal.size(); i++) {
                impuestos= impuestos + listatemporal.get(i).getIva();             
            }
            
            double subtotal = (Double.parseDouble(txtacumulado.getText())-impuestos);
            FrmImpresionFactura.lbltotalneto.setText(txtacumulado.getText());
            FrmImpresionFactura.lblimpuestos.setText(String.valueOf(impuestos));
            FrmImpresionFactura.lblsubtotal.setText(String.valueOf(subtotal));
            //FrmImpresion.lblnumerofactura.setText(PFactura.numeroUltimaFactura.toString());

//            Calendar cal = Calendar.getInstance();
//                int dia = (cal.get(Calendar.DATE));
//                int mes = (cal.get(Calendar.MONTH));
//                int annio = (cal.get(Calendar.YEAR));
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
//            funciones.crearDocumentoTxt("F"+lblnumerofact.getText(),contenido2+contenido);

        
    }
    
    private String crearResumenRemision(){
        
        String formatoTotales = "%1$65s %2$12s ";
        String totales="";
        totales= totales+ String.format(formatoTotales,"TOTAL: ",txttotalsin.getText());
        
        return totales;
    }
    
    private String crearResumenFactura(){
        
        //ensayar para los saltos de linea
        //El Bloc de notas solo reconoce CR, LF (0x0d, 0x0a), mientras que otras fuentes pueden usar solo CR o LF.
        //LINE FEED (CRLF): Windows and other non-Unix/IBM operating systems
        //“\r\n”.
        String formatoTotales = "%1$60s %2$13s ";
        String totales="\r\n";
        String impuestos= String.valueOf(Double.parseDouble(txtacumulado.getText())-Double.parseDouble(txttotalsin.getText()));
        totales= totales+" "+ String.format(formatoTotales,"SUBTOTAL: ",txttotalsin.getText()+"\r\n");
        totales= totales+ String.format(formatoTotales,"IMPUESTOS: ",impuestos+"\r\n");
        totales= totales+ String.format(formatoTotales,"TOTAL: ",txtacumulado.getText()+"\r\n");
        return totales;
    }
    private void crearArchivoTxtFactura(){
        
            String formatoImpresion = "%1$-30s %2$10s %3$18s %4$15s %5$15s ";
            String formatoArchivoTxt= "%1$-20s %2$10s %3$10s %4$15s %5$15s  ";
            String listadoImpresion; 
            String listadoTxt;
            String contenido="";
            String tituloTabla= String.format(formatoArchivoTxt,"DESCRIPCION","CANTIDAD","PRECIO","IVA","TOTAL");
            
            for (int i = 0; i < listatemporal.size(); i++) {
             
               listadoImpresion = String.format(formatoImpresion
                       ,listatemporal.get(i).getNombreArticulo()
                       ,listatemporal.get(i).getCantidad()
                       ,listatemporal.get(i).getPrecioArticulo()
                       ,listatemporal.get(i).getIva()
                       ,listatemporal.get(i).getTotalPorArticulo()                 
               
               );
               listadoTxt = String.format(formatoArchivoTxt
                       ,listatemporal.get(i).getNombreArticulo()
                       ,listatemporal.get(i).getCantidad()
                       ,listatemporal.get(i).getPrecioArticulo()
                       ,listatemporal.get(i).getIva()
                       ,listatemporal.get(i).getTotalPorArticulo()                 
               
               );
               
               FrmImpresionFactura.jTextArea1.append(listadoImpresion + "\r\n");
               contenido=contenido+ "\r\n"+listadoTxt;
            }
            
             Calendar cal = Calendar.getInstance();
                int dia = (cal.get(Calendar.DATE));
                int mes = (cal.get(Calendar.MONTH));
                int annio = (cal.get(Calendar.YEAR));
                
       
            PGenerarFormato formato = new PGenerarFormato();
            String encabezado= formato.generarEncabezado(dia+"/"+(mes+1)+"/"+annio, 
                    lblnumerofact.getText(),
                    txtnombrecliente.getText(), 
                    txtnitcliente.getText(), 
                    txttelefonocliente.getText());
            String resumenFactura = crearResumenFactura();
            String piedePagina = formato.generarPiedePagina();
            PManejoDocumento funciones = new PManejoDocumento();            
            funciones.crearDocumentoTxt("F"+lblnumerofact.getText(),encabezado+"\r\n"+tituloTabla+"\r\n"+contenido+"\r\n"+resumenFactura+"\r\n"+piedePagina);
    }
    
    private void crearArchivoTxtRemision(){
        
         //Variables que almacenan el formato en que seran enviados los valores a la factura 
         //tanto a la impresion como al archivo txt que se guarda en el equipo
            String format = "%1$-40s %2$-5s %3$20s %4$20s ";
            String formatotxt= "%1$-30s %2$-5s %3$20s %4$20s ";
            String formatotitulo ="%1$-27s %2$-5s %3$20s %4$20s";
            String someLine; 
            String someLinetxt;
            String contenido="";
            String contenidotxt= String.format(formatotitulo,"DESCRIPCION","CANTIDAD","PRECIO","TOTAL");
            
            //dentro del ciclo se capturan todos los datos que contiene el arraylist para enviarlos
            //con su respectivo formato a una variable String que sera enviada a la clase generar documento
            for (int i = 0; i < listatemporal.size(); i++) {

                int cantidad= listatemporal.get(i).getCantidad();
                double precio= listatemporal.get(i).getPrecioArticulo();
                double totalporarticulo = cantidad*precio;
               //este formato se envia a la previsualizacion de el recibo 
               someLine = String.format(format
                       ,listatemporal.get(i).getNombreArticulo()
                       ,listatemporal.get(i).getCantidad()
                       ,listatemporal.get(i).getPrecioArticulo()
                       ,totalporarticulo              
               );
               //este formato se envia al archivo txt que se guarda como constancia de factura
               someLinetxt = String.format(formatotxt
                       ,listatemporal.get(i).getNombreArticulo()
                       ,listatemporal.get(i).getCantidad()
                       ,listatemporal.get(i).getPrecioArticulo()
                       ,totalporarticulo                
               );
               
               FrmImpresionRemision.jTextArea1.append(someLine + "\r\n");
               contenido=contenido+ "\r\n"+someLine;
               contenidotxt=contenidotxt+ "\r\n"+someLinetxt;
               
            }
            //se crea una instancia de calendario para que en la factura
            //pueda ser visualizada la fecha
             Calendar cal = Calendar.getInstance();
                int dia = (cal.get(Calendar.DATE));
                int mes = (cal.get(Calendar.MONTH));
                int annio = (cal.get(Calendar.YEAR));
            
            //se llama a la clase generar formato que es la que crea el encabezado 
            //unico para cada factura
            PGenerarFormato formato = new PGenerarFormato();
            String contenido2= formato.generarEncabezado(dia+"/"+(mes+1)+"/"+annio, 
                    lblnumerofact.getText(),
                    txtnombrecliente.getText(), 
                    txtnitcliente.getText(), 
                    txttelefonocliente.getText());
            String totalFactura = crearResumenRemision();
            String piedepagina = formato.generarPiedePagina();
            //se llama la clase que crea el archivo txt
            PManejoDocumento funciones = new PManejoDocumento();
            funciones.crearDocumentoTxt("R"+lblnumerofact.getText(),contenido2+"\r\n"+contenidotxt+"\r\n"+totalFactura+"\r\n"+piedepagina);
        
    }
    
    private void visualizarRemision(){
        
        FrmImpresionRemision form = new FrmImpresionRemision();
        form.setVisible(true);
        
        //el siguiente  extracto captura los item que estan en tabla(el arraylist) de formulario factura 
        // y los acomoda ordenadamente sobre un jtextarea
            //String format = "%1$-3s %2$-30s %3$-10s %4$10s %5$10s %6$10s %7$10s";
//            String format = "%1$-3s %2$-30s %3$-10s %4$15s %5$15s %6$15s ";
//            String formatotxt= "%1$-3s %2$-30s %3$-3s %4$10s %5$15s %6$10s ";
//            String formatotitulo ="%1$-30s %2$-3s %3$10s %4$10s %5$10s ";
//            String someLine; 
//            String someLinetxt;
//            String contenido="";
//            String contenidotxt= String.format(formatotitulo,"descripcion","cantidad","precio","iva","total");
//            for (int i = 0; i < listatemporal.size(); i++) {
//
//                int cantidad= listatemporal.get(i).getCantidad();
//                double precio= listatemporal.get(i).getPrecioArticulo();
//                double totalporarticulo = cantidad*precio;
//               //este formato se envia a la previsualizacion de el recibo 
//               someLine = String.format(format,listatemporal.get(i).getCodigoArticulo()
//                       ,listatemporal.get(i).getNombreArticulo()
//                       ,listatemporal.get(i).getCantidad()
//                       ,listatemporal.get(i).getPrecioArticulo()
//                       ,"no aplica"
//                       ,totalporarticulo              
//               );
//               //este formato se envia al archivo txt que se guarda como constancia de factura
//               someLinetxt = String.format(formatotxt,listatemporal.get(i).getCodigoArticulo()
//                       ,listatemporal.get(i).getNombreArticulo()
//                       ,listatemporal.get(i).getCantidad()
//                       ,listatemporal.get(i).getPrecioArticulo()
//                       ,"no aplica"
//                       ,totalporarticulo                
//               );
//               
//               FrmImpresionRemision.jTextArea1.append(someLine + "\n");
//               contenido=contenido+ "\n"+someLine;
//               contenidotxt=contenidotxt+ "\n"+someLinetxt;
//               
//            }
            
            //  Cargar el resto de los datos de la factura que no pertenecen al arraylist
            // datos del cliente, y totales de la factura
            FrmImpresionRemision.lblnitcliente.setText(txtnitcliente.getText());
            FrmImpresionRemision.lblnombrecliente.setText(txtnombrecliente.getText());
            FrmImpresionRemision.lbltelefonocliente.setText(txttelefonocliente.getText());
            FrmImpresionRemision.lblnumerofactura.setText(lblnumerofact.getText());     
            
            double impuestos=0;
            for (int i = 0; i < listatemporal.size(); i++) {
                impuestos= impuestos + listatemporal.get(i).getIva();             
            }
            
            double subtotal = (Double.parseDouble(txtacumulado.getText())-impuestos);
            FrmImpresionRemision.lbltotalneto.setText(txttotalsin.getText());
            FrmImpresionRemision.lblimpuestos.setText("0");
            FrmImpresionRemision.lblsubtotal.setText(txttotalsin.getText());
            //FrmImpresion.lblnumerofactura.setText(PFactura.numeroUltimaFactura.toString());

            
//             Calendar cal = Calendar.getInstance();
//                int dia = (cal.get(Calendar.DATE));
//                int mes = (cal.get(Calendar.MONTH));
//                int annio = (cal.get(Calendar.YEAR));
//                
//       
//            PGenerarFormato formato = new PGenerarFormato();
//            String contenido2= formato.generarEncabezado(dia+"/"+(mes+1)+"/"+annio, 
//                    lblnumerofact.getText(),
//                    txtnombrecliente.getText(), 
//                    txtnitcliente.getText(), 
//                    txttelefonocliente.getText());
//            
//            PManejoDocumento funciones = new PManejoDocumento();
//            funciones.crearDocumentoTxt("R"+lblnumerofact.getText(),contenido2+contenidotxt);

            
//        Object matriz[][]=new Object[listatemporal.size()][7];
//        
//        //se vacia en la matriz todo el contenido de la listaArticulos
//        for (int i = 0; i < listatemporal.size(); i++) {
//            matriz[i][0]= llenarEspacios(String.valueOf(listatemporal.get(i).getCodigoArticulo()),5);
//            matriz[i][1]= llenarEspacios(listatemporal.get(i).getNombreArticulo(),30);
//            matriz[i][2]= llenarEspacios(listatemporal.get(i).getCantidad(),5);
//            matriz[i][3]= llenarEspacios(listatemporal.get(i).getPrecioArticulo(),10);
//            matriz[i][4]= llenarEspacios(listatemporal.get(i).getIva(),10);
//            matriz[i][5]= llenarEspacios(listatemporal.get(i).getPrecioVenta(),10);
//            matriz[i][6]= llenarEspacios(listatemporal.get(i).getTotalPorArticulo(),10);             
//           
        
//            FrmImpresionRemision.txtarea.append(matriz[i][0]+"");
//            FrmImpresionRemision.txtarea.append(matriz[i][1]+"");
//            FrmImpresionRemision.txtarea.append(matriz[i][2]+"");
//            FrmImpresionRemision.txtarea.append(matriz[i][3]+"");
//            FrmImpresionRemision.txtarea.append(matriz[i][4]+"");
//            FrmImpresionRemision.txtarea.append(matriz[i][5]+"");
//            FrmImpresionRemision.txtarea.append(matriz[i][6]+"");
//            form.txtarea.append(System.getProperty("line.separator")); // Esto para el salto de línea 
////            form.txtarea.c
       // }
    }
    
//    
    
    public void mostrarlista(String buscar) {

        try {
            DefaultTableModel modelo;
            PListaFactura funciones = new PListaFactura();
            modelo = funciones.mostrar(codigoarticulo);
            TABLA.setModel(modelo);
            
            System.out.println("presentacion.FrmFactura.mostrarlista()");
            System.out.println(buscar);
            //ocultarColumnas();
            //lbltotalregistros.setText("Total de registros" + Integer.toString(funciones.totalregistros));

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(rootPane, e);
        }
    }
    
    void guardarFactura(){
         // TODO add your handling code here:
         //PEmpleado empleado = new PEmpleado();
        PListaFactura total = new PListaFactura();
        //total.actualizarTotalFactura();
        total.actualizarTotalFactura();
        
        //validar que no esten vacios 
        VFactura factura  = new VFactura();// se crea la instancia de la  clase de los datos
        PFactura procedimientos = new PFactura(); //se crea instancia de los procedimientos
        
        //datos de la persona
        int codigoempleado;
        if (PEmpleado.usuario[0]==null){codigoempleado=1;} else{codigoempleado=Integer.parseInt(PEmpleado.usuario[0]);}
       
        factura.setIdEmpleado(codigoempleado);        
        factura.setTotal(Double.parseDouble(txtacumulado.getText()));
        
                //obtenemos fecha 
                Calendar cal = Calendar.getInstance();
                int dia = (cal.get(Calendar.DATE));
                int mes = (cal.get(Calendar.MONTH));
                int annio = (cal.get(Calendar.YEAR)-1900);
                
        factura.setFecha(new Date(annio,mes,dia));
        if(txtnitcliente.getText().isEmpty()){
            factura.setCedulaCliente("comun");
            
        }else{
            factura.setCedulaCliente(txtnitcliente.getText());
            
        }
        
        
        factura.setTipoVenta("contado");
        //factura.setOperatividad("S");  //Operatividad indica si el registro esta activo o se "elimino" del sistema
       
        
        if (procedimientos.insertar(factura)) {
                JOptionPane.showMessageDialog(null, "La factura ha sido guardada exitosamente");
                
                
            }
    }
   
    void guardarRemision(){
         // TODO add your handling code here:
        PListaFactura total = new PListaFactura();
        //total.actualizarTotalFactura();
        total.actualizarTotalFactura();
        
        //validar que no esten vacios 
        VFactura factura  = new VFactura();// se crea la instancia de la  clase de los datos
        PFactura procedimientos = new PFactura(); //se crea instancia de los procedimientos
        
        //datos de la persona
        int codigoempleado;
        if (PEmpleado.usuario[0]==null){codigoempleado=1;} else{codigoempleado=Integer.parseInt(PEmpleado.usuario[0]);}
       
        factura.setIdEmpleado(codigoempleado);        
        factura.setTotal(Double.parseDouble(txttotalsin.getText()));
        
                //obtenemos fecha 
                Calendar cal = Calendar.getInstance();
                int dia = (cal.get(Calendar.DATE));
                int mes = (cal.get(Calendar.MONTH));
                int annio = (cal.get(Calendar.YEAR)-1900);
                
        factura.setFecha(new Date(annio,mes,dia));
        if(txtnitcliente.getText().isEmpty()){
            factura.setCedulaCliente("comun");
            
        }else{
            factura.setCedulaCliente(txtnitcliente.getText());
            
        }
        
        
        factura.setTipoVenta("contado");
        //factura.setOperatividad("S");  //Operatividad indica si el registro esta activo o se "elimino" del sistema
       
        
        if (procedimientos.insertar(factura)) {
                JOptionPane.showMessageDialog(null, "La factura ha sido guardada exitosamente");
                
                
            }
    }
     
    private void llenarlista(){
        //listaArticulos.clear();
        System.out.println(TABLA.getRowCount());
        if (TABLA.getRowCount()>0){
        
        
            for(int i=0; i<= TABLA.getRowCount();i++){

                int codigoArticulo=Integer.parseInt(TABLA.getValueAt(i, 0).toString());            
                String nombreArticulo= (TABLA.getValueAt(i, 0).toString());
                int cantidad = Integer.parseInt(TABLA.getValueAt(i, 0).toString());
                double precioArticulo = Double.parseDouble(TABLA.getValueAt(i, 0).toString());
                double iva = Double.parseDouble(TABLA.getValueAt(i, 0).toString());
                double precioVenta = Double.parseDouble(TABLA.getValueAt(i, 0).toString());
                double totalPorArticulo = Double.parseDouble(TABLA.getValueAt(i, 0).toString());      

                VListaFactura lista = new VListaFactura(codigoArticulo,nombreArticulo,cantidad,precioArticulo,iva,precioVenta,totalPorArticulo);
                listaArticulos.add(lista);
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtnombrecliente = new javax.swing.JTextField();
        txtnitcliente = new javax.swing.JTextField();
        txttelefonocliente = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        txtbuscarcliente = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtultimafact = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnagregar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TABLA = new javax.swing.JTable();
        btnguardar = new javax.swing.JButton();
        txttotalfactura = new javax.swing.JTextField();
        txtacumulado = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtcancelacon = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtdevuelta = new javax.swing.JTextField();
        lbl = new javax.swing.JLabel();
        lblnumerofact = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txttotalsin = new javax.swing.JTextField();
        btnremision = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

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

        jLabel2.setText("Fecha");

        jLabel3.setText("decoU");

        jLabel4.setText("Nit");

        jLabel5.setText("Cliente");

        jLabel6.setText("Telefono");

        jDateChooser1.setDateFormatString("dd/MM/yyyy");
        jDateChooser1.setEnabled(false);

        txtbuscarcliente.setText("Buscar");
        txtbuscarcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarclienteActionPerformed(evt);
            }
        });

        btnsalir.setForeground(new java.awt.Color(255, 0, 51));
        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(64, 64, 64)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtnitcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(txtbuscarcliente))
                    .addComponent(txtnombrecliente, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttelefonocliente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnNuevo))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnsalir)))
                        .addGap(35, 35, 35))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscarcliente)
                    .addComponent(txtnitcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(btnsalir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(txtnombrecliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(txttelefonocliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnNuevo)
                        .addGap(6, 6, 6))))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Articulos a facturar"));

        txtultimafact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtultimafactActionPerformed(evt);
            }
        });

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

        btnguardar.setText("Guardar");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        txtacumulado.setEnabled(false);

        jLabel7.setText("Total Factura");

        jLabel15.setText("Cancela con:");

        txtcancelacon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcancelaconKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcancelaconKeyTyped(evt);
            }
        });

        jLabel16.setText("Devolver:");

        lbl.setText("Numero de Factura");

        lblnumerofact.setText("jLabel8");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(lbl)
                        .addGap(32, 32, 32)
                        .addComponent(lblnumerofact)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(31, 31, 31)
                        .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtultimafact)
                            .addComponent(txttotalfactura))
                        .addGap(149, 149, 149)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(34, 34, 34)
                                .addComponent(txtacumulado, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(jLabel16)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(34, 34, 34)
                                .addComponent(txtcancelacon, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(txtdevuelta, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                .addComponent(btnguardar)))))
                .addContainerGap(102, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lbl)
                        .addComponent(lblnumerofact))
                    .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txttotalfactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtultimafact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtacumulado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtcancelacon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdevuelta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnguardar)))))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Remision"));

        btnremision.setText("Remision");
        btnremision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnremisionActionPerformed(evt);
            }
        });

        jLabel8.setText("Total por remision");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel8)
                .addGap(27, 27, 27)
                .addComponent(txttotalsin, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(btnremision)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txttotalsin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnremision))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        listaArticulos.clear();
        listatemporal.clear();
        limpiarCampos();
        this.dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void salir(){
        listaArticulos.clear();
        listatemporal.clear();
        limpiarCampos();
        this.dispose();
    }
    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
        // TODO add your handling code here:
        //listaArticulos.clear();
        //llenarlista();
        
        FrmVistaArticulo form = new FrmVistaArticulo();
        form.toFront();
        form.setVisible(true);
        //mostrarlista(codigoarticulo);
       
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

        
    }//GEN-LAST:event_TABLAKeyReleased

    private void TABLAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TABLAKeyPressed
        
    }//GEN-LAST:event_TABLAKeyPressed

    private void TABLAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TABLAKeyTyped
       
    }//GEN-LAST:event_TABLAKeyTyped

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
       
        if(verificarCampos()){
            guardarFactura();
            
            visualizarFactura();
            crearArchivoTxtFactura();
            desactivarCampos();
        }else{
            JOptionPane.showMessageDialog(null, "Falta diligenciar algun dato para poder guardar la factura");
        }
       
        
    }//GEN-LAST:event_btnguardarActionPerformed

    private void txtbuscarclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarclienteActionPerformed
        // TODO add your handling code here:
        //buscarCliente();
          
        FrmVistaCliente form = new FrmVistaCliente();
        form.toFront();        
        form.setVisible(true);
    }//GEN-LAST:event_txtbuscarclienteActionPerformed

    private void txtultimafactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtultimafactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtultimafactActionPerformed

    private void txtcancelaconKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcancelaconKeyTyped
        // TODO add your handling code here:
        if(validacion.validarNumeros(evt)){
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtcancelaconKeyTyped

    private void txtcancelaconKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcancelaconKeyReleased
        // TODO add your handling code here:
        try {
            mostrarDevuelta();
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_txtcancelaconKeyReleased

    private void btnremisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnremisionActionPerformed
        // TODO add your handling code here:
        if(verificarCampos()){
            guardarRemision();
            
            visualizarRemision();
            crearArchivoTxtRemision();
            desactivarCampos();
        }else{
            JOptionPane.showMessageDialog(null, "Falta diligenciar algun dato para poder guardar la factura");
        }
    }//GEN-LAST:event_btnremisionActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        activarCampos();
        limpiarCampos();
        FrmFactura.listatemporal.clear();
        PFactura numFactura = new PFactura(); 
        lblnumerofact.setText(String.valueOf(numFactura.consultarNumeroFactura()));
    }//GEN-LAST:event_btnNuevoActionPerformed

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
            java.util.logging.Logger.getLogger(FrmFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmFactura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable TABLA;
    private javax.swing.JButton btnNuevo;
    public static javax.swing.JButton btnagregar;
    public static javax.swing.JButton btnguardar;
    public static javax.swing.JButton btnremision;
    private javax.swing.JButton btnsalir;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl;
    public static javax.swing.JLabel lblnumerofact;
    public static javax.swing.JTextField txtacumulado;
    private javax.swing.JButton txtbuscarcliente;
    public static javax.swing.JTextField txtcancelacon;
    public static javax.swing.JTextField txtdevuelta;
    public static javax.swing.JTextField txtnitcliente;
    public static javax.swing.JTextField txtnombrecliente;
    public static javax.swing.JTextField txttelefonocliente;
    public static javax.swing.JTextField txttotalfactura;
    public static javax.swing.JTextField txttotalsin;
    public static javax.swing.JTextField txtultimafact;
    // End of variables declaration//GEN-END:variables

    private void addWindowStateListener(WindowStateListener windowStateListener) {
        //throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
        //this.setVisible(true);
        //this.toFront();
       
    }

    private void addWindowFocusListener(WindowFocusListener windowFocusListener) {
       // throw new UnsupportedOperationException("Not supported yet."); 
//To change body of generated methods, choose Tools | Templates.
    //this.setVisible(true);
    //this.toFront();
     JOptionPane.showMessageDialog(null, "saliendo");
    }
}
