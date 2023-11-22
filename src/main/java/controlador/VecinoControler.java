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
import modelo.Usuario;
import modelo.Vecino;
import providers.UsuarioProvider;
import providers.VecinoProvider;

/** Asignatura Proyecto de ingenieria de software 
 * 
 * @author frami
 */
public class VecinoControler extends ComponentesGeneralControler {

    private VecinoProvider vecinoProvider;
    private UsuarioProvider usuarioProvider;
    private Firestore db;

    /**
     * Realizamos la conexión
     */
    public VecinoControler() {
        db = Conexion.getConnection(); //Aquí se establece la conexión con Firestore
        vecinoProvider = new VecinoProvider();
        usuarioProvider = new UsuarioProvider();
    }

    /** Fijamos al modelo Vecino los valores que recibimos como parametros
     * 
     * @param usuario vecino
     * @param contraseña vecino
     * @param dni vecino
     * @param nombre vecino
     * @param tipoUsuario vecino
     * @param telefono vecino
     * @param email vecino
     */
    public void guardarVecino(
            String usuario,
            String contraseña,
            String dni,
            String nombre,
            String tipoUsuario,
            String telefono,
            String email) {

        Vecino v = new Vecino();
        v.setUsuario(usuario);
        v.setContraseña(contraseña);
        v.setDni(dni);
        v.setNombre(nombre);
        v.setTipo(tipoUsuario);
        v.setTelefono(telefono);
        v.setEmail(email);

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
            usuarioProvider.guardarModeloUsuario(u);//Guardamos el nuevo usuario con el id para enviarlo 
            vecinoProvider.guardarModeloVecino(v);//Recibimos ese id y lo guardamos en el Vecino
            JOptionPane.showMessageDialog(null, "Vecino guardado exitosamente");
            //System.out.println("Vecino guardado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            //System.out.println("Error al guardar el vecino: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al guardar el vecino: " + ex.getMessage());
        }
    }

    /** Este metodo carga datos en tiempo real todo es gracias a addSnapshotListener 
     * que escucha los cambios en la base de datos
     * 
     * @param tabla tbl
     */
    public static void cargarTablaVecinoTR(JTable tabla) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("Vecino");

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
                    document.getString("idUsuario") != null ? document.getString("idUsuario") : "",
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

    /** Actualiza el vecino
     *
     * @param idDoc vecino
     * @param idDocUsuario  vecino
     * @param usuario vecino
     * @param contraseña vecino
     * @param dni vecino
     * @param nombre vecino
     * @param tipoUsuario vecino
     * @param telefono vecino
     * @param email vecino
     */
    public void actualizarUsuario(
            String idDoc,
            String idDocUsuario,
            String usuario,
            String contraseña,
            String dni,
            String nombre,
            String tipoUsuario,
            String telefono,
            String email) {

        Vecino v = new Vecino();
        v.setUsuario(usuario);
        v.setContraseña(contraseña);
        v.setDni(dni);
        v.setNombre(nombre);
        v.setTipo(tipoUsuario);
        v.setTelefono(telefono);
        v.setEmail(email);

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
            vecinoProvider.actualizarModeloVecino(idDoc, v);
            usuarioProvider.actualizarModeloUsuario(idDocUsuario, u);
            JOptionPane.showMessageDialog(null, "Vecino actualizado exitosamente");
            //System.out.println("Vecino actualizado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el vecino: " + ex.getMessage());
            //System.out.println("Error al actualizar el vecino: " + ex.getMessage());
        }
    }

    /**Elimina el vecino
     *
     * @param idDoc vecino
     * @param idDocUsuario vecino
     */
    public void eliminarVecino(String idDoc, String idDocUsuario) {
        try {
            vecinoProvider.eliminarModeloVecino(idDoc);
            usuarioProvider.eliminarModeloUsuario(idDocUsuario);
            JOptionPane.showMessageDialog(null, "Vecino eliminado exitosamente");
            //System.out.println("Vecino eliminado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el vecino: " + ex.getMessage());
            // System.out.println("Error al eliminar el vecino: " + ex.getMessage());
        }
    }

    /** Elimina el vecino en funcion de su Id
     * 
     * @param idDocUsuario id vecino 
     */
    public void eliminarVecinoByIdUser(String idDocUsuario) {
        try {
            vecinoProvider.eliminarModeloVecino(idDocUsuario);
            //usuarioProvider.eliminarModeloUsuario(idDoc);
            JOptionPane.showMessageDialog(null, "Vecino eliminado exitosamente");
            //System.out.println("Vecino eliminado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el vecino: " + ex.getMessage());
            // System.out.println("Error al eliminar el vecino: " + ex.getMessage());
        }
    }

    /** limpia ventana
     *
     * @param txtId vecino
     * @param txtNombre vecino
     * @param txtDescripcion vecino
     */
    public void limpiarCampos(
            JTextField txtId,
            JTextField txtNombre,
            JTextArea txtDescripcion) {

        txtId.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
    }

    /** limpia ventana
     *
     * @param txtId vecino
     * @param txtIdUsuario vecino
     * @param txtUsuario vecino
     * @param jPasswordContraseña vecino
     * @param jPasswordContraseñaRep vecino
     * @param txtNombre vecino 
     * @param txtDni vecino
     * @param txtTelefono vecino
     * @param txtEmail vecino
     */
    public void limpiarCamposAdmin(
            JTextField txtId,
            JTextField txtIdUsuario,
            JTextField txtUsuario,
            JPasswordField jPasswordContraseña,
            JPasswordField jPasswordContraseñaRep,
            JTextField txtNombre,
            JTextField txtDni,
            JTextField txtTelefono,
            JTextField txtEmail) {

        txtId.setText("");
        txtIdUsuario.setText("");
        txtUsuario.setText("");
        jPasswordContraseña.setText("");
        jPasswordContraseñaRep.setText("");
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

    /** Define el formato de fecha
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

    /** Permite convertir la fecha a string
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
