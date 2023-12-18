package vista;

import controlador.GastoControler;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/** Proyecto asignatura Proyectos de programación 
 *
 * @author frami
 */
public class VistaGasto extends javax.swing.JFrame {

    GastoControler gastoControler = new GastoControler();

    /**
     * Creates new form VistaGasto
     */
    public VistaGasto() {
        initComponents();
        txtId.setEditable(false);
        setTitle("Ventana de gestión de gastos.");
        GastoControler.cargarTablaGastosTR(tblGasto);
        try {
            GastoControler.cargarComboProveedor(cmbProveedor);
            GastoControler.cargarComboConcepto(cmbConceptoS);
            GastoControler.cargarComboServicio(cmbServicio);
            GastoControler.cargarComboLiquidacion(cmbLiquidacion);

        } catch (InterruptedException | ExecutionException | IOException ex) {
            Logger.getLogger(VistaGasto.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTitle("Ventana para la gestión de gastos");
        txtIdServicio.setEditable(false);
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jFechaRegistro = new com.toedter.calendar.JDateChooser();
        btnModificar = new javax.swing.JButton();
        btnCrear = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnBaja = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGasto = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtImporte = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNumeroComprobante = new javax.swing.JTextField();
        jFechaPago = new com.toedter.calendar.JDateChooser();
        cmbProveedor = new javax.swing.JComboBox<>();
        cmbConceptoS = new javax.swing.JComboBox<>();
        cmbServicio = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cmbLiquidacion = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtIdServicio = new javax.swing.JTextField();
        btnRecargarInmuebles = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnCrear.setText("CREAR");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnBaja.setText("ELIMINAR");
        btnBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBajaActionPerformed(evt);
            }
        });

