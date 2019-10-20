/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.VEmpleado;
import datos.VPersona;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.PEmpleado;

/**
 *
 * @author Edwin
 */
public class FrmEmpleado extends javax.swing.JInternalFrame {

    String [] datosEmpleado = new String[12];
    /**
     * Creates new form FrmAbonar
     */
    public FrmEmpleado() {
        initComponents();
        bloquearBotones();
    }

    void limpiarCampos(){
         
        txtcodigopersona.setText(null);
        txtapellidos.setText(null);
        txtcorreo.setText(null);
        txtdireccion.setText(null);
        txtcargo.setText(null);
        txtnombre.setText(null);
        txtnumerodocumento.setText(null);
        txttelefono.setText(null);
        txttipopersona.setText(null);
        txtoperatividad.setText(null);  
        txtlogin.setText(null);
        txtpassword.setText(null);
        
    }
    
    private void bloquearBotones(){
        
        txtoperatividad.setVisible(false);
        txtcodigopersona.setVisible(false);
        txttipopersona.setVisible(false);
                
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
                || txtcargo.getText().isEmpty() 
                || txtnombre.getText().isEmpty()
                || txtnumerodocumento.getText().isEmpty()
                || txtlogin.getText().isEmpty()
                || txtpassword.getText().isEmpty()
                || txttelefono.getText().isEmpty());
                //no se valida que el campo descripcion este diligenciado pues se permite que el producto
                //no cuente con una descripcion
        
    }  
    
    void mostrarAtributos(){
    
        txtcodigopersona.setText(datosEmpleado[0]);
        txtnumerodocumento.setText(datosEmpleado[1]);
        txtapellidos.setText(datosEmpleado[2]);
        txtnombre.setText(datosEmpleado[3]);
        txttelefono.setText(datosEmpleado[4]);
        txttipopersona.setText(datosEmpleado[5]);
        txtdireccion.setText(datosEmpleado[6]);
        txtcorreo.setText(datosEmpleado[7]);
        txtoperatividad.setText(datosEmpleado[8]);         
        txtcargo.setText(datosEmpleado[9]);     
        txtlogin.setText(datosEmpleado[10]);
        txtpassword.setText(datosEmpleado[11]);      
       
         
        
    }
    
    boolean mensajeConfirmacion(String accion){
        
        int opcion= JOptionPane.showConfirmDialog(null, "Realmente desea "+accion+" este registro?", "Confirmar accion", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return opcion==0;
    }
    
    
    void buscarDocumento(){
        
        DefaultTableModel modelo;
        //VProducto producto = new VProducto();
        PEmpleado funciones = new PEmpleado();
        modelo = funciones.mostrar(txtbuscardocumento.getText());
        tablalistado.setModel(modelo);
        //tablaproductos.setModel(funciones.mostrar(txtbuscarproducto.getText()));
    }
    
    void buscarIdentificacion(){
        
        DefaultTableModel modelo;
        //VProducto producto = new VProducto();
        PEmpleado funciones = new PEmpleado();
        modelo = funciones.mostrar(txtnumerodocumento.getText());
        tablalistado.setModel(modelo);
        //tablaproductos.setModel(funciones.mostrar(txtbuscarproducto.getText()));
    }
     
    void guardarEmpleado(){
         
          //validar que no esten vacios 
        VEmpleado empleado  = new VEmpleado();// se crea la instancia de la  clase de los datos
        PEmpleado procedimientos = new PEmpleado(); //se crea instancia de los procedimientos
        
        //datos de la persona
        empleado.setNumeroDocumento(txtnumerodocumento.getText());
        empleado.setApellidos(txtapellidos.getText());
        empleado.setNombre(txtnombre.getText());
        empleado.setTelefono(txttelefono.getText());
        empleado.setTipoPersona("E");
        empleado.setDireccion(txtdireccion.getText());
        empleado.setCorreo(txtcorreo.getText());
        empleado.setOperatividad("S");  //Operatividad indica si el registro esta activo o se "elimino" del sistema
        
        //datos propios del cliente
        empleado.setCargo(txtcargo.getText());
        empleado.setLogin(txtlogin.getText());
        empleado.setPassword(txtpassword.getText());
        
        
        if (procedimientos.insertar(empleado)) {
                JOptionPane.showMessageDialog(rootPane, "El cliente fue registrado exitosamente");
                
            }
     }
     
    void actualizarEmpleado(){
        
        VEmpleado empleado = new VEmpleado();// se crea la instancia de la  clase de los datos
        PEmpleado procedimientos = new PEmpleado(); //se crea instancia de los procedimientos        
        
        //empleado.setIdpersona(txtnumerodocumento.getText());
        empleado.setApellidos(txtapellidos.getText());
        empleado.setNombre(txtnombre.getText());
        empleado.setTelefono(txttelefono.getText());        
        empleado.setTipoPersona(txttipopersona.getText());        
        empleado.setDireccion(txtdireccion.getText());
        empleado.setCorreo(txtcorreo.getText());
        empleado.setOperatividad("S"); 
        empleado.setCargo(txtcargo.getText());
        empleado.setLogin(txtlogin.getText());
        empleado.setPassword(txtpassword.getText());
        empleado.setCodigoPersona(Integer.parseInt(txtcodigopersona.getText()));
        
        
        if (procedimientos.editar(empleado)) {
                JOptionPane.showMessageDialog(rootPane, "El empleado fue actualizado exitosamente");
                
            }
        
    }
     
    void eliminarEmpleado(){
        
        VEmpleado empleado = new VEmpleado();// se crea la instancia de la  clase de los datos
        PEmpleado procedimientos = new PEmpleado(); //se crea instancia de los procedimientos
                        
        empleado.setCodigoPersona(Integer.parseInt(txtcodigopersona.getText()));
                
        if (procedimientos.eliminar(empleado)) {
                JOptionPane.showMessageDialog(rootPane, "El cliente fue eliminado exitosamente");
                
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
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnnuevo = new javax.swing.JButton();
        txtcorreo = new javax.swing.JTextField();
        txtcargo = new javax.swing.JTextField();
        txtlogin = new javax.swing.JTextField();
        txtpassword = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnguardar = new javax.swing.JButton();
        btnactualizar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        txttipopersona = new javax.swing.JTextField();
        txtoperatividad = new javax.swing.JTextField();
        txtcodigopersona = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnsalir = new javax.swing.JButton();

        setClosable(true);
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion del empleado"));

        txtnumerodocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnumerodocumentoKeyReleased(evt);
            }
        });

        jLabel1.setText("Correo");

        jLabel2.setText("Nombre");

        jLabel3.setText("Apellidos");

        jLabel4.setText("Login");

        jLabel6.setText("Cargo");

        btnnuevo.setText("Nuevo");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        jLabel12.setText("Numero de documento");

        jLabel13.setText("Direccion");

        jLabel14.setText("Telefono");

        jLabel15.setText("Password");

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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txttipopersona, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(32, 32, 32)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel12)
                                .addComponent(jLabel15)
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
                            .addComponent(btnguardar))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(btnactualizar)
                                .addGap(32, 32, 32)
                                .addComponent(btneliminar))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtapellidos)
                                .addComponent(txtnombre)
                                .addComponent(txtdireccion)
                                .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtcorreo)
                                .addComponent(txtcargo)
                                .addComponent(txtlogin)
                                .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtnumerodocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(txtoperatividad, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtcodigopersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txttipopersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtoperatividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcodigopersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtlogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnuevo)
                    .addComponent(btnguardar)
                    .addComponent(btnactualizar)
                    .addComponent(btneliminar))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("EMPLEADOS");

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
                guardarEmpleado();
               
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
            
            PEmpleado funciones = new PEmpleado();
            datosEmpleado = funciones.cargarDatosEspecificos(codigo);
            //funciones.llenarListaProducto(codigo);
           
            mostrarAtributos();
            desbloquearBotones();
            
            
        }
    }//GEN-LAST:event_tablalistadoMousePressed

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
        // TODO add your handling code here:
        if(mensajeConfirmacion("actualizar")){
           
           if (verificarCampos()){//si verifica que los campos no esten vacios, se guardara
                actualizarEmpleado();
               
            }else{
                JOptionPane.showMessageDialog(null, "Falta diligenciar alguno de los datos");
            }    
       }
    }//GEN-LAST:event_btnactualizarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        // TODO add your handling code here:
        if(mensajeConfirmacion("eliminar")){
           
           if (verificarCampos()){//si verifica que los campos no esten vacios, se guardara
                eliminarEmpleado();
               
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
            java.util.logging.Logger.getLogger(FrmEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new FrmEmpleado().setVisible(true);
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
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablalistado;
    private javax.swing.JTextField txtapellidos;
    private javax.swing.JTextField txtbuscardocumento;
    private javax.swing.JTextField txtcargo;
    private javax.swing.JTextField txtcodigopersona;
    private javax.swing.JTextField txtcorreo;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtlogin;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtnumerodocumento;
    private javax.swing.JTextField txtoperatividad;
    private javax.swing.JTextField txtpassword;
    private javax.swing.JTextField txttelefono;
    private javax.swing.JTextField txttipopersona;
    // End of variables declaration//GEN-END:variables
}
