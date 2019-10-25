
package presentacion;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.*;
import java.awt.print.PrinterJob;
import javax.swing.JOptionPane;
import logica.PFactura;
import static presentacion.FrmFactura.listaArticulos;
import static presentacion.FrmFactura.listatemporal;
import static presentacion.FrmFacturacion.lblnumerofact;



public class FrmImpresionAbono extends javax.swing.JFrame implements Printable{

    public FrmImpresionAbono() {
        initComponents();
        cargarDatos();
        
    }
    
    public void cargarDatos(){
        lbltotalabonado.setText(FrmAbonar.txttotalabonado.getText());
        lblabonosrealizados.setText(FrmAbonar.txtabonosrealizados.getText());
        lblsaldopendiente.setText(FrmAbonar.txtsaldopendiente.getText());
        lblesteabono.setText(FrmAbonar.txtcantidadaabonar.getText());
        lblnitcliente.setText(FrmAbonar.txtcedula.getText());
        lblnombrecliente.setText(FrmAbonar.txtbuscarnombre.getText());
        lbltelefonocliente.setText(FrmAbonar.txttelefonocliente.getText());
        lblidcredito.setText(FrmAbonar.txtbuscarcredito.getText());
        lbltotalcredito.setText(FrmAbonar.txttotalcredito.getText());
        double saldopendiente= Double.parseDouble(FrmAbonar.txtsaldopendiente.getText());
        double abonorealizado= Double.parseDouble(FrmAbonar.txtcantidadaabonar.getText());
        double nuevosaldopendiente= saldopendiente-abonorealizado;
        lblnuevosaldopendiente.setText(String.valueOf(nuevosaldopendiente));
        PFactura numFactura = new PFactura(); 
        lblnumerofactura.setText(String.valueOf(numFactura.consultarNumeroFactura()));
         
    }

  
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        recibo = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblnombrecliente = new javax.swing.JLabel();
        lblnitcliente = new javax.swing.JLabel();
        lbltelefonocliente = new javax.swing.JLabel();
        lbltelefono = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblcorreocliente = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblnumerofactura = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblabonosrealizados = new javax.swing.JLabel();
        lbltotalabonado = new javax.swing.JLabel();
        lblesteabono = new javax.swing.JLabel();
        lblsaldopendiente = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblidcredito = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbltotalcredito = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblcuotasconmora = new javax.swing.JLabel();
        lblnuevosaldopendiente = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        recibo.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jLabel6.setText("DecoU");

        jLabel7.setText("Nit: 10236542-8");

        jLabel8.setText("Cliente:");

        jLabel9.setText("Documento o nit:");

        jLabel10.setText("Telefono: 8586598");

        lblnombrecliente.setText("jLabel6");

        lblnitcliente.setText("jLabel7");

        lbltelefonocliente.setText("jLabel8");

        lbltelefono.setText("Telefono:");

        jLabel12.setText("Direccion: calle tata 5897");

        jLabel13.setText("Correo: decoutata@gmail.com");

        jLabel14.setText("Correo:");

        lblcorreocliente.setText("Correo electronico");

        jLabel18.setText("FACTURA DE VENTA:");

        lblnumerofactura.setText("jLabel14");

        jLabel15.setText("Abonos realizados");

        jLabel16.setText("Total abonado");

        jLabel17.setText("Este abono");

        jLabel19.setText("Nuevo saldo pendiente");

        jLabel22.setText("Saldo pendiente");

        lblabonosrealizados.setText("jLabel23");

        lbltotalabonado.setText("jLabel24");

        lblesteabono.setText("jLabel25");

        lblsaldopendiente.setText("jLabel26");

        jLabel23.setText("Id credito");

        lblidcredito.setText("jLabel24");

        jLabel1.setText("Total credito");

        lbltotalcredito.setText("jLabel2");

        jLabel2.setText("Cuotas con mora");

        lblcuotasconmora.setText("jLabel3");

        lblnuevosaldopendiente.setText("jLabel3");

