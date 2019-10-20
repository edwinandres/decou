/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;


import datos.VEmpleado;
import datos.VPersona;
import datos.VProveedor;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import logica.PEmpleado;
import logica.PProveedor;

/**
 *
 * @author Edwin
 */
public class FrmProveedor extends javax.swing.JInternalFrame {

    String datosProveedor[]=new String[10];
    /**
     * Creates new form FrmAbonar
     */
    public FrmProveedor() {
        
        setClosable(true);
        initComponents();
        bloquearBotones();
    }

    void limpiarCampos(){
         
        txtcodigopersona.setText(null);
        txtapellidos.setText(null);
        txtcorreo.setText(null);
        txtdireccion.setText(null);
        txtnombreempresa.setText(null);
        txtnombre.setText(null);
        txtnumerodocumento.setText(null);
        txttelefono.setText(null);
        txttipopersona.setText(null);
        txtoperatividad.setText(null);  
        
    }
    
    private void bloquearBotones(){
        
        txttipopersona.setVisible(false);
        txtcodigopersona.setVisible(false);
        txtoperatividad.setVisible(false);
                
        btnguardar.setEnabled(true);
        btnactualizar.setEnabled(false);
        btneliminar.setEnabled(false);
        //btncancelar.setEnabled(false);
       // btnnuevo.setEnabled(true);
    }
    
    void desbloquearBotones(){
        btnguardar.setEnabled(false);
        btnactualizar.setEnabled(true);
        btneliminar.setEnabled(true);
       // btncancelar.setEnabled(true);
       // btnnuevo.setEnabled(true);
    }
    
    boolean verificarCampos(){
        
        return !(txtapellidos.getText().isEmpty()
                
                || txtcorreo.getText().isEmpty() 
                || txtdireccion.getText().isEmpty()
                || txtnombreempresa.getText().isEmpty() 
                || txtnombre.getText().isEmpty()
                || txtnumerodocumento.getText().isEmpty()  
                || txtnombreempresa.getText().isEmpty()
                || txttelefono.getText().isEmpty());
        
                
        
    }  
    
    void mostrarAtributos(){
        
    
        txtcodigopersona.setText(datosProveedor[0]);
        txtnumerodocumento.setText(datosProveedor[1]);
        txtapellidos.setText(datosProveedor[2]);
        txtnombre.setText(datosProveedor[3]);
        txttelefono.setText(datosProveedor[4]);
        txttipopersona.setText(datosProveedor[5]);
        txtdireccion.setText(datosProveedor[6]);
        txtcorreo.setText(datosProveedor[7]);
        txtoperatividad.setText(datosProveedor[8]); 
        txtnombreempresa.setText(datosProveedor[9]);  
        
        
        
    }
    
    boolean mensajeConfirmacion(String accion){
        
        int opcion= JOptionPane.showConfirmDialog(null, "Realmente desea "+accion+" este registro?", "Confirmar accion", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return opcion==0;
    }
    
    
    void buscarDocumento(){
        
        DefaultTableModel modelo;
        //VProducto producto = new VProducto();
        PProveedor funciones = new PProveedor();
        modelo = funciones.mostrar(txtbuscardocumento.getText());
        tablalistado.setModel(modelo);
        //tablaproductos.setModel(funciones.mostrar(txtbuscarproducto.getText()));
    }
    
    void buscarIdentificacion(){
        
        DefaultTableModel modelo;
        //VProducto producto = new VProducto();
        PProveedor funciones = new PProveedor();
        modelo = funciones.mostrar(txtnumerodocumento.getText());
        tablalistado.setModel(modelo);
        //tablaproductos.setModel(funciones.mostrar(txtbuscarproducto.getText()));
    }
     
    void guardarProveedor(){
         
          //validar que no esten vacios 
        VProveedor proveedor  = new VProveedor();// se crea la instancia de la  clase de los datos
        PProveedor procedimientos = new PProveedor(); //se crea instancia de los procedimientos
        
        //datos de la persona
        proveedor.setNumeroDocumento(txtnumerodocumento.getText());
        proveedor.setApellidos(txtapellidos.getText());
        proveedor.setNombre(txtnombre.getText());
        proveedor.setTelefono(txttelefono.getText());
        proveedor.setTipoPersona("P");
        proveedor.setDireccion(txtdireccion.getText());
        proveedor.setCorreo(txtcorreo.getText());
        proveedor.setOperatividad("S");  //Operatividad indica si el registro esta activo o se "elimino" del sistema
        
        //datos propios del cliente
        proveedor.setNombreEmpresa(txtnombreempresa.getText());
        
        
        if (procedimientos.insertar(proveedor)) {
                JOptionPane.showMessageDialog(rootPane, "El proveedor fue registrado exitosamente");
                
            }
     }
     
    void actualizarProveedor(){
        
        VProveedor proveedor = new VProveedor();// se crea la instancia de la  clase de los datos
        PProveedor procedimientos = new PProveedor(); //se crea instancia de los procedimientos        
        
        proveedor.setCodigoPersona(Integer.parseInt(txtcodigopersona.getText()));
        proveedor.setApellidos(txtapellidos.getText());
        proveedor.setNombre(txtnombre.getText());
        proveedor.setTelefono(txttelefono.getText());        
        proveedor.setTipoPersona(txttipopersona.getText());        
        proveedor.setDireccion(txtdireccion.getText());
        proveedor.setCorreo(txtcorreo.getText());
        proveedor.setOperatividad("S"); 
        proveedor.setNombreEmpresa(txtnombreempresa.getText());
        
        
        if (procedimientos.editar(proveedor)) {
                JOptionPane.showMessageDialog(rootPane, "El proveedor fue actualizado exitosamente");
                
            }
        
    }
     
    void eliminarProveedor(){
        
        VProveedor proveedor  = new VProveedor();// se crea la instancia de la  clase de los datos
        PProveedor procedimientos = new PProveedor(); //se crea instancia de los procedimientos
                        
        proveedor.setCodigoPersona(Integer.parseInt(txtcodigopersona.getText()));
                
        if (procedimientos.eliminar(proveedor)) {
                JOptionPane.showMessageDialog(rootPane, "El proveedor fue eliminado exitosamente");
                
        }else{
            
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

        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablalistado = new javax.swing.JTable();
        txtbuscardocumento = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtapellidos = new javax.swing.JTextField();
        txttelefono = new javax.swing.JTextField();
        txtdireccion = new javax.swing.JTextField();
        txtnumerodocumento = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnnuevo = new javax.swing.JButton();
        txtcorreo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnguardar = new javax.swing.JButton();
        btnactualizar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        txtnombreempresa = new javax.swing.JTextField();
        txtoperatividad = new javax.swing.JTextField();
        txttipopersona = new javax.swing.JTextField();
        txtcodigopersona = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnsalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Busqueda"));

        jLabel8.setText("Documento");

        tablalistado.setModel(new javax.swing.table.DefaultTableModel(
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
        tablalistado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablalistadoMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tablalistado);

        txtbuscardocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscardocumentoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(32, 32, 32)
                        .addComponent(txtbuscardocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtbuscardocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion del proveedor"));

        txtnumerodocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnumerodocumentoKeyReleased(evt);
            }
        });

        jLabel1.setText("Correo");

        jLabel2.setText("Nombre");

        jLabel3.setText("Apellidos");

        jLabel6.setText("Nombre de la empresa");

        btnnuevo.setText("Nuevo");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        jLabel12.setText("Numero de documento");

        jLabel13.setText("Direccion");

        jLabel14.setText("Telefono");

        btnguardar.setText("Guardar");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnactualizar.setText("Actualizar");
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });

