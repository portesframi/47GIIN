package controlador;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.database.annotations.Nullable;
import com.mycompany.viuproyecto.Conexion;
import static controlador.UsuarioController.formatDate;
import java.util.concurrent.ExecutionException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Administrador;
import modelo.Usuario;
import providers.AdministradorProvider;
import providers.UsuarioProvider;

/** Asignatura Proyecto de ingenieria de software 
 *
 * @author frami
 */
public class AdministradorControler extends ComponentesGeneralControler{

    private AdministradorProvider administradorProvider;
    private UsuarioProvider usuarioProvider;
    private Firestore db;

    /**
     * Realizamos la conexión
     */

    public AdministradorControler() {
        db = Conexion.getConnection(); //Aquí se establece la conexión con Firestore
        administradorProvider = new AdministradorProvider();
        usuarioProvider = new UsuarioProvider();
    }


    /** Fijamos al modelo Administrador los valores que recibimos como parametros
     *
     * @param usuario usuario administrador
     * @param contraseña contraseña de administrador
     * @param dni dni de administrador
     * @param nombre nombre de administrador
     * @param tipoUsuario tipo de usuario 
     * @param telefono telefono de administrador
     * @param email email de administrador
     * @param alta registro de alta de administrador
     * @param baja registro de baja de administrador
     * @param modificado registro de modificado de administrador
     */

    
    public void guardarAdministrador(
            String usuario,
            String contraseña,
            String dni,
            String nombre,
            String tipoUsuario,
            String telefono,
            String email,
            String alta,
            String baja,
            String modificado) {

        Administrador a = new Administrador();
        a.setUsuario(usuario);
        a.setContraseña(contraseña);
        a.setDni(dni);
        a.setNombre(nombre);
        a.setTipo(tipoUsuario);
        a.setTelefono(telefono);
        a.setEmail(email);
        //
        a.setAlta(alta);
        a.setBaja(baja);
        a.setModificado(modificado);
        
        /////
        Usuario u = new Usuario();
        u.setUsuario(usuario);
        u.setContraseña(contraseña);
        u.setDni(dni);
        u.setNombre(nombre);
        u.setTipo(tipoUsuario);
        u.setTelefono(telefono);
        u.setEmail(email);

        try {
            
            usuarioProvider.guardarModeloUsuario(u);//Guardamos el nuevo Usuario con el id para enviarlo 
            administradorProvider.guardarModeloAdministrador(a);//Recibimos ese id y lo guardamos en el Administrador
            JOptionPane.showMessageDialog(null, "Administrador guardado exitosamente");
            //System.out.println("Administrador guardado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            //System.out.println("Error al guardar el administrador: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al guardar el administrador: " + ex.getMessage());
        }
    }

    /** Este metodo carga datos en tiempo real todo es gracias a addSnapshotListener 
     * que escucha los cambios en la base de datos
     * 
     * @param tabla carga datos
     */

    public static void cargarTablaAdministradorTR(JTable tabla) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("Administrador");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("ID USUARIO");
        model.addColumn("usuario");
        model.addColumn("Contraseña");
        model.addColumn("DNI");
        model.addColumn("Nombre");
        model.addColumn("Tipo");
        model.addColumn("Teléfono");
        model.addColumn("E-mail");
        model.addColumn("Alta");
        model.addColumn("Baja");
        model.addColumn("Modificado");
        model.addColumn("Fecha de alta");

