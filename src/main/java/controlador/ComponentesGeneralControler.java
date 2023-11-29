package controlador;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */

/** 
 * Clase que cambia el estado de una variable de tipo boolean al realizar un clic sobre el JPasswordField
 */
public class ComponentesGeneralControler {

    boolean clic = false;


    /** Metodo para controlar los eventos en un JPasswordField
     * 
     * @param passwordField variable de tipo boolean 
     */

    public void isPasswordFieldClicked(JPasswordField passwordField) {
        /*
        Esta logica lo que hace es cambiar el estado de una variable 
        de tipo boolean al realizar un clic sobre el JPasswordField
        de forma que quite el texto puesto y coloque la variable como
        verdadera para que no se vuelva a quitar el nuevo texto colocado.
         */
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                clic = true;
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        if (clic) {

        } else {
            passwordField.setText("");
        }
    }

    /** Atributos para comparar contraseñas en la creacion
     *
     * @param contraseña contraseña 1   
     * @param contraseñaRep repeticion de contraseña
     * @return devuelve falso
     */
    public boolean contraseñasIguales(String contraseña, String contraseñaRep) {
        // Validar contraseñas que sean iguales
        if (!contraseña.equals(contraseñaRep)) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
            return false;
        }

        return true;
    }

    /** Atributos de validacion de correo
     *
     * @param correo email de acceso
     * @param contrasena contraseña de acceso
     * @return devuelve falso
     */
    public boolean validarCorreoContrasena(String correo, String contrasena) {

        // Validar correo electrónico
        if (!correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            JOptionPane.showMessageDialog(null, "Ingrese un correo valido");
            return false;
        }

        // Validar contraseña
        if (contrasena.length() < 4 || contrasena.length() > 8) {
            JOptionPane.showMessageDialog(null, "Ingrese una clave entre 4 y 8 dígitos");
            return false;
        }

        return true;
    }

    /** Selecciona opciones
     *
     * @param comboBox variable control para seleccionar en la lista 
     * @return devuelve falso
     */
    public boolean verificarSeleccion(JComboBox comboBox) {
        if (comboBox.getSelectedIndex() == 0) {
            //JOptionPane.showMessageDialog(null, "Selecciona una opción");
            //comboBox.setSelectedIndex(1);
            return false;
        }
        return true;
    }

    /** Añade componentes
     *
     * @param frame panel 
     */
    public void confirmarSalida(JFrame frame) {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
//                System.out.println("Se hizo clic en el botón de salida");
                UIManager.put("OptionPane.yesButtonText", "Sí");
                int opcion = JOptionPane.showConfirmDialog(frame, "¿Está seguro que desea salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }
}
