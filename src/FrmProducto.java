/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.VCliente;
import datos.VEmpleado;
import datos.VListaFactura;
import datos.VPersona;
import datos.VProducto;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.PCliente;
import logica.PEmpleado;
import logica.PListaFactura;
import logica.PProducto;
import logica.PValidacionNumerica;
import static presentacion.FrmFactura.codigoarticulo;

/**
 *
 * @author Edwin
 */
public class FrmProducto extends javax.swing.JInternalFrame {

    public static ArrayList<VProducto> listProducto = new ArrayList<>();
    String [] datosProducto = new String[10];
    PValidacionNumerica validacion = new PValidacionNumerica();
    /**
     * Creates new form FrmAbonar
     */
    public FrmProducto() {
        setClosable(true);
        initComponents();
        bloquearBotones();
    }
    
    ///SECCION DE LOS PROCEDIMIENTOS QUE VALIDAN INFORMACION Y ESTADO DE LOS OBJETOS DENTRO DEL FORMULARIO
    
    void limpiarCampos(){
         
        txtdescripcion.setText(null);
        txtidcategoria.setText(null);
        txtiva.setText(null);
        txtnombrearticulo.setText(null);
        txtnombrecategoria.setText(null);
        txtpreciocompra.setText(null);
        txtprecioventa.setText(null);
        txtstock.setText(null);
        txtidproducto.setText(null);
        
    }
    
    private void bloquearBotones(){
        
        txtidproducto.setVisible(false);
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
        
        return !(txtnombrecategoria.getText().isEmpty()
                || txtnombrearticulo.getText().isEmpty() 
                || txtiva.getText().isEmpty()
                || txtpreciocompra.getText().isEmpty() 
                || txtprecioventa.getText().isEmpty()
                
                || txtidcategoria.getText().isEmpty()
                || txtstock.getText().isEmpty());
                //no se valida que el campo descripcion este diligenciado pues se permite que el producto
                //no cuente con una descripcion
        
    }
    
    void mostrarAtributos(){
    
        txtdescripcion.setText(datosProducto[7]);
        txtidcategoria.setText(datosProducto[1]);
        txtiva.setText(datosProducto[5]);
        txtnombrearticulo.setText(datosProducto[2]);
        txtnombrecategoria.setText(datosProducto[8]);
        txtpreciocompra.setText(datosProducto[3]);
        txtprecioventa.setText(datosProducto[4]);
        txtstock.setText(datosProducto[9]);
        txtidproducto.setText(datosProducto[0]);
        
        //falta el uso de [6]que es precioconiva    
        
    }
    
