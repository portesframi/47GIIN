package controlador;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.database.annotations.Nullable;
import com.mycompany.viuproyecto.Conexion;
import java.util.concurrent.ExecutionException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Administrador;
import modelo.Usuario;
import modelo.Vecino;
import providers.AdministradorProvider;
import providers.UsuarioProvider;
import providers.VecinoProvider;

/** Asignatura Proyecto de ingenieria de software 
 *
 * @author frami
 */
public class RegistroControler extends ComponentesGeneralControler {

    private UsuarioProvider usuarioProvider;
    private AdministradorProvider administradorProvider;
    private VecinoProvider vecinoProvider;
    private Firestore db;

    /**
     * Realizamos la conexión
     */
    public RegistroControler() {
        db = Conexion.getConnection(); //Aquí se establece la conexión con Firestore
        usuarioProvider = new UsuarioProvider();
        administradorProvider = new AdministradorProvider();
        vecinoProvider = new VecinoProvider();
    }

    /**Fijamos al modelo usuario los valores que recibimos como parametros
     * 
     * @param usuario user
     * @param contraseña contraseña
     * @param dni dni
     * @param nombre nombre user
     * @param tipoUsuario tipo user
     * @param telefono tel user
     * @param email email user
     */
    public void guardarUsuario(
            String usuario,
            String contraseña,
            String dni,
            String nombre,
            String tipoUsuario,
            String telefono,
            String email) {

        Usuario u = new Usuario();
        u.setUsuario(usuario);
        u.setContraseña(contraseña);
        u.setDni(dni);
        u.setNombre(nombre);
        u.setTipo(tipoUsuario);
        u.setTelefono(telefono);
        u.setEmail(email);

        Administrador a = new Administrador();
        a.setUsuario(usuario);
        a.setContraseña(contraseña);
        a.setDni(dni);
        a.setNombre(nombre);
        a.setTipo(tipoUsuario);
        a.setTelefono(telefono);
        a.setEmail(email);

        Vecino v = new Vecino();
        v.setUsuario(usuario);
        v.setContraseña(contraseña);
        v.setDni(dni);
        v.setNombre(nombre);
        v.setTipo(tipoUsuario);
        v.setTelefono(telefono);
        v.setEmail(email);

        if (tipoUsuario.equals("Administrador")) {
            try {
                usuarioProvider.guardarModeloUsuario(u);//Creamos el ID en el Usuario
                administradorProvider.guardarModeloAdministrador(a);//Obtenemos el ID del Usuario y Guardamos en el Administrador
                JOptionPane.showMessageDialog(null, "Usuario guardado exitosamente");
                //System.out.println("Gasto guardado exitosamente");
            } catch (InterruptedException | ExecutionException ex) {
                //System.out.println("Error al guardar el gasto: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "Error al guardar el usuario: " + ex.getMessage());
            }
        } else if (tipoUsuario.equals("Vecino")) {
            try {
                usuarioProvider.guardarModeloUsuario(u);//Creamos el ID en el Usuario
                vecinoProvider.guardarModeloVecino(v);//Obtenemos el ID //Obtenemos el ID del Usuario y Guardamos en el Vecino
                JOptionPane.showMessageDialog(null, "Usuario guardado exitosamente");
                //System.out.println("Gasto guardado exitosamente");
            } catch (InterruptedException | ExecutionException ex) {
                //System.out.println("Error al guardar el gasto: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "Error al guardar el usuario: " + ex.getMessage());
            }
        }
    }

    /**Este metodo carga datos en tiempo real todo es gracias a addSnapshotListener 
     * que escucha los cambios en la base de datos
     * 
     * @param tabla tbl
     */
    public static void cargarTablaProveedorTR(JTable tabla) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("Usuario");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Telefono");
        model.addColumn("E-mail");
        model.addColumn("Tipo de usuario");
        model.addColumn("Contraseña");

        gastos.addSnapshotListener((@Nullable QuerySnapshot querySnapshot, @Nullable FirestoreException error) -> {
            if (error != null) {
                System.err.println("Error al escuchar cambios: " + error);
                return;
            }

            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {

                /*
            En el caso de las fechas se fija por defecto un valor tal cual es 0000-00-00
            en caso de venir vacio.
                 */
                model.addRow(new Object[]{
                    document.getId(),
                    /*
                En el caso de las cadenas de texto se fija una cadena sin ningún caracter
                     */
                    document.getString("nombre") != null ? document.getString("nombre") : "",
                    document.getString("telefono") != null ? document.getString("telefono") : "",
                    document.getString("email") != null ? document.getString("email") : "",
                    document.getString("tipoUsuario") != null ? document.getString("tipoUsuario") : "",
                    document.getString("clave") != null ? document.getString("clave") : "",});
            }

            tabla.setModel(model);
        });
    }

    /** Actualiza el usuario en la Bd
     *
     * @param idDoc id
     * @param usuario user
     * @param contraseña clave
     * @param dni dni user
     * @param nombre nombre user
     * @param tipoUsuario tipo user
     * @param telefono tel user
     * @param email email user
     */
    public void actualizarUsuario(
            String idDoc,
            String usuario,
            String contraseña,
            String dni,
            String nombre,
            String tipoUsuario,
            String telefono,
            String email) {

        Usuario u = new Usuario();
        u.setUsuario(usuario);
        u.setContraseña(contraseña);
        u.setDni(dni);
        u.setNombre(nombre);
        u.setTipo(tipoUsuario);
        u.setTelefono(telefono);
        u.setEmail(email);

        try {
            usuarioProvider.actualizarModeloUsuario(idDoc, u);
            JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente");
            //System.out.println("Usuario actualizado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el usuario: " + ex.getMessage());
            //System.out.println("Error al actualizar el usuario: " + ex.getMessage());
        }
    }

    /** Eliminar usuario
     *
     * @param idDoc id
     */
    public void eliminarUsuario(String idDoc) {
        try {
            usuarioProvider.eliminarModeloUsuario(idDoc);
            JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente");
            //System.out.println("Gasto eliminado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el usuario: " + ex.getMessage());
            // System.out.println("Error al eliminar el gasto: " + ex.getMessage());
        }
    }

    /** Selcciona usurio
     *
     * @param comboBox componenete
     * @return valor
     */
    public boolean verificarSeleccionUsuario(JComboBox comboBox) {
        if (!verificarSeleccion(comboBox)) {
            JOptionPane.showMessageDialog(null, "Seleccione una opción en Tipo de Usuario");
            return false;
        }
        return true;
    }

    /** limpia los campos de los atributos en la vista 
     *
     * @param txtUsuario user
     * @param txtContraseña clave user
     * @param txtContraseñaRepetida repetir clave user
     * @param txtDni dni user
     * @param txtNombresUsuario nombre user
     * @param cmbTipoUser tipo user
     * @param txtEmail email user
     * @param txtTelefono tel user
     */
    public void limpiarCampos(
            JTextField txtUsuario,
            JTextField txtContraseña,
            JTextField txtContraseñaRepetida,
            JTextField txtDni,
            JTextField txtNombresUsuario,
            JComboBox cmbTipoUser,
            JTextField txtEmail,
            JTextField txtTelefono
    ) {

        txtUsuario.setText("");
        txtContraseña.setText("");
        txtContraseñaRepetida.setText("");
        txtDni.setText("");
        txtNombresUsuario.setText("");
        cmbTipoUser.setSelectedIndex(0);
        txtEmail.setText("");
        txtTelefono.setText("");

    }
}
