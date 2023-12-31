package vista;

import controlador.AdministradorControler;
import javax.swing.JOptionPane;

/** Asignatura Proyecto de ingenieria de software 
 *
 * @author frami
 */
public class VistaAdministrador extends javax.swing.JFrame {

    /**
     * Creates new form VistaRegistro
     */
    AdministradorControler ac = new AdministradorControler();

    /**
     * inicializa la vista administrador
     */
    public VistaAdministrador() {
        initComponents();
        txtId.setEditable(false);

        setTitle("Ventana de administración de Administradores");
        AdministradorControler.cargarTablaAdministradorTR(tblAdministradores);
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
        txtEmail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombreAppUsuario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        txtNombresUsuario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtRepPass = new javax.swing.JPasswordField();
        btnCrear = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAdministradores = new javax.swing.JTable();
        txtAlta = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtBaja = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtModificado = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtIdUsuario = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 320, 122, -1));

        jLabel2.setText("Contraseña");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, -1, -1));

        jLabel4.setText("Nombres");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, -1));

        jLabel5.setText("Email");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 300, -1, -1));
        jPanel1.add(txtNombreAppUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 122, -1));

        jLabel6.setText("Nombre de usuario");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        txtPass.setText("....");
        txtPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPassMouseClicked(evt);
            }
        });
        jPanel1.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, 122, -1));
        jPanel1.add(txtNombresUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 122, -1));

        jLabel7.setText("Rep. Contraseña");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, -1, -1));
        jPanel1.add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 390, 122, -1));

        jLabel8.setText("DNI");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, -1, -1));
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 390, 122, -1));

        jLabel9.setText("Teléfono");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 370, -1, -1));

        txtRepPass.setText("....");
        txtRepPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRepPassMouseClicked(evt);
            }
        });
        jPanel1.add(txtRepPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 320, 122, -1));

        btnCrear.setText("CREAR");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        jPanel1.add(btnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 540, -1, -1));

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 540, -1, -1));

        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 540, -1, -1));

        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 540, -1, -1));

        jLabel10.setText("Id");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 20, -1));
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 220, -1));

        tblAdministradores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblAdministradores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAdministradoresMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblAdministradores);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1250, 250));
        jPanel1.add(txtAlta, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 320, 122, -1));

        jLabel11.setText("Alta");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 300, -1, -1));
        jPanel1.add(txtBaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 310, 122, -1));

        jLabel12.setText("Baja");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 290, -1, -1));
        jPanel1.add(txtModificado, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 390, 122, -1));

        jLabel13.setText("Modificado");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 370, -1, -1));
        jPanel1.add(txtIdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 220, -1));

        jLabel14.setText("Id Usuario");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 80, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPassMouseClicked
        ac.isPasswordFieldClicked(txtPass);
    }//GEN-LAST:event_txtPassMouseClicked

    private void txtRepPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRepPassMouseClicked
        ac.isPasswordFieldClicked(txtRepPass);
    }//GEN-LAST:event_txtRepPassMouseClicked

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        String usuario = txtNombreAppUsuario.getText().trim();
        String clave = txtPass.getText().trim();
        String claveRep = txtRepPass.getText().trim();
        String dni = txtDni.getText().trim();
        String nombres = txtNombresUsuario.getText().trim();
        String email = txtEmail.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String alta = txtAlta.getText().trim();
        String baja = txtBaja.getText().trim();
        String modificado = txtModificado.getText().trim();

        if (!usuario.isEmpty()) {
            if (!clave.isEmpty()) {
                if (!dni.isEmpty()) {
                    if (!nombres.isEmpty()) {
                        if (!email.isEmpty()) {
                            if (!telefono.isEmpty()) {
                                if (ac.validarCorreoContrasena(email, clave)) {
                                    if (ac.contraseñasIguales(clave, claveRep)) {
                                        ac.guardarAdministrador(usuario, clave, dni, nombres, "Administrador", telefono, email,
                                                alta, baja, modificado);

                                        ac.limpiarCampos(txtId, txtNombreAppUsuario, txtPass, txtRepPass,
                                                txtNombresUsuario, txtDni, txtTelefono, txtEmail,
                                                txtAlta, txtBaja, txtModificado);
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Ingrese el télefono");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Ingrese el E-mail");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ingrese los nombres");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese el DNI");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese la clave");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ingrese el nombre de usuario");
        }

    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        //Capturamos
        String idDoc = txtId.getText().trim();
        String idUsuario = txtIdUsuario.getText().trim();
        
        if (!idDoc.isEmpty()) {
            //Eliminamos
            ac.eliminarAdministrador(idDoc, idUsuario);

            //Limpiamos
            ac.limpiarCampos(txtId, txtNombreAppUsuario, txtPass, txtRepPass,
                    txtNombresUsuario, txtDni, txtTelefono, txtEmail,
                    txtAlta, txtBaja, txtModificado);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un ID de un dato de la tabla");
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        String idDoc = txtId.getText().trim();
        String idUusario = txtIdUsuario.getText().trim();
        String usuario = txtNombreAppUsuario.getText().trim();
        String clave = txtPass.getText().trim();
        String claveRep = txtRepPass.getText().trim();
        String dni = txtDni.getText().trim();
        String nombres = txtNombresUsuario.getText().trim();
        String email = txtEmail.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String alta = txtAlta.getText().trim();
        String baja = txtBaja.getText().trim();
        String modificado = txtModificado.getText().trim();

        if (!idDoc.isEmpty()) {
            if (!usuario.isEmpty()) {
                if (!clave.isEmpty()) {
                    if (!dni.isEmpty()) {
                        if (!nombres.isEmpty()) {
                            if (!email.isEmpty()) {
                                if (!telefono.isEmpty()) {
                                    if (ac.validarCorreoContrasena(email, clave)) {
                                        if (ac.contraseñasIguales(clave, claveRep)) {
                                            ac.actualizarAdministrador(idDoc, idUusario, usuario, clave, dni, nombres,
                                                    "Administrador", telefono, email,
                                                    alta, baja, modificado);

                                        }
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Ingrese el télefono");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Ingrese el E-mail");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Ingrese los nombres");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ingrese el DNI");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese la clave");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el nombre de usuario");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un ID de un dato de la tabla");
        }


    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        ac.limpiarCampos(txtId, txtNombreAppUsuario, txtPass, txtRepPass,
                txtNombresUsuario, txtDni, txtTelefono, txtEmail,
                txtAlta, txtBaja, txtModificado);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tblAdministradoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAdministradoresMouseClicked
        int selection = tblAdministradores.getSelectedRow();
        txtId.setText(tblAdministradores.getValueAt(selection, 0) + "");
        txtIdUsuario.setText(tblAdministradores.getValueAt(selection, 1) + "");
        txtNombreAppUsuario.setText(tblAdministradores.getValueAt(selection, 2) + "");
        txtPass.setText(tblAdministradores.getValueAt(selection, 3) + "");
        txtRepPass.setText(tblAdministradores.getValueAt(selection, 3) + "");
        txtDni.setText(tblAdministradores.getValueAt(selection, 4) + "");
        txtNombresUsuario.setText(tblAdministradores.getValueAt(selection, 5) + "");
        txtTelefono.setText(tblAdministradores.getValueAt(selection, 7) + "");
        txtEmail.setText(tblAdministradores.getValueAt(selection, 8) + "");
        txtAlta.setText(tblAdministradores.getValueAt(selection, 9) + "");
        txtBaja.setText(tblAdministradores.getValueAt(selection, 10) + "");
        txtModificado.setText(tblAdministradores.getValueAt(selection, 11) + "");

    }//GEN-LAST:event_tblAdministradoresMouseClicked

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
            java.util.logging.Logger.getLogger(VistaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new VistaAdministrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblAdministradores;
    private javax.swing.JTextField txtAlta;
    private javax.swing.JTextField txtBaja;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtModificado;
    private javax.swing.JTextField txtNombreAppUsuario;
    private javax.swing.JTextField txtNombresUsuario;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JPasswordField txtRepPass;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
