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
public class FrmAbonar extends javax.swing.JInternalFrame {

    PValidacionNumerica validacion = new PValidacionNumerica();
    /**
     * Creates new form FrmAbonar
     */
   
    public FrmAbonar() {
        initComponents();
        establecerFecha();
        ocultarColumnas();
        txttelefonocliente.setVisible(false);
             
       
    }
    
    void actualizarEstadoCredito(){
        VCredito credito = new VCredito();
        PCredito funciones = new PCredito();
        
        credito.setEstado("cancelado");
        credito.setIdCredito(Integer.parseInt(txtbuscarcredito.getText()));
        
        if(funciones.actualizar(credito)){
            JOptionPane.showMessageDialog(null,"El credito ha sido cancelado en su totalidad");
        }
        
    }
    
    void habilitarCampos(){
        txtcantidadaabonar.setText(null);
        txtobservaciones.setText(null);
    }
    
    void realizarAbono(){
        //crea una instancia de la clase abono y otra instancia de la clase procedimientos abono
        VAbono abono = new VAbono();
        PAbono funciones = new PAbono();
        
        //validar si se realiza el pago completo del credito
        double saldopendiente = Double.parseDouble(txtsaldopendiente.getText());
        double valorabono = Double.parseDouble(txtcantidadaabonar.getText());
        if(valorabono>=saldopendiente){
        ///
        // llamar a actualizar credito
        actualizarEstadoCredito();
        
        }
        
        //Obtenemos fecha actual en tres variables
        Calendar cal = Calendar.getInstance();
        int dia = (cal.get(Calendar.DATE));
        int mes = (cal.get(Calendar.MONTH));
        int annio = (cal.get(Calendar.YEAR)-1900);         
        
        //creamos el objeto abono con los valores obtenidos del formulario
        abono.setIdCredito(Integer.parseInt(txtbuscarcredito.getText()));
        abono.setFecha(new Date(annio,mes,dia));
        abono.setValorAbono(Double.parseDouble(txtcantidadaabonar.getText()));
        abono.setNumCuota((Integer.parseInt(txtabonosrealizados.getText()))+1);
        abono.setNotaCobranza(txtobservaciones.getText());
        //abono.setOperatividad("S");
        
        //llamamos al procedimiento insertar de la clase PAbono, y enviamos el objeto abono que acabamos de crear         
        if (funciones.insertarAbono(abono)) {
            JOptionPane.showMessageDialog(null, "El abono ha sido registrado exitosamente");                
        }  
    }
    
    void realizarNoAbono(){
        //se realiza un no abono para llenar el campo en la tabla de la base de datos
        //con un valor cero , y de esta manera podemos verificar que cuotas no fueron pagadas
        
        //crea una instancia de la clase abono y otra instancia de la clase procedimientos abono
        VAbono abono = new VAbono();
        PAbono funciones = new PAbono();
        
        //Obtenemos fecha actual en tres variables
        Calendar cal = Calendar.getInstance();
        int dia = (cal.get(Calendar.DATE));
        int mes = (cal.get(Calendar.MONTH));
        int annio = (cal.get(Calendar.YEAR)-1900);         
        
        //creamos el objeto abono con los valores obtenidos del formulario
        abono.setIdCredito(Integer.parseInt(txtbuscarcredito.getText()));
        abono.setFecha(new Date(annio,mes,dia));
        abono.setValorAbono(0);
        abono.setNumCuota((Integer.parseInt(txtabonosrealizados.getText()))+1);
        abono.setNotaCobranza(txtobservaciones.getText());
        //abono.setOperatividad("S");
        
        //llamamos al procedimiento insertar de la clase PAbono, y enviamos el objeto abono que acabamos de crear         
        if (funciones.insertarAbono(abono)) {
            JOptionPane.showMessageDialog(null, "Se ha registrado un abono no realizado");                
        }  
    }
    