        btneliminar.setText("Eliminar");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        txtcodigopersona.setText("codigoPer");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel13)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel14)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel3))
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel6))
                                        .addGap(1, 1, 1))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnnuevo)
                                .addGap(18, 18, 18)
                                .addComponent(btnguardar)))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(txtoperatividad, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcodigopersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addComponent(btnactualizar)
                            .addGap(32, 32, 32)
                            .addComponent(btneliminar))
                        .addComponent(txtapellidos)
                        .addComponent(txtnombre)
                        .addComponent(txtdireccion)
                        .addComponent(txttelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                        .addComponent(txtcorreo)
                        .addComponent(txtnumerodocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                        .addComponent(txtnombreempresa)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addComponent(txttipopersona, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtoperatividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txttipopersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnumerodocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtnombreempresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(txtcodigopersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnuevo)
                    .addComponent(btnguardar)
                    .addComponent(btnactualizar)
                    .addComponent(btneliminar))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("PROVEEDOR");

        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(937, 937, 937)
                        .addComponent(btnsalir))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel11)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(btnsalir))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(77, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        // TODO add your handling code here:
        bloquearBotones();
        limpiarCampos();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        // TODO add your handling code here:
       if(mensajeConfirmacion("guardar")){
           
            if (verificarCampos()){//si verifica que los campos no esten vacios, se guardara
                guardarProveedor();
                limpiarCampos();
               
            }else{
                JOptionPane.showMessageDialog(null, "Falta diligenciar alguno de los datos");
            }    
        }  
       
    }//GEN-LAST:event_btnguardarActionPerformed

    private void txtbuscardocumentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscardocumentoKeyReleased
        // TODO add your handling code here:
        buscarDocumento();
    }//GEN-LAST:event_txtbuscardocumentoKeyReleased

    private void tablalistadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablalistadoMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount()==2) {
            int fila = tablalistado.getSelectedRow();
            String codigo, cantidad;
            
            codigo = tablalistado.getValueAt(fila, 0).toString();
            
            PProveedor funciones = new PProveedor();
            datosProveedor = funciones.cargarDatosEspecificos(codigo);
            //funciones.llenarListaProducto(codigo);
           
            mostrarAtributos();
            desbloquearBotones();
            
            
        }
    }//GEN-LAST:event_tablalistadoMousePressed

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
        // TODO add your handling code here:
        if(mensajeConfirmacion("eliminar")){
           
           if (verificarCampos()){//si verifica que los campos no esten vacios, se guardara
                actualizarProveedor();
               
            }else{
                JOptionPane.showMessageDialog(null, "Falta diligenciar alguno de los datos");
            }    
       }
    }//GEN-LAST:event_btnactualizarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        // TODO add your handling code here:
        if(mensajeConfirmacion("eliminar")){
           
           if (verificarCampos()){//si verifica que los campos no esten vacios, se guardara
                eliminarProveedor();
               
            }else{
                JOptionPane.showMessageDialog(null, "Falta diligenciar alguno de los datos");
            }    
       }
    }//GEN-LAST:event_btneliminarActionPerformed

    private void txtnumerodocumentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumerodocumentoKeyReleased
        // TODO add your handling code here:
         buscarIdentificacion();
    }//GEN-LAST:event_txtnumerodocumentoKeyReleased

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
            java.util.logging.Logger.getLogger(FrmProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmProveedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablalistado;
    private javax.swing.JTextField txtapellidos;
    private javax.swing.JTextField txtbuscardocumento;
    private javax.swing.JTextField txtcodigopersona;
    private javax.swing.JTextField txtcorreo;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtnombreempresa;
    private javax.swing.JTextField txtnumerodocumento;
    private javax.swing.JTextField txtoperatividad;
    private javax.swing.JTextField txttelefono;
    private javax.swing.JTextField txttipopersona;
    // End of variables declaration//GEN-END:variables
}