        gastos.addSnapshotListener((@Nullable QuerySnapshot querySnapshot, @Nullable FirestoreException error) -> {
            if (error != null) {
                System.err.println("Error al escuchar cambios: " + error);
                return;
            }

            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {

                long timeAgo = document.getLong("fechaAlta") != null ? document.getLong("fechaAlta") : 0;
                String fechaAlta = String.valueOf(formatDate(timeAgo));
                /*
            En el caso de las fechas se fija por defecto un valor tal cual es 0000-00-00
            en caso de venir vacio.
                 */
                model.addRow(new Object[]{
                    document.getId(),
                    /*
                En el caso de las cadenas de texto se fija una cadena sin ningún caracter
                     */
                    document.getString("idUsuario") != null ? document.getString("idUsuario") : "",
                    document.getString("usuario") != null ? document.getString("usuario") : "",
                    document.getString("clave") != null ? document.getString("clave") : "",
                    document.getString("dni") != null ? document.getString("dni") : "",
                    document.getString("nombre") != null ? document.getString("nombre") : "",
                    document.getString("tipo") != null ? document.getString("tipo") : "",
                    document.getString("telefono") != null ? document.getString("telefono") : "",
                    document.getString("email") != null ? document.getString("email") : "",
                    document.getString("alta") != null ? document.getString("alta") : "",
                    document.getString("baja") != null ? document.getString("baja") : "",
                    document.getString("modificado") != null ? document.getString("modificado") : "",
                    fechaAlta
                });

            }

            tabla.setModel(model);
        });
    }

    /** Actualizacion de administrador
     *
     * @param idDoc identificador de administrador
     * @param idDocUsuario identficado de usuario
     * @param usuario define el usuario
     * @param contraseña contraseña de usaurio
     * @param dni dni de usaurio
     * @param nombre nombre de usaurio
     * @param tipoUsuario tipo de usaurio
     * @param telefono telefono de usaurio
     * @param email emil de usaurio
     * @param alta alta de usaurio
     * @param baja baja de usuario
     * @param modificado modificado de usuario
     */
    public void actualizarAdministrador(
            String idDoc,
            String idDocUsuario,
            String usuario,
            String contraseña,
            String dni,
            String nombre,
            String tipoUsuario,
            String telefono,
            String email,
            String alta,
            String baja,
            String modificado) {

        Administrador a = new Administrador();
        a.setUsuario(usuario);
        a.setContraseña(contraseña);
        a.setDni(dni);
        a.setNombre(nombre);
        a.setTipo(tipoUsuario);
        a.setTelefono(telefono);
        a.setEmail(email);
        //
        a.setAlta(alta);
        a.setBaja(baja);
        a.setModificado(modificado);
        
         /////
        Usuario u = new Usuario();
        u.setUsuario(usuario);
        u.setContraseña(contraseña);
        u.setDni(dni);
        u.setNombre(nombre);
        u.setTipo(tipoUsuario);
        u.setTelefono(telefono);
        u.setEmail(email);

        try {
            administradorProvider.actualizarModeloAdministrador(idDoc, a);
            usuarioProvider.actualizarModeloUsuario(idDocUsuario, u);
            JOptionPane.showMessageDialog(null, "Administrador actualizado exitosamente");
            //System.out.println("Administrador actualizado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el administrador: " + ex.getMessage());
            //System.out.println("Error al actualizar el administrador: " + ex.getMessage());
        }
    }

    /** Eliminar administrador
     *
     * @param idDoc busca id administrador
     * @param idDocUsuario busca id usuario
     */
    public void eliminarAdministrador(String idDoc, String idDocUsuario) {
        try {
            administradorProvider.eliminarModeloAdministrador(idDoc);
            usuarioProvider.eliminarModeloUsuario(idDocUsuario);
            //usuarioProvider.eliminarModeloUsuario(idDoc);
            JOptionPane.showMessageDialog(null, "Administrador eliminado exitosamente");
            //System.out.println("Administrador eliminado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el administrador: " + ex.getMessage());
            // System.out.println("Error al eliminar el administrador: " + ex.getMessage());
        }
    }
    
    /** Elimina administrador a traves de su id
     *
     * @param idDocUsuario id usuario
     */
    public void eliminarAdministradorByIdUser(String idDocUsuario) {
        try {
            administradorProvider.eliminarModeloAdministrador(idDocUsuario);
            //usuarioProvider.eliminarModeloUsuario(idDoc);
            JOptionPane.showMessageDialog(null, "Administrador eliminado exitosamente");
            //System.out.println("Administrador eliminado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el administrador: " + ex.getMessage());
            // System.out.println("Error al eliminar el administrador: " + ex.getMessage());
        }
    }
    
    /** Seleccion de tipo de usuario
     *
     * @param comboBox componente 
     * @return devuelve falso
     */
    public boolean verificarSeleccionPeriodoTipoUsuario(JComboBox comboBox) {
        if (!verificarSeleccion(comboBox)) {
            JOptionPane.showMessageDialog(null, "Seleccione una opción en Tipo de Usuario");
            return false;
        }
        return true;
    }
    
    /** Limpia los campos de la vista que tengas datos
     *
     * @param txtId id usuario
     * @param txtUsuario usuario
     * @param jPasswordContraseña contraseña usuario
     * @param jPasswordContraseñaRep repeticion de contraseña usuario
     * @param txtNombre nombre usuario
     * @param txtDni dni usuario
     * @param txtTelefono telfono usuario
     * @param txtAlta alta usuario
     * @param txtBaja baja usuario
     * @param txtModificado modificcion de usuario 
     * @param txtEmail email usuario
     */
    public void limpiarCampos(
            JTextField txtId,
            JTextField txtUsuario,
            JPasswordField jPasswordContraseña,
            JPasswordField jPasswordContraseñaRep,
            JTextField txtNombre,
            JTextField txtDni,
            JTextField txtTelefono,
            JTextField txtAlta,
            JTextField txtBaja,
            JTextField txtModificado,
            JTextField txtEmail) {

        txtId.setText("");
        txtUsuario.setText("");
        jPasswordContraseña.setText("");
        jPasswordContraseñaRep.setText("");
        txtNombre.setText("");
        txtDni.setText("");
        txtTelefono.setText("");
        txtAlta.setText("");
        txtBaja.setText("");
        txtModificado.setText("");
        txtEmail.setText("");
    }
}