    private void reportarMora(){
        VMora mora = new VMora();
        PMora funciones = new PMora();
        
        int numeroDeCuota= Integer.parseInt(txtabonosrealizados.getText());
        Calendar cal = Calendar.getInstance();
        int dia = (cal.get(Calendar.DATE));
        int mes = (cal.get(Calendar.MONTH));
        int annio = (cal.get(Calendar.YEAR)-1900);  
        
        mora.setIdCredito(Integer.parseInt(txtbuscarcredito.getText()));
        mora.setNumeroCuota(numeroDeCuota+1);
        mora.setOperatividad("S");
        mora.setTotalcuota(Double.parseDouble(txtvaloracordado.getText()));
        mora.setTotalabonado(0);
        mora.setAdeuda(Double.parseDouble(txtvaloracordado.getText())*2);
        mora.setFecha(new Date(annio,mes,dia));
        funciones.insertar(mora);
        
    }
    
    private void ocultarColumnas() {
        tablacreditos.getColumnModel().getColumn(2).setMaxWidth(0);
        tablacreditos.getColumnModel().getColumn(2).setMinWidth(0);
        tablacreditos.getColumnModel().getColumn(2).setPreferredWidth(0);
        
        tablacreditos.getColumnModel().getColumn(3).setMaxWidth(0);
        tablacreditos.getColumnModel().getColumn(3).setMinWidth(0);
        tablacreditos.getColumnModel().getColumn(3).setPreferredWidth(0);
        
        tablacreditos.getColumnModel().getColumn(4).setMaxWidth(0);
        tablacreditos.getColumnModel().getColumn(4).setMinWidth(0);
        tablacreditos.getColumnModel().getColumn(4).setPreferredWidth(0);
        //TABLA.getColumnModel().getColumn(0).setCellEditor(mipropiatabla.class.);
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
        txtFecha.setText(new Date(annio,mes,dia).toString());
       
    }  
    void limpiarTablaAbonos(){
        
         String [] titulos = {"No.credito","Id abono","Abono","Fecha"}; 
         tablaabonos.setModel(new DefaultTableModel(null,titulos));  
    }
    
    void generarPlantillaAbono(String pCodigoCredito){
        try {
            //llenamos un array con la lista de todos los abonos y el procedimiento devuelve la suma de todos los abonos
            PAbono funciones = new PAbono();
            double totalAbonado=funciones.llenarListaAbonos(pCodigoCredito);
            
            //llevamos los valores requeridos para la plantilla de abono a las variables
            int fila= tablacreditos.getSelectedRow();
            double totalcredito = Double.parseDouble(tablacreditos.getValueAt(fila, 1).toString());            
            int abonosrealizados = tablaabonos.getRowCount();
            double saldopendiente = totalcredito-totalAbonado;
            
            //cargamos los campos de texto con los valores que guardan las variables
            txttotalcredito.setText(String.valueOf(totalcredito));
            txtbuscarnombre.setText(tablacreditos.getValueAt(0, 2).toString()+" "+tablacreditos.getValueAt(fila, 3).toString());
            txtbuscarcredito.setText(pCodigoCredito);
            txtnombre.setText(tablacreditos.getValueAt(0, 2).toString()+" "+tablacreditos.getValueAt(fila, 3).toString());
            txtabonosrealizados.setText(String.valueOf(abonosrealizados));
            txttotalabonado.setText(String.valueOf(totalAbonado));            
            txtsaldopendiente.setText(String.valueOf(totalcredito-totalAbonado));
            txttelefonocliente.setText(tablacreditos.getValueAt(0,4).toString());
            /*
            funciones.desplegarListaAbonos(tablaensayo);
            funciones.desplegarListaAbonos(tablaabonos);*/
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(rootPane, e);
        }
    }
    