    boolean mensajeConfirmacion(String accion){
        
        int opcion= JOptionPane.showConfirmDialog(null, "Realmente desea "+accion+" este registro?", "Confirmar accion", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return opcion==0;
    }
    
     //// SECCION DE LOS PROCEDIMIENTOS QUE LLAMAN A LAS FUNCIONES CRUD///
    
    void buscarProductos(){
        
        DefaultTableModel modelo;
        //VProducto producto = new VProducto();
        PProducto funciones = new PProducto();
        modelo = funciones.mostrar(txtbuscarproducto.getText());
        tablaproductos.setModel(modelo);
        //tablaproductos.setModel(funciones.mostrar(txtbuscarproducto.getText()));
    }
    
    void guardarProducto(){
        
         //validar que no esten vacios 
        VProducto producto  = new VProducto();// se crea la instancia de la  clase de los datos
        PProducto procedimientos = new PProducto(); //se crea instancia de los procedimientos
        
        //datos de la persona
        producto.setIdCategoria(Integer.parseInt(txtidcategoria.getText()));        
        producto.setNombre(txtnombrearticulo.getText());
        producto.setPrecioCompra(Double.parseDouble(txtpreciocompra.getText()));
        producto.setPrecioVenta(Double.parseDouble(txtprecioventa.getText()));
        producto.setIva(producto.getPrecioVenta()*0.19);
        producto.setPrecioconIva(producto.getPrecioVenta()*1.19);
        producto.setDescripcion(txtdescripcion.getText());
        producto.setStock(Integer.parseInt(txtstock.getText()));
        producto.setOperatividad("S");  //Operatividad indica si el registro esta activo o se "elimino" del sistema
       
        
        if (procedimientos.insertar(producto)) {
                JOptionPane.showMessageDialog(rootPane, "El producto fue registrado exitosamente");
                
            }
    }
    
    void actualizarProducto(){
        
        VProducto producto = new VProducto();// se crea la instancia de la  clase de los datos
        PProducto procedimientos = new PProducto(); //se crea instancia de los procedimientos        
        
        producto.setIdCategoria(Integer.parseInt(txtidcategoria.getText()));
        producto.setNombre(txtnombrearticulo.getText());
        producto.setPrecioCompra(Double.parseDouble(txtpreciocompra.getText()));
        producto.setPrecioVenta(Double.parseDouble(txtprecioventa.getText()));        
        producto.setIva(Double.parseDouble(txtiva.getText()));
        double precioConIva = (Double.parseDouble(txtpreciocompra.getText())*1.19);
        producto.setPrecioconIva(precioConIva);
        producto.setDescripcion(txtdescripcion.getText());
        producto.setStock(Integer.parseInt(txtstock.getText()));       
        producto.setOperatividad("S");
        producto.setIdProducto(Integer.parseInt(txtidproducto.getText()));
        
        if (procedimientos.actualizar(producto)) {
                JOptionPane.showMessageDialog(rootPane, "El producto fue actualizado exitosamente");
                
            }
        
    }
    
    void eliminarProducto(){
        
        VProducto producto = new VProducto();// se crea la instancia de la  clase de los datos
        PProducto procedimientos = new PProducto(); //se crea instancia de los procedimientos
                        
        producto.setIdProducto(Integer.parseInt(txtidproducto.getText()));
                
        if (procedimientos.eliminar(producto)) {
                JOptionPane.showMessageDialog(rootPane, "El producto fue eliminado exitosamente");
                
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
        tablaproductos = new javax.swing.JTable();
        txtbuscarproducto = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtnombrearticulo = new javax.swing.JTextField();
        txtprecioventa = new javax.swing.JTextField();
        txtiva = new javax.swing.JTextField();
        txtidcategoria = new javax.swing.JTextField();
        txtpreciocompra = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnnuevo = new javax.swing.JButton();
        txtstock = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnguardar = new javax.swing.JButton();
        btnactualizar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdescripcion = new javax.swing.JTextArea();
        txtnombrecategoria = new javax.swing.JTextField();
        btnbuscarcategoria = new javax.swing.JButton();
        txtidproducto = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnsalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Busqueda"));

        jLabel8.setText("Producto");

        tablaproductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Codigo", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaproductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaproductosMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tablaproductos);

        txtbuscarproducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscarproductoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarproductoKeyReleased(evt);
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
                        .addComponent(txtbuscarproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtbuscarproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion del producto"));

        txtprecioventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecioventaKeyTyped(evt);
            }
        });

        txtiva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtivaKeyTyped(evt);
            }
        });

        txtpreciocompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpreciocompraKeyTyped(evt);
            }
        });

        jLabel1.setText("Stock inicial");

        jLabel2.setText("Precio de compra");

        jLabel3.setText("Nombre articulo");

        jLabel6.setText("Descripcion");

        btnnuevo.setText("Nuevo");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        txtstock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtstockKeyTyped(evt);
            }
        });

        jLabel12.setText("Nombre categoria");

        jLabel13.setText("IVA");

        jLabel14.setText("Precio de venta");

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

        txtdescripcion.setColumns(20);
        txtdescripcion.setRows(5);
        jScrollPane1.setViewportView(txtdescripcion);

        btnbuscarcategoria.setText("...");
        btnbuscarcategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarcategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel6))
                                .addGap(1, 1, 1))
                            .addComponent(txtidproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnnuevo)
                        .addGap(18, 18, 18)
                        .addComponent(btnguardar)))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtnombrearticulo)
                        .addComponent(txtpreciocompra)
                        .addComponent(txtiva)
                        .addComponent(txtprecioventa, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                        .addComponent(txtstock)
                        .addComponent(jScrollPane1)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addComponent(btnactualizar)
                            .addGap(32, 32, 32)
                            .addComponent(btneliminar))
                        .addComponent(txtidcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtnombrecategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnbuscarcategoria)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtidcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtnombrecategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscarcategoria))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnombrearticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpreciocompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtprecioventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnuevo)
                    .addComponent(btnguardar)
                    .addComponent(btnactualizar)
                    .addComponent(btneliminar))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("PRODUCTO");

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
                .addContainerGap(47, Short.MAX_VALUE))
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
                .addContainerGap(64, Short.MAX_VALUE))
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
//        if(verificarCampos()){
//            JOptionPane.showMessageDialog(null, "datos llenos");
//        }else{
//            JOptionPane.showMessageDialog(null, "faltan datos");
//        }
        
        
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        
        if(mensajeConfirmacion("guardar")){
           
            if (verificarCampos()){//si verifica que los campos no esten vacios, se guardara
                guardarProducto();
               
            }else{
                JOptionPane.showMessageDialog(null, "Falta diligenciar alguno de los datos");
            }    
        }        
        
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnbuscarcategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarcategoriaActionPerformed
        // TODO add your handling code here:
        FrmVistaCategoria form = new FrmVistaCategoria();
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_btnbuscarcategoriaActionPerformed

    private void txtbuscarproductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarproductoKeyPressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txtbuscarproductoKeyPressed

    private void tablaproductosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaproductosMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount()==2) {
            int fila = tablaproductos.getSelectedRow();
            String codigo, cantidad;
            
            codigo = tablaproductos.getValueAt(fila, 0).toString();
            
            PProducto funciones = new PProducto();
            datosProducto= funciones.cargarDatosEspecificos(codigo);
            //funciones.llenarListaProducto(codigo);
           
            mostrarAtributos();
            desbloquearBotones();
            
            
        }
    }//GEN-LAST:event_tablaproductosMousePressed

    private void txtbuscarproductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarproductoKeyReleased
        // TODO add your handling code here:
         buscarProductos();
    }//GEN-LAST:event_txtbuscarproductoKeyReleased

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
        // TODO add your handling code here:
         if(mensajeConfirmacion("actualizar")){            
           if (verificarCampos()){//si verifica que los campos no esten vacios, se guardara              
                actualizarProducto();               
            }else{
                JOptionPane.showMessageDialog(null, "Falta diligenciar alguno de los datos");
            }    
       }
    }//GEN-LAST:event_btnactualizarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        // TODO add your handling code here:
        if(mensajeConfirmacion("eliminar")){
           
           if (verificarCampos()){//si verifica que los campos no esten vacios, se guardara
                eliminarProducto();
               
            }else{
                JOptionPane.showMessageDialog(null, "Falta diligenciar alguno de los datos");
            }    
       }
    }//GEN-LAST:event_btneliminarActionPerformed

    private void txtpreciocompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpreciocompraKeyTyped
        // TODO add your handling code here:
        if(validacion.validarNumeros(evt)){
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtpreciocompraKeyTyped

    private void txtprecioventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioventaKeyTyped
        // TODO add your handling code here:
        if(validacion.validarNumeros(evt)){
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtprecioventaKeyTyped

    private void txtivaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtivaKeyTyped
        // TODO add your handling code here:
        if(validacion.validarNumeros(evt)){
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtivaKeyTyped

    private void txtstockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstockKeyTyped
        // TODO add your handling code here:
        if(validacion.validarNumeros(evt)){
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtstockKeyTyped

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
            java.util.logging.Logger.getLogger(FrmProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmProducto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btnbuscarcategoria;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaproductos;
    private javax.swing.JTextField txtbuscarproducto;
    public static javax.swing.JTextArea txtdescripcion;
    public static javax.swing.JTextField txtidcategoria;
    private javax.swing.JTextField txtidproducto;
    public static javax.swing.JTextField txtiva;
    public static javax.swing.JTextField txtnombrearticulo;
    public static javax.swing.JTextField txtnombrecategoria;
    public static javax.swing.JTextField txtpreciocompra;
    public static javax.swing.JTextField txtprecioventa;
    public static javax.swing.JTextField txtstock;
    // End of variables declaration//GEN-END:variables
}
