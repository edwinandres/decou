/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.VAbono;
import datos.VCredito;
import datos.VMora;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.PAbono;
import logica.PCredito;
import logica.PListaFactura;
import logica.PMora;
import logica.PProducto;
import logica.PValidacionNumerica;
import static presentacion.FrmFactura.codigoarticulo;

/**
 *
 * @author Edwin
 */
public class FrmMora extends javax.swing.JInternalFrame {

    PValidacionNumerica validacion = new PValidacionNumerica();
    /**
     * Creates new form FrmAbonar
     */
   
    public FrmMora() {
        initComponents();
        //establecerFecha();
        //ocultarColumnas();
        cargarCreditosConMora();
             
       
    }
    
    void cargarCreditosConMora(){
         DefaultTableModel modelo;
        //VProducto producto = new VProducto();
        PMora funciones = new PMora();
        modelo = funciones.mostrar("S");
        tablacreditosconmora.setModel(modelo);
        
    }
    
//    void actualizarEstadoCredito(){
//        VCredito credito = new VCredito();
//        PCredito funciones = new PCredito();
//        
//        credito.setEstado("cancelado");
//        credito.setIdCredito(Integer.parseInt(txtbuscarcredito.getText()));
//        
//        if(funciones.actualizar(credito)){
//            JOptionPane.showMessageDialog(null,"El credito ha sido cancelado en su totalidad");
//        }
//        
//    }
    
    
    void cargarDatosEspecificosCuota(){
        int filaCredito= tablacreditosconmora.getSelectedRow();
        int filaCuotas= tablacuotasconmora.getSelectedRow();
        String idCredito = tablacreditosconmora.getValueAt(filaCredito, 0).toString(); 
        String idCuota = tablacuotasconmora.getValueAt(filaCuotas, 0).toString();
        txtidcredito.setText(tablacreditosconmora.getValueAt(filaCredito, 0).toString());
        txtnombre.setText(tablacreditosconmora.getValueAt(filaCredito, 2).toString());
        txtnumerocuota.setText(tablacuotasconmora.getValueAt(filaCuotas, 0).toString());
        txtvalorcuota.setText(tablacuotasconmora.getValueAt(filaCuotas, 2).toString());
        txtfechacuota.setText(tablacuotasconmora.getValueAt(filaCuotas, 1).toString());
        double valoraPagar= Double.parseDouble(txtvalorcuota.getText());
        txttotalapagar.setText(String.valueOf(valoraPagar *2));
        txttotalabonado.setText(tablacuotasconmora.getValueAt(filaCuotas, 3).toString());
        txtidmora.setText(tablacuotasconmora.getValueAt(filaCuotas, 4).toString());
    }
    
    void habilitarCampos(){
        txtcantidadaabonar.setText(null);
//        txtobservaciones.setText(null);
    }
    
//    void realizarAbono(){
//        //crea una instancia de la clase abono y otra instancia de la clase procedimientos abono
//        VAbono abono = new VAbono();
//        PAbono funciones = new PAbono();
//        
//        //validar si se realiza el pago completo del credito
//        double saldopendiente = Double.parseDouble(txttotalapagar.getText());
//        double valorabono = Double.parseDouble(txtcantidadaabonar.getText());
//        if(valorabono>=saldopendiente){
//        ///
//        // llamar a actualizar credito
//        actualizarEstadoCredito();
//        
//        }
//        
//        //Obtenemos fecha actual en tres variables
//        Calendar cal = Calendar.getInstance();
//        int dia = (cal.get(Calendar.DATE));
//        int mes = (cal.get(Calendar.MONTH));
//        int annio = (cal.get(Calendar.YEAR)-1900);         
//        
//        //creamos el objeto abono con los valores obtenidos del formulario
//        abono.setIdCredito(Integer.parseInt(txtbuscarcredito.getText()));
//        abono.setFecha(new Date(annio,mes,dia));
//        abono.setValorAbono(Double.parseDouble(txtcantidadaabonar.getText()));
//        abono.setNumCuota((Integer.parseInt(txtvalorcuota.getText()))+1);
//        abono.setNotaCobranza(txtobservaciones.getText());
//        //abono.setOperatividad("S");
//        
//        //llamamos al procedimiento insertar de la clase PAbono, y enviamos el objeto abono que acabamos de crear         
//        if (funciones.insertarAbono(abono)) {
//            JOptionPane.showMessageDialog(null, "El abono ha sido registrado exitosamente");                
//        }  
//    }
    
//    void realizarNoAbono(){
//        //crea una instancia de la clase abono y otra instancia de la clase procedimientos abono
//        VAbono abono = new VAbono();
//        PAbono funciones = new PAbono();
//        
//        //Obtenemos fecha actual en tres variables
//        Calendar cal = Calendar.getInstance();
//        int dia = (cal.get(Calendar.DATE));
//        int mes = (cal.get(Calendar.MONTH));
//        int annio = (cal.get(Calendar.YEAR)-1900);         
//        
//        //creamos el objeto abono con los valores obtenidos del formulario
//        abono.setIdCredito(Integer.parseInt(txtbuscarcredito.getText()));
//        abono.setFecha(new Date(annio,mes,dia));
//        abono.setValorAbono(0);
//        abono.setNumCuota((Integer.parseInt(txtvalorcuota.getText()))+1);
//        abono.setNotaCobranza(txtobservaciones.getText());
//        //abono.setOperatividad("S");
//        
//        //llamamos al procedimiento insertar de la clase PAbono, y enviamos el objeto abono que acabamos de crear         
//        if (funciones.insertarAbono(abono)) {
//            JOptionPane.showMessageDialog(null, "Se ha registrado un abono no realizado");                
//        }  
  //  }
    
//    private void reportarMora(){
//        VMora mora = new VMora();
//        PMora funciones = new PMora();
//        
//        int numeroDeCuota= Integer.parseInt(txtvalorcuota.getText());
//         
//        mora.setIdCredito(Integer.parseInt(txtbuscarcredito.getText()));
//        mora.setNumeroCuota(numeroDeCuota);
//        mora.setOperatividad("S");
//        
//        funciones.insertar(mora);
//        
//    }
    
