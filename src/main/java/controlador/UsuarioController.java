package controlador;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.database.annotations.Nullable;
import com.mycompany.viuproyecto.Conexion;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextArea;
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
public class UsuarioController extends ComponentesGeneralControler {

    private UsuarioProvider usuarioProvider;
    private AdministradorProvider administradorProvider;
    private VecinoProvider vecinoProvider;
    private Firestore db;

    /**
     * Realizamos la conexión
     */
    public UsuarioController() {
        db = Conexion.getConnection(); //Aquí se establece la conexión con Firestore
        usuarioProvider = new UsuarioProvider();
        administradorProvider = new AdministradorProvider();
        vecinoProvider = new VecinoProvider();
    }

    /** Fijamos al modelo Concepto los valores que recibimos como parametros
     * 
     * @param usuario user
     * @param contraseña clave
     * @param dni dni user
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

    /** Este metodo carga datos en tiempo real todo es gracias a addSnapshotListener 
     * que escucha los cambios en la base de datos
     * 
     * @param tabla tbl
     */
    public static void cargarTablaUsuarioTR(JTable tabla) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("Usuario");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("usuario");
        model.addColumn("Contraseña");
        model.addColumn("DNI");
        model.addColumn("Nombre");
        model.addColumn("Tipo");
        model.addColumn("Teléfono");
        model.addColumn("E-mail");
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
                    document.getString("usuario") != null ? document.getString("usuario") : "",
                    document.getString("contraseña") != null ? document.getString("contraseña") : "",
                    document.getString("dni") != null ? document.getString("dni") : "",
                    document.getString("nombre") != null ? document.getString("nombre") : "",
                    document.getString("tipo") != null ? document.getString("tipo") : "",
                    document.getString("telefono") != null ? document.getString("telefono") : "",
                    document.getString("email") != null ? document.getString("email") : "",
                    fechaAlta
                });

            }

            tabla.setModel(model);
        });
    }

    /** Actualiza usuario
     *
     * @param idDoc id user
     * @param usuario user
     * @param contraseña clave user
     * @param dni user
     * @param nombre user
     * @param tipoUsuario user
     * @param telefono user 
     * @param email user
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
                usuarioProvider.actualizarModeloUsuario(idDoc, u);
                administradorProvider.actualizarAdministradorPorIdUsuario(idDoc, a);
                JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente");
                //System.out.println("Proveedor actualizado exitosamente");
            } catch (InterruptedException | ExecutionException ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar el usuario: " + ex.getMessage());
                //System.out.println("Error al actualizar el proveedor: " + ex.getMessage());
            }
        } else if (tipoUsuario.equals("Vecino")) {
            try {
                usuarioProvider.actualizarModeloUsuario(idDoc, u);
                vecinoProvider.actualizarVecinorPorIdUsuario(idDoc, v);
                JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente");
                //System.out.println("Proveedor actualizado exitosamente");
            } catch (InterruptedException | ExecutionException ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar el usuario: " + ex.getMessage());
                //System.out.println("Error al actualizar el proveedor: " + ex.getMessage());
            }
        }
    }

    /** Actualiza usuario concreto
     *
     * @param idDoc id user
     * @param usuario user
     * @param contraseña user
     * @param dni user
     * @param nombre user
     * @param tipoUsuario user
     * @param telefono user
     * @param email user
     */
    public void actualizarUsuarioXXX(
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

        DocumentSnapshot usuarioActual = null;
        usuarioActual = usuarioProvider.obtenerModeloUsuarioDOC(idDoc);

        if (usuarioActual == null) {
            JOptionPane.showMessageDialog(null, "No se encontró un usuario con el ID especificado.");
            return;
        }

        String tipoUsuarioActual = usuarioActual.getString("tipo");
        if (tipoUsuarioActual.equals(tipoUsuario)) {
            // El tipo de usuario no ha cambiado, solo actualizar el documento en la colección 'Usuario'
            try {
                usuarioProvider.actualizarModeloUsuario(idDoc, u);
            } catch (InterruptedException | ExecutionException ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar el usuario en la colección 'Usuario': " + ex.getMessage());
                return;
            }
        } else {
            // El tipo de usuario ha cambiado, realizar las acciones correspondientes
            try {
                // Eliminar el usuario de su colección original
                if (tipoUsuarioActual.equals("Administrador")) {
                    administradorProvider.eliminarAdministradorPorIdUsuario(idDoc);
                    usuarioProvider.eliminarModeloUsuario(idDoc);
                } else if (tipoUsuarioActual.equals("Vecino")) {
                    vecinoProvider.eliminarVecinoPorIdUsuario(idDoc);
                    usuarioProvider.eliminarModeloUsuario(idDoc);
                }

                // Actualizar el documento en la colección 'Usuario'
                //usuarioProvider.actualizarModeloUsuario(idDoc, u);

                // Insertar el usuario en su nueva colección
                if (tipoUsuario.equals("Administrador")) {
                    Administrador administrador = u.convertirAAdministrador();
                   // administrador.setId(idDoc);
                    usuarioProvider.guardarModeloUsuario(u);//Creamos el ID en el Usuario
                    administradorProvider.guardarModeloAdministrador(a);//Obtenemos el ID del Usuario y Guardamos en el Administrador

                } else if (tipoUsuario.equals("Vecino")) {
                    Vecino vecino = u.convertirAVecino();
                   // vecino.setId(idDoc);
                    usuarioProvider.guardarModeloUsuario(u);//Creamos el ID en el Usuario
                    vecinoProvider.guardarModeloVecino(v);//Obtenemos el ID //Obtenemos el ID del Usuario y Guardamos en el Vecino

                }
            } catch (InterruptedException | ExecutionException ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar el usuario: " + ex.getMessage());
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente.");
    }

    /** Elimina usuario 
     *
     * @param idDoc id
     * @param esAdministrador comprueba si es admin
     */
    public void eliminarUsuario(
            String idDoc,
            boolean esAdministrador) {

        if (esAdministrador) {
            try {
                usuarioProvider.eliminarModeloUsuario(idDoc);
                administradorProvider.eliminarAdministradorPorIdUsuario(idDoc);
                JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente");
                //System.out.println("Gasto eliminado exitosamente");
            } catch (InterruptedException | ExecutionException ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar el usuario: " + ex.getMessage());
                // System.out.println("Error al eliminar el gasto: " + ex.getMessage());
            }
        } else if (!esAdministrador) {
            try {
                usuarioProvider.eliminarModeloUsuario(idDoc);
                vecinoProvider.eliminarModeloVecino(idDoc);
                JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente");
                //System.out.println("Gasto eliminado exitosamente");
            } catch (InterruptedException | ExecutionException ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar el usuario: " + ex.getMessage());
                // System.out.println("Error al eliminar el gasto: " + ex.getMessage());
            }
        }
    }

    /** limpia campos ventana
     *
     * @param txtId id
     * @param txtNombre user
     * @param txtDescripcion user
     */
    public void limpiarCampos(
            JTextField txtId,
            JTextField txtNombre,
            JTextArea txtDescripcion) {

        txtId.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
    }

    /** limpia campos ventana
     *
     * @param txtId user
     * @param txtUsuario user
     * @param jPasswordContraseña user
     * @param jPasswordContraseñaRep user
     * @param cmbUsuario user
     * @param txtNombre user
     * @param txtDni user
     * @param txtTelefono user
     * @param txtEmail user
     */
    public void limpiarCamposAdmin(
            JTextField txtId,
            JTextField txtUsuario,
            JPasswordField jPasswordContraseña,
            JPasswordField jPasswordContraseñaRep,
            JComboBox cmbUsuario,
            JTextField txtNombre,
            JTextField txtDni,
            JTextField txtTelefono,
            JTextField txtEmail) {

        txtId.setText("");
        txtUsuario.setText("");
        jPasswordContraseña.setText("");
        jPasswordContraseñaRep.setText("");
        cmbUsuario.setSelectedIndex(0);
        txtNombre.setText("");
        txtDni.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");

    }

    /** Selecciona el usuario
     *
     * @param comboBox componente
     * @return valor
     */
    public boolean verificarSeleccionUsuario(JComboBox comboBox) {
        if (!verificarSeleccion(comboBox)) {
            JOptionPane.showMessageDialog(null, "Seleccione una opción en Tipo de Usuario");
            return false;
        }
        return true;
    }

    /**Permite definir el formato de fecha
     * 
     * @param timestamp formato
     * @return valor
     */
    public static String formatDate(long timestamp) {
        // Crear un objeto Date a partir del timestamp
        Date date = new Date(timestamp);

        // Crear un objeto SimpleDateFormat para formatear la fecha y hora
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        // Configurar la zona horaria a UTC (opcional)
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        // Formatear la fecha y hora y devolver como cadena de texto
        return formatter.format(date);
    }

    /** Permite dar formato a la fecha
     * 
     * @param timestamp formato
     * @return valor
     */
    public static String getTimeElapsedString(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        Instant now = Instant.now();
        Duration duration = Duration.between(instant, now);
        long seconds = duration.getSeconds();
        if (seconds < 60) {
            return seconds + " segundo" + (seconds == 1 ? "" : "s") + " atrás";
        }
        long minutes = seconds / 60;
        if (minutes < 60) {
            return minutes + " minuto" + (minutes == 1 ? "" : "s") + " atrás";
        }
        long hours = minutes / 60;
        if (hours < 24) {
            return hours + " hora" + (hours == 1 ? "" : "s") + " atrás";
        }
        long days = hours / 24;
        if (days < 30) {
            return days + " día" + (days == 1 ? "" : "s") + " atrás";
        }
        long months = days / 30;
        if (months < 12) {
            return months + " mes" + (months == 1 ? "" : "es") + " atrás";
        }
        long years = months / 12;
        return years + " año" + (years == 1 ? "" : "s") + " atrás";
    }
}
