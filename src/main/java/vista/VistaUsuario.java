package vista;

import controlador.AdministradorControler;
import controlador.UsuarioController;
import javax.swing.JOptionPane;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */
public class VistaUsuario extends javax.swing.JFrame {

    /**
     * Creates new form VistaRegistro
     */
    UsuarioController uc = new UsuarioController();
    AdministradorControler ac = new AdministradorControler();

    /**
     * Iniciliza la vista de usuario
     */
    public VistaUsuario() {
        initComponents();
        txtId.setEditable(false);
        UsuarioController.cargarTablaUsuarioTR(tblUsuarios);
        setTitle("Ventana de administración de Usuarios");
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombreAppUsuario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbTipoUsuario = new javax.swing.JComboBox<>();
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
        tblUsuarios = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 230, 122, -1));

        jLabel2.setText("Contraseña");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, -1, -1));

        jLabel3.setText("Tipo de usuario");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 280, -1, -1));

        jLabel4.setText("Nombres");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, -1, -1));

        jLabel5.setText("Email");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 210, -1, -1));
        jPanel1.add(txtNombreAppUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 122, -1));

        jLabel6.setText("Nombre de usuario");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, -1, -1));

        cmbTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opción", "Administrador", "Vecino" }));
        jPanel1.add(cmbTipoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 300, 122, -1));

        txtPass.setText("....");
        txtPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPassMouseClicked(evt);
            }
        });
        jPanel1.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 122, -1));
        jPanel1.add(txtNombresUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 122, -1));

        jLabel7.setText("Rep. Contraseña");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 210, -1, -1));
        jPanel1.add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 300, 122, -1));

        jLabel8.setText("DNI");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 280, -1, -1));
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 300, 122, -1));

        jLabel9.setText("Teléfono");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 280, -1, -1));

        txtRepPass.setText("....");
        txtRepPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRepPassMouseClicked(evt);
            }
        });
        jPanel1.add(txtRepPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 122, -1));

        btnCrear.setText("CREAR");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        jPanel1.add(btnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 460, -1, -1));

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 460, -1, -1));

        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 460, -1, -1));

        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 460, -1, -1));

        jLabel10.setText("Id");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 20, -1));
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 230, -1));

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblUsuarios);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1240, 180));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1265, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPassMouseClicked
        uc.isPasswordFieldClicked(txtPass);
    }//GEN-LAST:event_txtPassMouseClicked

    private void txtRepPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRepPassMouseClicked
        uc.isPasswordFieldClicked(txtRepPass);
    }//GEN-LAST:event_txtRepPassMouseClicked

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        String usuario = txtNombreAppUsuario.getText().trim();
        String clave = txtPass.getText().trim();
        String claveRep = txtRepPass.getText().trim();
        String dni = txtDni.getText().trim();
        String nombres = txtNombresUsuario.getText().trim();
        String tipoUsuario = cmbTipoUsuario.getSelectedItem().toString();
        String email = txtEmail.getText().trim();
        String telefono = txtTelefono.getText().trim();

        if (!usuario.isEmpty()) {
            if (!usuario.isEmpty()) {
                if (!dni.isEmpty()) {
                    if (!nombres.isEmpty()) {
                        if (!email.isEmpty()) {
                            if (!telefono.isEmpty()) {
                                if (uc.validarCorreoContrasena(email, clave)) {
                                    if (uc.contraseñasIguales(clave, claveRep)) {
                                        if (uc.verificarSeleccionUsuario(cmbTipoUsuario)) {
                                            uc.guardarUsuario(usuario, clave, dni, nombres, tipoUsuario, telefono, email);
                                            uc.limpiarCamposAdmin(txtId, txtNombreAppUsuario, txtPass,
                                                    txtRepPass, cmbTipoUsuario,
                                                    txtNombresUsuario, txtDni,
                                                    txtTelefono, txtEmail);
                                        }
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Ingrese el telefono");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Ingrese el e-mail");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Ingrese los nombres");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese el DNI");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Ingrese la contraseña");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ingrese el usuario");
        }
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        //Capturamos
        String idDoc = txtId.getText().trim();
        boolean esAdministrador = false;

        if (cmbTipoUsuario.getSelectedIndex() == 1) {
            esAdministrador = true;
        } else if (cmbTipoUsuario.getSelectedIndex() == 2) {
            esAdministrador = false;
        }

        //Eliminamos
        if (!idDoc.isEmpty()) {
            uc.eliminarUsuario(idDoc, esAdministrador);
            //Limpiamos
            uc.limpiarCamposAdmin(txtId, txtNombreAppUsuario, txtPass, txtRepPass,
                    cmbTipoUsuario, txtNombresUsuario, txtDni, txtTelefono, txtEmail);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un ID de un dato de la tabla");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        String idDoc = txtId.getText().trim();

        String usuario = txtNombreAppUsuario.getText().trim();
        String clave = txtPass.getText().trim();
        String claveRep = txtRepPass.getText().trim();
        String dni = txtDni.getText().trim();
        String nombres = txtNombresUsuario.getText().trim();
        String tipoUsuario = cmbTipoUsuario.getSelectedItem().toString();
        String email = txtEmail.getText().trim();
        String telefono = txtTelefono.getText().trim();

        if (!idDoc.isEmpty()) {
            if (!usuario.isEmpty()) {
                if (!usuario.isEmpty()) {
                    if (!dni.isEmpty()) {
                        if (!nombres.isEmpty()) {
                            if (!email.isEmpty()) {
                                if (!telefono.isEmpty()) {
                                    if (uc.validarCorreoContrasena(email, clave)) {
                                        if (uc.contraseñasIguales(clave, claveRep)) {
                                            if (uc.verificarSeleccionUsuario(cmbTipoUsuario)) {
                                                uc.actualizarUsuarioXXX(idDoc, usuario,
                                                        clave, dni, nombres,
                                                        tipoUsuario, telefono, email);
                                                uc.limpiarCamposAdmin(txtId, txtNombreAppUsuario,
                                                        txtPass, txtRepPass,
                                                        cmbTipoUsuario, txtNombresUsuario,
                                                        txtDni, txtTelefono, txtEmail);
                                            }
                                        }
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Ingrese el telefono");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Ingrese el e-mail");
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Ingrese los nombres");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Ingrese el DNI");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese la contraseña");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el usuario");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un ID de un dato de la tabla");
        }

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        uc.limpiarCamposAdmin(txtId, txtNombreAppUsuario, txtPass, txtRepPass,
                cmbTipoUsuario, txtNombresUsuario, txtDni, txtTelefono, txtEmail);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        int selection = tblUsuarios.getSelectedRow();
        txtId.setText(tblUsuarios.getValueAt(selection, 0) + "");
        txtNombreAppUsuario.setText(tblUsuarios.getValueAt(selection, 1) + "");
        txtPass.setText(tblUsuarios.getValueAt(selection, 2) + "");
        txtRepPass.setText(tblUsuarios.getValueAt(selection, 2) + "");
        txtDni.setText(tblUsuarios.getValueAt(selection, 3) + "");
        txtNombresUsuario.setText(tblUsuarios.getValueAt(selection, 4) + "");
        cmbTipoUsuario.setSelectedItem(tblUsuarios.getValueAt(selection, 5) + "");
        txtTelefono.setText(tblUsuarios.getValueAt(selection, 6) + "");
        txtEmail.setText(tblUsuarios.getValueAt(selection, 7) + "");
    }//GEN-LAST:event_tblUsuariosMouseClicked

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
            java.util.logging.Logger.getLogger(VistaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cmbTipoUsuario;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombreAppUsuario;
    private javax.swing.JTextField txtNombresUsuario;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JPasswordField txtRepPass;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}