    private void ocultarColumnas() {
        tablacreditosconmora.getColumnModel().getColumn(2).setMaxWidth(0);
        tablacreditosconmora.getColumnModel().getColumn(2).setMinWidth(0);
        tablacreditosconmora.getColumnModel().getColumn(2).setPreferredWidth(0);
        
//        tablacreditos.getColumnModel().getColumn(3).setMaxWidth(0);
//        tablacreditos.getColumnModel().getColumn(3).setMinWidth(0);
//        tablacreditos.getColumnModel().getColumn(3).setPreferredWidth(0);
//        
//        tablacreditos.getColumnModel().getColumn(4).setMaxWidth(0);
//        tablacreditos.getColumnModel().getColumn(4).setMinWidth(0);
//        tablacreditos.getColumnModel().getColumn(4).setPreferredWidth(0);
//        //TABLA.getColumnModel().getColumn(0).setCellEditor(mipropiatabla.class.);
        //TABLA.getColumnModel().getColumn(0).
    }
    
    private void establecerFecha(){
        /* Las lineas del c2 sirven para establecer la fecha actual en un jdatechooser*/
        Calendar c2 = new GregorianCalendar();
        jDateChooser1.setCalendar(c2); 
        
        //Obtenemos fecha actual en tres variables
        Calendar cal = Calendar.getInstance();
        int dia = (cal.get(Calendar.DATE));
        int mes = (cal.get(Calendar.MONTH));
        int annio = (cal.get(Calendar.YEAR)-1900);  
        //Obtenida la fecha, la asignamos a un txt
        txtidcredito.setText(new Date(annio,mes,dia).toString());
       
    }  
    
    void limpiarTablaCuotasConMora(){
        
         String [] titulos = {"Numero Cuota","Fecha","Valor"}; 
         tablacuotasconmora.setModel(new DefaultTableModel(null,titulos));  
    }
    
//    void generarPlantillaAbono(String pCodigoCredito){
//        try {
//            //llenamos un array con la lista de todos los abonos y el procedimiento devuelve la suma de todos los abonos
//            PAbono funciones = new PAbono();
//            double totalAbonado=funciones.llenarListaAbonos(pCodigoCredito);
//            
//            //llevamos los valores requeridos para la plantilla de abono a las variables
//            int fila= tablacreditosconmora.getSelectedRow();
//            double totalcredito = Double.parseDouble(tablacreditosconmora.getValueAt(fila, 1).toString());            
//            int abonosrealizados = tablacuotasconmora.getRowCount();
//            double saldopendiente = totalcredito-totalAbonado;
//            
//            //cargamos los campos de texto con los valores que guardan las variables
//            txtnumerocuota.setText(String.valueOf(totalcredito));
//            txtbuscarnombre.setText(tablacreditosconmora.getValueAt(0, 2).toString()+" "+tablacreditosconmora.getValueAt(fila, 3).toString());
//            txtbuscarcredito.setText(pCodigoCredito);
//            txtnombre.setText(tablacreditosconmora.getValueAt(0, 2).toString()+" "+tablacreditosconmora.getValueAt(fila, 3).toString());
//            txtvalorcuota.setText(String.valueOf(abonosrealizados));
//            txttotalabonado.setText(String.valueOf(totalAbonado));            
//            txttotalapagar.setText(String.valueOf(totalcredito-totalAbonado));
//            txttelefonocliente.setText(tablacreditosconmora.getValueAt(0,4).toString());
//            /*
//            funciones.desplegarListaAbonos(tablaensayo);
//            funciones.desplegarListaAbonos(tablaabonos);*/
//        } catch (Exception e) {
//            JOptionPane.showConfirmDialog(rootPane, e);
//        }
//    }    
    