    void consultarValorAcordado(String codigo){
        
        
        PAbono funciones = new PAbono();
        txtvaloracordado.setText(String.valueOf(funciones.consultarValorAcordado(codigo)));
        
    }
    
    
    void mostrarListaCreditos(String buscar) {

        try {
            DefaultTableModel modelo;
            PAbono funciones = new PAbono();
            modelo = funciones.mostrarListaCreditos(buscar);
            tablacreditos.setModel(modelo);
            //ocultarColumnas();
           // lbltotalregistros.setText("Total de registros" + Integer.toString(funciones.totalregistros));

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(rootPane, e);
        }
    }
    
    void mostrarListaAbonos(String buscar) {

        try {
            DefaultTableModel modelo;
            PAbono funciones = new PAbono();
            funciones.llenarListaAbonos(buscar);
            funciones.desplegarListaAbonos(tablaabonos);
            
            
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
        txtcedula = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnbuscar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtbuscarnombre = new javax.swing.JTextField();
        txtbuscarcredito = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaabonos = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablacreditos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txtFecha = new javax.swing.JTextField();
        txttotalabonado = new javax.swing.JTextField();
        txtsaldopendiente = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txttotalcredito = new javax.swing.JTextField();
        txtabonosrealizados = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtobservaciones = new javax.swing.JTextArea();
        btnrealizarabono = new javax.swing.JButton();
        btnreportarmora = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        txtcantidadaabonar = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtvaloracordado = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnsalir = new javax.swing.JButton();
        txttelefonocliente = new javax.swing.JTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccion"));

        txtcedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcedulaKeyTyped(evt);
            }
        });

        jLabel8.setText("Cedula");

        btnbuscar.setText("Buscar");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        jLabel9.setText("Nombre");

        jLabel10.setText("Numero de credito");

        txtbuscarnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscarnombreKeyTyped(evt);
            }
        });

        tablaabonos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Factura", "Fecha", "Total", "Cliente"
            }
        ));
        tablaabonos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaabonosMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tablaabonos);

        tablacreditos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id Credito", "Total Credito", "Title 3", "Title 4", "Title 5"
            }
        ));
        tablacreditos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablacreditosMousePressed(evt);
            }
        });
        tablacreditos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablacreditosKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tablacreditos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtcedula, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                .addGap(28, 28, 28)
                                .addComponent(btnbuscar))
                            .addComponent(txtbuscarnombre)
                            .addComponent(txtbuscarcredito)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(btnbuscar))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtbuscarnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtbuscarcredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del credito"));

        txtFecha.setEnabled(false);

        txttotalabonado.setEnabled(false);

        txtsaldopendiente.setEnabled(false);

        txtnombre.setEnabled(false);

        txttotalcredito.setEnabled(false);

        txtabonosrealizados.setEnabled(false);

        jLabel1.setText("Fecha");

        jLabel2.setText("Nombre");

        jLabel3.setText("Total del credito");

        jLabel4.setText("Abonos realizados");

        jLabel5.setText("Total Abonado");

        jLabel6.setText("Saldo pendiente");

        jLabel7.setText("Observaciones");

        txtobservaciones.setColumns(20);
        txtobservaciones.setRows(5);
        jScrollPane1.setViewportView(txtobservaciones);

        btnrealizarabono.setText("Realizar abono");
        btnrealizarabono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrealizarabonoActionPerformed(evt);
            }
        });

        btnreportarmora.setText("Reportar mora");
        btnreportarmora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreportarmoraActionPerformed(evt);
            }
        });

        jDateChooser1.setPreferredSize(new java.awt.Dimension(0, 0));

        jLabel12.setText("Cantidad a abonar");

        txtcantidadaabonar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcantidadaabonarKeyTyped(evt);
            }
        });

        jLabel13.setText("Valor acordado");

        txtvaloracordado.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(btnrealizarabono)
                            .addComponent(jLabel13))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnreportarmora)
                                .addGap(66, 66, 66))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFecha)
                                    .addComponent(txtnombre)
                                    .addComponent(txttotalcredito)
                                    .addComponent(txtabonosrealizados)
                                    .addComponent(txttotalabonado)
                                    .addComponent(txtsaldopendiente)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                    .addComponent(txtcantidadaabonar)
                                    .addComponent(txtvaloracordado)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txttotalcredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtabonosrealizados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addComponent(jLabel5))
                    .addComponent(txttotalabonado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtsaldopendiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtvaloracordado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtcantidadaabonar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnrealizarabono)
                    .addComponent(btnreportarmora))
                .addContainerGap())
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("REALIZAR ABONOS");

        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        txttelefonocliente.setText("jTextField1");

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
                        .addComponent(txttelefonocliente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txttelefonocliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnsalir)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        // TODO add your handling code here:
        if(!txtcedula.getText().isEmpty()){
             mostrarListaCreditos(txtcedula.getText());
             ocultarColumnas();
             
        }
       
       
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void tablacreditosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablacreditosKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tablacreditosKeyReleased

    private void tablacreditosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablacreditosMousePressed
        // TODO add your handling code here:      
        limpiarTablaAbonos();
         
        // if (evt.getClickCount()==2) {
            int fila= tablacreditos.getSelectedRow();
            String codigoCredito = tablacreditos.getValueAt(fila, 0).toString(); 
            
            mostrarListaAbonos(codigoCredito); 
            generarPlantillaAbono(codigoCredito);
            consultarValorAcordado(txtbuscarcredito.getText());
            
       
       // }
    }//GEN-LAST:event_tablacreditosMousePressed

    private void tablaabonosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaabonosMousePressed
        // TODO add your handling code here:
        /*
        if (evt.getClickCount()==2) {
            int fila= tablaabonos.getSelectedRow();
            String codigoCredito = tablaabonos.getValueAt(fila, 0).toString();
            mostrarListaAbonos(codigoCredito);
        }
        */
    }//GEN-LAST:event_tablaabonosMousePressed

    private void txtcedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcedulaKeyTyped
        // TODO add your handling code here:
        //bloquea que sean digitadas letras sobre este txt
          char c=evt.getKeyChar();          
         
          if(Character.isLetter(c)) { 
              getToolkit().beep();               
              evt.consume(); 
               
              txtcedula.setToolTipText("Ingresa Solo Numeros");              
          } 
    }//GEN-LAST:event_txtcedulaKeyTyped

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
        realizarAbono();
        FrmImpresionAbono form = new FrmImpresionAbono();
        form.toFront();
        form.setVisible(true);
        
    }//GEN-LAST:event_btnrealizarabonoActionPerformed

    private void txtbuscarnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarnombreKeyTyped
        // TODO add your handling code here:
        
        if(validacion.validarNumeros(evt)){
            getToolkit().beep();
        }
        
    }//GEN-LAST:event_txtbuscarnombreKeyTyped

    private void btnreportarmoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreportarmoraActionPerformed
        // TODO add your handling code here:
        if(txtabonosrealizados.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"No se reconoce la informacion que se desea enviar","ATENCION",JOptionPane.INFORMATION_MESSAGE);
        }else{
           reportarMora();
           realizarNoAbono();
        }
        
    }//GEN-LAST:event_btnreportarmoraActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAbonar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAbonar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAbonar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAbonar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAbonar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btnrealizarabono;
    private javax.swing.JButton btnreportarmora;
    private javax.swing.JButton btnsalir;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTable tablaabonos;
    private javax.swing.JTable tablacreditos;
    private javax.swing.JTextField txtFecha;
    public static javax.swing.JTextField txtabonosrealizados;
    public static javax.swing.JTextField txtbuscarcredito;
    public static javax.swing.JTextField txtbuscarnombre;
    public static javax.swing.JTextField txtcantidadaabonar;
    public static javax.swing.JTextField txtcedula;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextArea txtobservaciones;
    public static javax.swing.JTextField txtsaldopendiente;
    public static javax.swing.JTextField txttelefonocliente;
    public static javax.swing.JTextField txttotalabonado;
    public static javax.swing.JTextField txttotalcredito;
    private javax.swing.JTextField txtvaloracordado;
    // End of variables declaration//GEN-END:variables
}
