/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.VCategoria;
import datos.VCliente;
import datos.VEmpleado;
import datos.VPersona;
import datos.VProducto;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.PCategoria;
import logica.PCliente;
import logica.PEmpleado;
import logica.PProducto;

/**
 *
 * @author Edwin
 */
public class FrmCategoria extends javax.swing.JInternalFrame {

    String [] datosCategoria = new String[3];
    /**
     * Creates new form FrmAbonar
     */
    public FrmCategoria() {
        initComponents();
        //txtidcategoria.setVisible(false);
        bloquearBotones();
    }
    
    ///SECCION DE LOS PROCEDIMIENTOS QUE VALIDAN INFORMACION Y ESTADO DE LOS OBJETOS DENTRO DEL FORMULARIO
    
    void limpiarCampos(){
        
        txtidcategoria.setText(null);
        txtnombrecategoria.setText(null);
        txtdescripcion.setText(null);
    }
    
    private void bloquearBotones(){
        
        txtidcategoria.setVisible(false);
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
        
        if(!txtnombrecategoria.getText().isEmpty()){
            if(!txtdescripcion.getText().isEmpty()){
                
                return true;
            }
            else{
                return false;
            }
        }else{
            return false;
        }           
        
    }
    
    void mostrarAtributos(){
    
        txtidcategoria.setText(datosCategoria[0]);
        txtnombrecategoria.setText(datosCategoria[1]);
        txtdescripcion.setText(datosCategoria[2]);
        
    }
    