    void mostrarListaCreditos(String buscar) {

        try {
            DefaultTableModel modelo;
            PAbono funciones = new PAbono();
            modelo = funciones.mostrarListaCreditos(buscar);
            tablacreditosconmora.setModel(modelo);
            //ocultarColumnas();
           // lbltotalregistros.setText("Total de registros" + Integer.toString(funciones.totalregistros));

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(rootPane, e);
        }
    }
    
    void mostrarListaCuotasConMora(String buscar) {

        try {
            DefaultTableModel modelo;
            PMora funciones = new PMora();
            tablacuotasconmora.setModel(funciones.mostrarCuotasConMora(buscar));
            //funciones.desplegarListaAbonos(tablacuotasconmora);
            
            
            //ocultarColumnas();
           // lbltotalregistros.setText("Total de registros" + Integer.toString(funciones.totalregistros));

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(rootPane, e);
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

        jSlider1 = new javax.swing.JSlider();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablacuotasconmora = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablacreditosconmora = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtidcredito = new javax.swing.JTextField();
        txtfechacuota = new javax.swing.JTextField();
        txttotalapagar = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txtnumerocuota = new javax.swing.JTextField();
        txtvalorcuota = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnrealizarabono = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        txtcantidadaabonar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txttotalabonado = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnsalir = new javax.swing.JButton();
        txtidmora = new javax.swing.JTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Creditos en Mora"));

        tablacuotasconmora.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Numero cuota", "Fecha", "Valor"
            }
        ));
        tablacuotasconmora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablacuotasconmoraMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tablacuotasconmora);

        tablacreditosconmora.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id Credito", "Total Credito", "Nombre"
            }
        ));
        tablacreditosconmora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablacreditosconmoraMousePressed(evt);
            }
        });
        tablacreditosconmora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablacreditosconmoraKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tablacreditosconmora);

        jLabel8.setText("Seleccione un credito para ver el total de cuotas en mora");

        jLabel9.setText("Seleccione una cuota para ver detalles y realizar algun cambio en el estado de la cuota");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del credito"));

        txtidcredito.setEnabled(false);

        txtfechacuota.setEnabled(false);

        txttotalapagar.setEnabled(false);

        txtnombre.setEnabled(false);

        txtnumerocuota.setEnabled(false);

        txtvalorcuota.setEnabled(false);

        jLabel1.setText("Id credito");

        jLabel2.setText("Nombre");

        jLabel3.setText("Numero cuota");

        jLabel4.setText("Valor de la cuota");

        jLabel5.setText("Fecha cuota");

        jLabel6.setText("Total a cancelar");

        btnrealizarabono.setText("Realizar abono");
        btnrealizarabono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrealizarabonoActionPerformed(evt);
            }
        });

        jDateChooser1.setPreferredSize(new java.awt.Dimension(0, 0));

        jLabel12.setText("Cantidad a abonar");

        txtcantidadaabonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcantidadaabonarActionPerformed(evt);
            }
        });
        txtcantidadaabonar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcantidadaabonarKeyTyped(evt);
            }
        });

        jLabel7.setText("Total abonado");

        txttotalabonado.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel7))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtidcredito)
                                    .addComponent(txtnombre)
                                    .addComponent(txtnumerocuota)
                                    .addComponent(txtvalorcuota)
                                    .addComponent(txtfechacuota)
                                    .addComponent(txttotalapagar, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                    .addComponent(txtcantidadaabonar, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                    .addComponent(txttotalabonado)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(180, 180, 180)
                                .addComponent(btnrealizarabono)))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtidcredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtnumerocuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtvalorcuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtfechacuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txttotalapagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txttotalabonado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtcantidadaabonar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnrealizarabono)
                .addContainerGap())
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("CREDITOS CON MORA");

        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        txtidmora.setText("jTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnsalir)
                .addGap(151, 151, 151))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(340, 340, 340)
                        .addComponent(txtidmora, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtidmora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addComponent(btnsalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void tablacreditosconmoraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablacreditosconmoraKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tablacreditosconmoraKeyReleased

    private void tablacreditosconmoraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablacreditosconmoraMousePressed
        // TODO add your handling code here:      
        limpiarTablaCuotasConMora();
         
        // if (evt.getClickCount()==2) {
            int fila= tablacreditosconmora.getSelectedRow();
            String codigoCredito = tablacreditosconmora.getValueAt(fila, 4).toString(); 
            
            mostrarListaCuotasConMora(codigoCredito); 
            //generarPlantillaAbono(codigoCredito);
            
       
       // }
    }//GEN-LAST:event_tablacreditosconmoraMousePressed

    private void tablacuotasconmoraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablacuotasconmoraMousePressed
        // TODO add your handling code here:
        cargarDatosEspecificosCuota();
        /*
        
        if (evt.getClickCount()==2) {
            int fila= tablaabonos.getSelectedRow();
            String codigoCredito = tablaabonos.getValueAt(fila, 0).toString();
            mostrarListaAbonos(codigoCredito);
        }
        */
    }//GEN-LAST:event_tablacuotasconmoraMousePressed

    private void txtcantidadaabonarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadaabonarKeyTyped
        // TODO add your handling code here:
        //bloquea que sean digitadas letras sobre este txt
          char c=evt.getKeyChar();          
         
          if(Character.isLetter(c)) { 
              getToolkit().beep();               
              evt.consume(); 
               
              txtcantidadaabonar.setToolTipText("Ingresa Solo Numeros");              
          } 
    }//GEN-LAST:event_txtcantidadaabonarKeyTyped

    private void btnrealizarabonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrealizarabonoActionPerformed
        // TODO add your handling code here:
        VMora mora = new VMora();
        PMora funciones = new PMora();
        if(txtcantidadaabonar.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Digite la cantidad a abonar");
        }else{
            double totalabonado= Double.parseDouble(txttotalabonado.getText());
            double cantidadaabonar= Double.parseDouble(txtcantidadaabonar.getText());
            double nuevototalabonado= totalabonado+cantidadaabonar;
            double totalcuota = Double.parseDouble(txttotalapagar.getText());
            double nuevosaldopendiente= totalcuota-nuevototalabonado;
            
            mora.setTotalabonado(nuevototalabonado);
            mora.setAdeuda(nuevosaldopendiente);
            mora.setIdMora(Integer.parseInt(txtidmora.getText()));
            if(funciones.actualizar(mora)){
                JOptionPane.showMessageDialog(null,"Se ha efectuado el abono");
            }
            
            if(nuevosaldopendiente==0){
                //quitar operatividad
                mora.setOperatividad("N");
                mora.setIdMora(Integer.parseInt(txtidmora.getText()));
                if(funciones.eliminar(mora)){
                    JOptionPane.showMessageDialog(null,"Se ha cancelado la totalidad de la cuota");
                }
            }
        }
//        realizarAbono();
//        FrmImpresionAbono form = new FrmImpresionAbono();
//        form.toFront();
//        form.setVisible(true);
        
    }//GEN-LAST:event_btnrealizarabonoActionPerformed

    private void txtcantidadaabonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcantidadaabonarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcantidadaabonarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmMora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMora().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnrealizarabono;
    private javax.swing.JButton btnsalir;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTable tablacreditosconmora;
    private javax.swing.JTable tablacuotasconmora;
    public static javax.swing.JTextField txtcantidadaabonar;
    public static javax.swing.JTextField txtfechacuota;
    private javax.swing.JTextField txtidcredito;
    public static javax.swing.JTextField txtidmora;
    private javax.swing.JTextField txtnombre;
    public static javax.swing.JTextField txtnumerocuota;
    private javax.swing.JTextField txttotalabonado;
    public static javax.swing.JTextField txttotalapagar;
    public static javax.swing.JTextField txtvalorcuota;
    // End of variables declaration//GEN-END:variables
}