        javax.swing.GroupLayout reciboLayout = new javax.swing.GroupLayout(recibo);
        recibo.setLayout(reciboLayout);
        reciboLayout.setHorizontalGroup(
            reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reciboLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(reciboLayout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(157, 157, 157)
                            .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel12)
                                .addComponent(jLabel13)
                                .addComponent(jLabel10)))
                        .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(reciboLayout.createSequentialGroup()
                                .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(reciboLayout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(63, 63, 63)
                                        .addComponent(lblcorreocliente))
                                    .addGroup(reciboLayout.createSequentialGroup()
                                        .addComponent(lbltelefono)
                                        .addGap(54, 54, 54)
                                        .addComponent(lbltelefonocliente))
                                    .addGroup(reciboLayout.createSequentialGroup()
                                        .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel8))
                                        .addGap(18, 18, 18)
                                        .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblnombrecliente)
                                            .addComponent(lblnitcliente))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblnumerofactura))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 131, Short.MAX_VALUE))
            .addGroup(reciboLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reciboLayout.createSequentialGroup()
                        .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(reciboLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(123, 123, 123)
                                .addComponent(lblesteabono))
                            .addGroup(reciboLayout.createSequentialGroup()
                                .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel1))
                                .addGap(94, 94, 94)
                                .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblidcredito)
                                    .addComponent(lblsaldopendiente)
                                    .addGroup(reciboLayout.createSequentialGroup()
                                        .addComponent(lbltotalcredito)
                                        .addGap(117, 117, 117)
                                        .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(reciboLayout.createSequentialGroup()
                                                .addComponent(jLabel15)
                                                .addGap(67, 67, 67)
                                                .addComponent(lblabonosrealizados))
                                            .addGroup(reciboLayout.createSequentialGroup()
                                                .addComponent(jLabel16)
                                                .addGap(67, 67, 67)
                                                .addComponent(lbltotalabonado))
                                            .addGroup(reciboLayout.createSequentialGroup()
                                                .addComponent(jLabel19)
                                                .addGap(67, 67, 67)
                                                .addComponent(lblnuevosaldopendiente))))))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(148, Short.MAX_VALUE))
                    .addGroup(reciboLayout.createSequentialGroup()
                        .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(reciboLayout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addComponent(lblcuotasconmora))
                            .addComponent(jLabel2)
                            .addComponent(jLabel22))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        reciboLayout.setVerticalGroup(
            reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reciboLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(reciboLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblnombrecliente, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8))
                .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reciboLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(lblnumerofactura))
                    .addGroup(reciboLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(lblnitcliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbltelefono)
                            .addComponent(lbltelefonocliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(lblcorreocliente))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lblidcredito))
                .addGap(18, 18, 18)
                .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbltotalcredito)
                    .addComponent(jLabel16)
                    .addComponent(lbltotalabonado))
                .addGap(18, 18, 18)
                .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblcuotasconmora)
                    .addComponent(jLabel15)
                    .addComponent(lblabonosrealizados))
                .addGap(18, 18, 18)
                .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(lblsaldopendiente))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lblesteabono)
                    .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(lblnuevosaldopendiente)))
                .addGap(211, 211, 211))
        );

        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimir(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(recibo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(recibo, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnImprimir)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imprimir(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimir
     
            try {
                //JOptionPane.showMessageDialog(null, "Error al tratar de imprimir","Error\n",JOptionPane.ERROR_MESSAGE);
                PrinterJob gap = PrinterJob.getPrinterJob();
                gap.setPrintable(this);
                boolean  top =gap.printDialog();
                if(top){
                    gap.print();
                    listaArticulos.clear();
                    listatemporal.clear();
                    //FrmAbonar.limpiarCampos();
                    //PFactura numFactura = new PFactura();        
                    //FrmFactura.lblnumerofact.setText(String.valueOf(numFactura.consultarNumeroFactura()));
                    this.dispose();
                }
            } catch (PrinterException e) {
                JOptionPane.showMessageDialog(null, "Error al tratar de imprimir","Error\n"+ e,ERROR);
            }     
        
       
    }//GEN-LAST:event_imprimir

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
            java.util.logging.Logger.getLogger(FrmImpresionAbono.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmImpresionAbono.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmImpresionAbono.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmImpresionAbono.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new FrmImpresionAbono().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblabonosrealizados;
    private javax.swing.JLabel lblcorreocliente;
    private javax.swing.JLabel lblcuotasconmora;
    private javax.swing.JLabel lblesteabono;
    private javax.swing.JLabel lblidcredito;
    public static javax.swing.JLabel lblnitcliente;
    public static javax.swing.JLabel lblnombrecliente;
    private javax.swing.JLabel lblnuevosaldopendiente;
    public static javax.swing.JLabel lblnumerofactura;
    private javax.swing.JLabel lblsaldopendiente;
    private javax.swing.JLabel lbltelefono;
    public static javax.swing.JLabel lbltelefonocliente;
    private javax.swing.JLabel lbltotalabonado;
    private javax.swing.JLabel lbltotalcredito;
    private javax.swing.JPanel recibo;
    // End of variables declaration//GEN-END:variables

    @Override
    public int print(Graphics graf, PageFormat pageformat, int index) throws PrinterException {
        
        if (index>0){
            return NO_SUCH_PAGE;
        }
        
        Graphics2D hub = (Graphics2D) graf;
        hub.translate(pageformat.getImageableX()+20, pageformat.getImageableY()+20);
        hub.scale(0.7, 0.7);
        recibo.printAll(graf);
        return PAGE_EXISTS;
    }
    
    
}
