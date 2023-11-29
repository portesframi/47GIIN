package vista;

import controlador.InmuebleControler;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */
public class VistaInmueble extends javax.swing.JFrame {

    /**
     * Creates new form VistaInmueble
     */
    InmuebleControler ic = new InmuebleControler();

    /**
     * Inicializa la vista de inmueble
     */
    public VistaInmueble() {
        initComponents();
        InmuebleControler.cargarTablaInmuebleTR(tblInmueble);

        try {
            InmuebleControler.cargarComboVecino(cmbVecino);
        } catch (InterruptedException | ExecutionException | IOException ex) {
            Logger.getLogger(VistaInmueble.class.getName()).log(Level.SEVERE, null, ex);
        }

        txtPiso.setEnabled(true);
        txtPuerta.setEnabled(true);
        txtParcela.setEnabled(false);
        txtId.setEditable(false);
        setTitle("Ventana para la gestión de Inmuebles");
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

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtMetros = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnCrear = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblInmueble = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtDomicilio = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbVecino = new javax.swing.JComboBox<>();
        txtPiso = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPuerta = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtParcela = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cheIsInmuebleCasa = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        txtIdVecino = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Metros:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 270, -1, -1));
        jPanel1.add(txtMetros, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 270, 100, -1));
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, 170, -1));

        jLabel5.setText("ID:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, -1, -1));

        btnCrear.setText("CREAR");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        jPanel1.add(btnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 510, -1, -1));

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 510, -1, -1));

        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 510, -1, -1));

        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 510, -1, -1));

        tblInmueble.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblInmueble.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInmuebleMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblInmueble);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 780, 170));

        jLabel1.setText("Domicilio:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, -1, -1));
        jPanel1.add(txtDomicilio, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 100, -1));
        jPanel1.add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 100, -1));

        jLabel2.setText("Número:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        jLabel3.setText("Vecino:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 230, -1, -1));

        cmbVecino.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbVecinoItemStateChanged(evt);
            }
        });
        cmbVecino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbVecinoActionPerformed(evt);
            }
        });
        jPanel1.add(cmbVecino, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 230, 170, -1));
        jPanel1.add(txtPiso, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 350, 100, -1));

        jLabel7.setText("Piso:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 350, -1, -1));
        jPanel1.add(txtPuerta, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 310, 100, -1));

        jLabel6.setText("Puerta:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 310, -1, -1));
        jPanel1.add(txtParcela, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, 100, -1));

        jLabel8.setText("Parcela:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, -1, -1));

        cheIsInmuebleCasa.setText("Es inmueble casa");
        cheIsInmuebleCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cheIsInmuebleCasaActionPerformed(evt);
            }
        });
        jPanel1.add(cheIsInmuebleCasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, -1, -1));

        jLabel9.setText("ID Vecino:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 410, 80, -1));
        jPanel1.add(txtIdVecino, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 410, 170, -1));

        jButton1.setText("Recargar Vecinos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 190, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        //Capturamos
        String vecinoCmb = cmbVecino.getSelectedItem().toString();
        String[] partesUsuario = vecinoCmb.split("-");
        String nombreVecino = partesUsuario[0].trim();
        String idVecino = partesUsuario[1].trim();

        String domicilio = txtDomicilio.getText().trim();

        String piso = txtPiso.getText().trim();
        String puerta = txtPuerta.getText().trim();
        String parcela = txtParcela.getText().trim();
        boolean isInmuebleCasa = false;

        if (cheIsInmuebleCasa.isSelected()) {
            isInmuebleCasa = true;
            if (!parcela.isEmpty()) {

                if (!domicilio.isEmpty()) {
                    if (!txtNumero.getText().isEmpty()) {
                        if (!txtMetros.getText().isEmpty()) {
                            long numero = Long.parseLong(txtNumero.getText().trim());
                            float metros = Float.parseFloat(txtMetros.getText().trim());

                            if (ic.verificarSeleccionVecino(cmbVecino)) {
                                try {
                                    /*
                                    CONSULTA TODOS LOS DATOS DE LA COLECCIÓN SuscripcionServicio
                                    POR EL ID DEL DOCUMENTO SELECCIONADO EN EL COMBOBOX Suscripcion Servicio.
                                     */
                                    CompletableFuture<Map<String, Object>> datosSuscripcionFuture
                                            = ic.consultarDatosSuscripcionInmueblePorId(idVecino);

                                    Map<String, Object> datosInmueble = datosSuscripcionFuture.get();

                                    // Obtener los valores de los campos del documento
                                    String idUsuario = (String) datosInmueble.get("idUsuario");

                                //Guardamos
                                ic.guardarInmueble(domicilio, numero, nombreVecino, idUsuario,
                                        metros, piso, puerta, parcela, isInmuebleCasa);
                                //Limpiamos
                                ic.limpiarCampos(txtId, txtDomicilio,
                                        txtNumero, txtIdVecino,
                                        cmbVecino, txtMetros,
                                        txtPiso, txtPuerta,
                                        txtParcela, cheIsInmuebleCasa);
                                //SI EL ID ESTA VACIO

                                } catch (InterruptedException | ExecutionException ex) {
                                    Logger.getLogger(VistaInmueble.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Ingrese los metros");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Ingrese el número");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese el domicilio");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Ingrese la parcela");
            }

        } else {
            isInmuebleCasa = false;

            if (!piso.isEmpty()) {
                if (!puerta.isEmpty()) {

                    if (!domicilio.isEmpty()) {
                        if (!txtNumero.getText().isEmpty()) {
                            if (!txtMetros.getText().isEmpty()) {
                                long numero = Long.parseLong(txtNumero.getText().trim());
                                float metros = Float.parseFloat(txtMetros.getText().trim());

                                if (ic.verificarSeleccionVecino(cmbVecino)) {
                                    try {
                                        /*
                                    CONSULTA TODOS LOS DATOS DE LA COLECCIÓN SuscripcionServicio
                                    POR EL ID DEL DOCUMENTO SELECCIONADO EN EL COMBOBOX Suscripcion Servicio.
                                         */
                                        CompletableFuture<Map<String, Object>> datosSuscripcionFuture
                                                = ic.consultarDatosSuscripcionInmueblePorId(idVecino);

                                        Map<String, Object> datosInmueble = datosSuscripcionFuture.get();

                                        // Obtener los valores de los campos del documento
                                        String idUsuario = (String) datosInmueble.get("idUsuario");

                                    //Guardamos
                                    ic.guardarInmueble(domicilio, numero, nombreVecino, idUsuario,
                                            metros, piso, puerta, parcela, isInmuebleCasa);

                                    //Limpiamos  
                                    ic.limpiarCampos(txtId, txtDomicilio,
                                            txtNumero, txtIdVecino,
                                            cmbVecino, txtMetros,
                                            txtPiso, txtPuerta,
                                            txtParcela, cheIsInmuebleCasa);

                                    } catch (InterruptedException | ExecutionException ex) {
                                        Logger.getLogger(VistaInmueble.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "Ingrese los metros");
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Ingrese el número");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Ingrese el domicilio");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese la puerta");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el piso");
            }
        }

    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        //Capturamos
        String idDoc = txtId.getText().trim();
        boolean isInmuebleCasa;

        if (cheIsInmuebleCasa.isSelected()) {
            isInmuebleCasa = true;
        } else {
            isInmuebleCasa = false;
        }

        if (!idDoc.isEmpty()) {
            //Eliminamos
            ic.eliminarInmueble(idDoc, isInmuebleCasa);
            //Limpiamos
            ic.limpiarCampos(txtId, txtDomicilio,
                    txtNumero, txtIdVecino,
                    cmbVecino, txtMetros,
                    txtPiso, txtPuerta,
                    txtParcela, cheIsInmuebleCasa);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un ID de un dato de la tabla");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        String vecinoCmb = cmbVecino.getSelectedItem().toString();
        String[] partesUsuario = vecinoCmb.split("-");
        String nombreVecino = partesUsuario[0].trim();
        String idVecino = partesUsuario[1].trim();
//Capturamos
        String idDoc = txtId.getText().trim();
        String domicilio = txtDomicilio.getText().trim();
        String vecino = cmbVecino.getSelectedItem().toString();
        String piso = txtPiso.getText().trim();
        String puerta = txtPuerta.getText().trim();
        String parcela = txtParcela.getText().trim();
        boolean isInmuebleCasa = false;

        if (!idDoc.isEmpty()) {

            if (cheIsInmuebleCasa.isSelected()) {
                isInmuebleCasa = true;
                if (!parcela.isEmpty()) {

                    if (!domicilio.isEmpty()) {
                        if (!txtNumero.getText().isEmpty()) {
                            if (!txtMetros.getText().isEmpty()) {
                                long numero = Long.parseLong(txtNumero.getText().trim());
                                float metros = Float.parseFloat(txtMetros.getText().trim());

                                if (ic.verificarSeleccionVecino(cmbVecino)) {
                                    try {
                                        /*
                                    CONSULTA TODOS LOS DATOS DE LA COLECCIÓN SuscripcionServicio
                                    POR EL ID DEL DOCUMENTO SELECCIONADO EN EL COMBOBOX Suscripcion Servicio.
                                         */
                                        CompletableFuture<Map<String, Object>> datosSuscripcionFuture
                                                = ic.consultarDatosSuscripcionInmueblePorId(idVecino);

                                        Map<String, Object> datosInmueble = datosSuscripcionFuture.get();

                                        // Obtener los valores de los campos del documento
                                        String idUserConsultado = (String) datosInmueble.get("idUsuario");

                                    //Guardamos
                                    ic.actualizarInmueble(idDoc, domicilio, numero,
                                            nombreVecino, idUserConsultado,
                                            metros, piso, puerta, parcela, isInmuebleCasa);
                                    //Limpiamos

                                    //SI EL ID ESTA VACIO
                                    } catch (InterruptedException | ExecutionException ex) {
                                        Logger.getLogger(VistaInmueble.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "Ingrese los metros");
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Ingrese el número");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Ingrese el domicilio");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese la parcela");
                }

            } else {
                isInmuebleCasa = false;

                if (!piso.isEmpty()) {
                    if (!puerta.isEmpty()) {

                        if (!domicilio.isEmpty()) {
                            if (!txtNumero.getText().isEmpty()) {
                                if (!txtMetros.getText().isEmpty()) {
                                    long numero = Long.parseLong(txtNumero.getText().trim());
                                    float metros = Float.parseFloat(txtMetros.getText().trim());

                                    if (ic.verificarSeleccionVecino(cmbVecino)) {
                                        try {
                                            /*
                                    CONSULTA TODOS LOS DATOS DE LA COLECCIÓN SuscripcionServicio
                                    POR EL ID DEL DOCUMENTO SELECCIONADO EN EL COMBOBOX Suscripcion Servicio.
                                             */
                                            CompletableFuture<Map<String, Object>> datosSuscripcionFuture
                                                    = ic.consultarDatosSuscripcionInmueblePorId(idVecino);

                                            Map<String, Object> datosInmueble = datosSuscripcionFuture.get();

                                            // Obtener los valores de los campos del documento
                                            String idUserConsultado = (String) datosInmueble.get("idUsuario");

                                        //Guardamos
                                        ic.actualizarInmueble(idDoc, domicilio, numero,
                                                nombreVecino, idUserConsultado,
                                                metros, piso, puerta, parcela, isInmuebleCasa);
                                            //Limpiamos

//                                        SI EL ID ESTA VACIO
                                        } catch (InterruptedException | ExecutionException ex) {
                                            Logger.getLogger(VistaInmueble.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }

                                } else {
                                    JOptionPane.showMessageDialog(null, "Ingrese los metros");
                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "Ingrese el número");
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Ingrese el domicilio");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Ingrese la puerta");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese el piso");
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un ID de un dato de la tabla");
        }

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        ic.limpiarCampos(txtId, txtDomicilio,
                txtNumero, txtIdVecino,
                cmbVecino, txtMetros,
                txtPiso, txtPuerta,
                txtParcela, cheIsInmuebleCasa);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tblInmuebleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInmuebleMouseClicked
        int selection = tblInmueble.getSelectedRow();
        txtId.setText(tblInmueble.getValueAt(selection, 0) + "");
        txtDomicilio.setText(tblInmueble.getValueAt(selection, 1) + "");
        txtNumero.setText(tblInmueble.getValueAt(selection, 2) + "");
        //cmbVecino.setSelectedItem(tblInmueble.getValueAt(selection, 3) + "");
        txtIdVecino.setText(tblInmueble.getValueAt(selection, 4) + "");
        txtMetros.setText(tblInmueble.getValueAt(selection, 5) + "");

        String parcela = String.valueOf(tblInmueble.getValueAt(selection, 8));

        if (parcela.equals("")) {
            txtPiso.setEnabled(true);
            txtPuerta.setEnabled(true);
            txtParcela.setEnabled(false);
            txtPiso.setText(tblInmueble.getValueAt(selection, 6) + "");
            txtPuerta.setText(tblInmueble.getValueAt(selection, 7) + "");
            txtParcela.setText("");
            cheIsInmuebleCasa.setSelected(false);
        } else {
            txtPiso.setEnabled(false);
            txtPuerta.setEnabled(false);
            txtParcela.setEnabled(true);
            txtPiso.setText("");
            txtPuerta.setText("");
            txtParcela.setText(tblInmueble.getValueAt(selection, 8) + "");
            cheIsInmuebleCasa.setSelected(true);
        }
    }//GEN-LAST:event_tblInmuebleMouseClicked

    private void cmbVecinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbVecinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVecinoActionPerformed

    private void cheIsInmuebleCasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cheIsInmuebleCasaActionPerformed
        if (cheIsInmuebleCasa.isSelected()) {
            txtPiso.setEnabled(false);
            txtPiso.setText("");
            txtPuerta.setEnabled(false);
            txtPuerta.setText("");
            txtParcela.setEnabled(true);

        } else {
            txtPiso.setEnabled(true);
            txtPuerta.setEnabled(true);
            txtParcela.setEnabled(false);
            txtParcela.setText("");
        }
    }//GEN-LAST:event_cheIsInmuebleCasaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            InmuebleControler.cargarComboVecino(cmbVecino);
        } catch (InterruptedException | ExecutionException | IOException ex) {
            Logger.getLogger(VistaInmueble.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbVecinoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbVecinoItemStateChanged
        if (cmbVecino.getSelectedIndex() != 0) {
            String usuario = cmbVecino.getSelectedItem().toString();
            String[] partesUsuario = usuario.split("-");
            String nombreVecino = partesUsuario[0].trim();
            String idVecino = partesUsuario[1].trim();
            txtIdVecino.setText(idVecino);
        }
    }//GEN-LAST:event_cmbVecinoItemStateChanged

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
            java.util.logging.Logger.getLogger(VistaInmueble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaInmueble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaInmueble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaInmueble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaInmueble().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JCheckBox cheIsInmuebleCasa;
    private javax.swing.JComboBox<String> cmbVecino;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblInmueble;
    private javax.swing.JTextField txtDomicilio;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdVecino;
    private javax.swing.JTextField txtMetros;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtParcela;
    private javax.swing.JTextField txtPiso;
    private javax.swing.JTextField txtPuerta;
    // End of variables declaration//GEN-END:variables
}