        tblGasto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblGasto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGastoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblGasto);

        jLabel3.setText("Fecha gasto:");

        jLabel4.setText("Concepto:");

        jLabel6.setText("Proveedor:");

        jLabel7.setText("Fecha pago:");

        jLabel8.setText("Importe:");

        txtId.setEditable(false);
        txtId.setForeground(new java.awt.Color(242, 242, 242));
        txtId.setBorder(null);

        jLabel10.setText("Número comprobante:");

        cmbServicio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbServicioItemStateChanged(evt);
            }
        });

        jLabel5.setText("Servicio:");

        jLabel9.setText("Liquidación:");

        txtIdServicio.setEditable(false);
        txtIdServicio.setForeground(new java.awt.Color(242, 242, 242));
        txtIdServicio.setBorder(null);

        btnRecargarInmuebles.setText("Recargar");
        btnRecargarInmuebles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecargarInmueblesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6))
                .addGap(248, 248, 248)
                .addComponent(btnCrear)
                .addGap(18, 18, 18)
                .addComponent(btnBaja)
                .addGap(18, 18, 18)
                .addComponent(btnModificar)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRecargarInmuebles)
                        .addGap(260, 260, 260))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cmbProveedor, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbConceptoS, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbServicio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbLiquidacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtIdServicio))))
                        .addGap(422, 422, 422))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txtNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(245, 245, 245)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(jLabel10)
                        .addComponent(txtNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel3))
                    .addComponent(jFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(cmbConceptoS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbLiquidacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(btnRecargarInmuebles))
                .addGap(25, 25, 25)
                .addComponent(txtIdServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCrear)
                            .addComponent(btnBaja)
                            .addComponent(btnModificar)
                            .addComponent(btnLimpiar))
                        .addGap(16, 16, 16))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1288, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRecargarInmueblesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecargarInmueblesActionPerformed
        try {
            GastoControler.cargarComboServicio(cmbServicio);
        } catch (InterruptedException | ExecutionException | IOException ex) {
            Logger.getLogger(VistaGasto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRecargarInmueblesActionPerformed

    private void cmbServicioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbServicioItemStateChanged
        String usuario = cmbServicio.getSelectedItem().toString();
        String[] partesServicio = usuario.split("-");
        String nombreServicio = partesServicio[0].trim();
        String idServio = partesServicio[1].trim();
        txtIdServicio.setText(idServio);
    }//GEN-LAST:event_cmbServicioItemStateChanged

    private void tblGastoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGastoMouseClicked
        int selection = tblGasto.getSelectedRow();
        txtId.setText(tblGasto.getValueAt(selection, 0) + "");
        txtImporte.setText(tblGasto.getValueAt(selection, 1) + "");
        String fechaGastoString = (String) tblGasto.getValueAt(selection, 2);
        String fechaPagoString = (String) tblGasto.getValueAt(selection, 3);
        txtNumeroComprobante.setText(tblGasto.getValueAt(selection, 4) + "");
        cmbProveedor.setSelectedItem(tblGasto.getValueAt(selection, 5) + "");
        cmbConceptoS.setSelectedItem(tblGasto.getValueAt(selection, 6) + "");
        cmbServicio.setSelectedItem(tblGasto.getValueAt(selection, 7) + "");
        cmbLiquidacion.setSelectedItem(tblGasto.getValueAt(selection, 8) + "");

        // Obtener el valor de la celda de la tabla como String
        // Definir el formato de fecha que se está utilizando en la tabla
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Convertir el String a un objeto Date utilizando el formato definido
            Date fechaGasto = dateFormat.parse(fechaGastoString);
            Date fechaPago = dateFormat.parse(fechaPagoString);

            // Establecer la fecha en el JDateChooser
            jFechaRegistro.setDate(fechaGasto);
            jFechaPago.setDate(fechaPago);
        } catch (ParseException ex) {
            // Manejar el error en caso de que el String no se pueda convertir a Date
            System.out.println("Error al convertir fecha: " + ex.getMessage());
        }
    }//GEN-LAST:event_tblGastoMouseClicked

    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed
        String idDoc = txtId.getText().trim();

        if (!idDoc.isEmpty()) {
            gastoControler.eliminarGasto(idDoc);
            gastoControler.limpiarCampos(txtImporte, txtNumeroComprobante, jFechaRegistro,
                jFechaPago, cmbProveedor,
                cmbConceptoS, cmbLiquidacion);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un ID de un dato de la tabla");
        }
    }//GEN-LAST:event_btnBajaActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        gastoControler.limpiarCampos(txtImporte, txtNumeroComprobante, jFechaRegistro,
            jFechaPago, cmbProveedor,
            cmbConceptoS, cmbLiquidacion);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        /*
        Capturamos los valores
        */
        String inmuebleCmb = cmbServicio.getSelectedItem().toString();
        String[] partes = inmuebleCmb.split("-");
        String nombreServicio = partes[0].trim();
        String idServicioDoc = partes[1].trim();

        Date fechaRegistro = jFechaRegistro.getDate();
        Date fechaPago = jFechaPago.getDate();
        String numeroComprobante = txtNumeroComprobante.getText().trim();
        String proveedor = cmbProveedor.getSelectedItem().toString();
        String concepto = cmbConceptoS.getSelectedItem().toString();
        String servicio = cmbServicio.getSelectedItem().toString();
        String liquidacion = cmbLiquidacion.getSelectedItem().toString();
        /*
        Guardamos los valores utilzando el controlador
        */

        if (!txtImporte.getText().equals("")) {
            if (!numeroComprobante.isEmpty()) {
                if (fechaRegistro != null) {
                    if (fechaPago != null) {
                        double importe = Double.parseDouble(txtImporte.getText());
                        if (gastoControler.verificarSeleccionProveedor(cmbProveedor)
                            && gastoControler.verificarSeleccionConcepto(cmbConceptoS)
                            && gastoControler.verificarSeleccionServicio(cmbServicio)
                            && gastoControler.verificarSeleccionLiquidacion(cmbLiquidacion)) {

                            gastoControler.guardarGasto(importe,
                                fechaRegistro, fechaPago,
                                numeroComprobante, proveedor,
                                concepto, nombreServicio,
                                liquidacion, idServicioDoc);

                            gastoControler.limpiarCampos(txtImporte, txtNumeroComprobante, jFechaRegistro,
                                jFechaPago, cmbProveedor,
                                cmbConceptoS, cmbLiquidacion);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Ingrese la fecha de Pago");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese la fecha de Gasto");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el número de commprobante");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ingrese el importe");
        }
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        String idDoc = txtId.getText().trim();
        String inmuebleCmb = cmbServicio.getSelectedItem().toString();
        String[] partes = inmuebleCmb.split("-");
        String nombreServicio = partes[0].trim();
        String idServicioDoc = partes[1].trim();

        Date fechaRegistro = jFechaRegistro.getDate();
        Date fechaPago = jFechaPago.getDate();
        String numeroComprobante = txtNumeroComprobante.getText().trim();
        String proveedor = cmbProveedor.getSelectedItem().toString();
        String concepto = cmbConceptoS.getSelectedItem().toString();
        String servicio = cmbServicio.getSelectedItem().toString();
        String liquidacion = cmbLiquidacion.getSelectedItem().toString();

        if (!idDoc.isEmpty()) {
            if (!txtImporte.getText().equals("")) {
                if (!numeroComprobante.isEmpty()) {
                    if (fechaPago != null) {
                        double importe = Double.parseDouble(txtImporte.getText());
                        if (gastoControler.verificarSeleccionProveedor(cmbProveedor)
                            && gastoControler.verificarSeleccionConcepto(cmbConceptoS)
                            && gastoControler.verificarSeleccionServicio(cmbServicio)
                            && gastoControler.verificarSeleccionLiquidacion(cmbLiquidacion)) {

                            gastoControler.actualizarGasto(idDoc, importe,
                                fechaRegistro, fechaPago,
                                numeroComprobante, proveedor,
                                concepto, nombreServicio, liquidacion,
                                idServicioDoc);

                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Ingrese la fecha de Pago");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese el número de commprobante");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el importe");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un ID de un dato de la tabla");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    /** the command line arguments
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
            java.util.logging.Logger.getLogger(VistaGasto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaGasto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaGasto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaGasto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new VistaGasto().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRecargarInmuebles;
    private javax.swing.JComboBox<String> cmbConceptoS;
    private javax.swing.JComboBox<String> cmbLiquidacion;
    private javax.swing.JComboBox<String> cmbProveedor;
    private javax.swing.JComboBox<String> cmbServicio;
    private com.toedter.calendar.JDateChooser jFechaPago;
    private com.toedter.calendar.JDateChooser jFechaRegistro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblGasto;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdServicio;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtNumeroComprobante;
    // End of variables declaration//GEN-END:variables

}