    boolean mensajeConfirmacion(String accion){
        
        int opcion= JOptionPane.showConfirmDialog(null, "Realmente desea "+accion+" este registro?", "Confirmar accion", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return opcion==0;
    }
    
    //// SECCION DE LOS PROCEDIMIENTOS QUE LLAMAN A LAS FUNCIONES CRUD///
    
    
    void actualizarCategoria(){
    
        VCategoria categoria  = new VCategoria();// se crea la instancia de la  clase de los datos
        PCategoria procedimientos = new PCategoria(); //se crea instancia de los procedimientos        
        
        categoria.setNombre(txtnombrecategoria.getText());
        categoria.setDescripcion(txtdescripcion.getText());
        categoria.setIdCategoria(Integer.parseInt(txtidcategoria.getText()));        
        
        if (procedimientos.actualizar(categoria)) {
                JOptionPane.showMessageDialog(rootPane, "La categoria fue actualizada exitosamente");
                
            }
    
    }    
    
    void buscarCategorias(){
        
        DefaultTableModel modelo;
        VCategoria categoria = new VCategoria();
        PCategoria funciones = new PCategoria();
        modelo = funciones.mostrar(txtbuscarcategoria.getText());
        tablalistado.setModel(modelo);
        //tablaproductos.setModel(funciones.mostrar(txtbuscarproducto.getText()));
    }
    
    void guardarCategoria(){
    
        //validar que no esten vacios 
        VCategoria categoria  = new VCategoria();// se crea la instancia de la  clase de los datos
        PCategoria procedimientos = new PCategoria(); //se crea instancia de los procedimientos
        
        //datos de la persona
        categoria.setNombre(txtnombrecategoria.getText());
        categoria.setDescripcion(txtdescripcion.getText());
        categoria.setOperatividad("S");
        
        
        if (procedimientos.insertar(categoria)) {
                JOptionPane.showMessageDialog(rootPane, "La categoria fue registrada exitosamente");
                bloquearBotones();
                limpiarCampos();
            }
    }
    
    void eliminarCategoria(){
        
        VCategoria categoria  = new VCategoria();// se crea la instancia de la  clase de los datos
        PCategoria procedimientos = new PCategoria(); //se crea instancia de los procedimientos
                        
        categoria.setIdCategoria(Integer.parseInt(txtidcategoria.getText()));
                
        if (procedimientos.eliminar(categoria)) {
                JOptionPane.showMessageDialog(rootPane, "La categoria fue eliminada exitosamente");
                
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
        Categoria = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablalistado = new javax.swing.JTable();
        txtbuscarcategoria = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtnombrecategoria = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnnuevo = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        btnguardar = new javax.swing.JButton();
        btnactualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdescripcion = new javax.swing.JTextArea();
        txtidcategoria = new javax.swing.JTextField();
        btneliminar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        btnsalir = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Busqueda"));

        Categoria.setText("Categoria");

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

        txtbuscarcategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarcategoriaKeyReleased(evt);
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
                        .addComponent(Categoria)
                        .addGap(32, 32, 32)
                        .addComponent(txtbuscarcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Categoria)
                    .addComponent(txtbuscarcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion de la categoria"));

        jLabel6.setText("Descripcion");

        btnnuevo.setText("Nuevo");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        jLabel12.setText("Nombre categoria");

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

        txtdescripcion.setColumns(20);
        txtdescripcion.setRows(5);
        jScrollPane1.setViewportView(txtdescripcion);

        btneliminar.setText("Eliminar");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnnuevo)
                                .addGap(18, 18, 18)
                                .addComponent(btnguardar))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel6))))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(btnactualizar)
                                .addGap(28, 28, 28)
                                .addComponent(btneliminar))
                            .addComponent(txtnombrecategoria)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(txtidcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(txtidcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtnombrecategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(101, 101, 101)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnuevo)
                    .addComponent(btnguardar)
                    .addComponent(btnactualizar)
                    .addComponent(btneliminar))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("CATEGORIA");

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
        
       if(mensajeConfirmacion("guardar")){
           
           if (verificarCampos()){//si verifica que los campos no esten vacios, se guardara
                guardarCategoria();
               
            }else{
                JOptionPane.showMessageDialog(null, "Falta diligenciar alguno de los datos");
            }    
       }
        
        
        
   
        
    }//GEN-LAST:event_btnguardarActionPerformed

    private void txtbuscarcategoriaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarcategoriaKeyReleased
        // TODO add your handling code here:
        buscarCategorias();
    }//GEN-LAST:event_txtbuscarcategoriaKeyReleased

    private void tablalistadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablalistadoMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount()==2) {
            int fila = tablalistado.getSelectedRow();
            String codigo, cantidad;
            
            codigo = tablalistado.getValueAt(fila, 0).toString();
            
            PCategoria funciones = new PCategoria();
            datosCategoria= funciones.cargarDatosEspecificos(codigo);   
            
            mostrarAtributos();
            desbloquearBotones();
//            btnguardar.setEnabled(false);
//            btnactualizar.setEnabled(true);
//            btneliminar.setEnabled(true);
            //JOptionPane.showMessageDialog(null,"ensayo array, los datos son:"+datosCategoria[0]+" \n"+ datosCategoria[1]);
        }
    }//GEN-LAST:event_tablalistadoMousePressed

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
        // TODO add your handling code here:
        
        if(mensajeConfirmacion("actualizar")){
           
           if (verificarCampos()){//si verifica que los campos no esten vacios, se guardara
                actualizarCategoria();
               
            }else{
                JOptionPane.showMessageDialog(null, "Falta diligenciar alguno de los datos");
            }    
       }
        
    }//GEN-LAST:event_btnactualizarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        // TODO add your handling code here:
        if(mensajeConfirmacion("eliminar")){
           
           if (verificarCampos()){//si verifica que los campos no esten vacios, se guardara
                eliminarCategoria();
               
            }else{
                JOptionPane.showMessageDialog(null, "Falta diligenciar alguno de los datos");
            }    
       }
        
        
        
        
    }//GEN-LAST:event_btneliminarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
       
       

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCategoria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Categoria;
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablalistado;
    private javax.swing.JTextField txtbuscarcategoria;
    private javax.swing.JTextArea txtdescripcion;
    private javax.swing.JTextField txtidcategoria;
    private javax.swing.JTextField txtnombrecategoria;
    // End of variables declaration//GEN-END:variables
}
