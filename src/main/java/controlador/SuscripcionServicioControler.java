package controlador;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.database.annotations.Nullable;
import com.mycompany.viuproyecto.Conexion;
import com.toedter.calendar.JDateChooser;
import static controlador.PrincipalControler.User;
import static controlador.PrincipalControler.tipoUser;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.SuscripcionServicio;
import providers.SuscripcionServicioProvider;
import vista.VistaLogin;
import vista.VistaPrincipal;
import vista.VistaSuscripcionServicio;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */
public class SuscripcionServicioControler extends ComponentesGeneralControler {

    PrincipalControler pc = new PrincipalControler();
    private SuscripcionServicioProvider suscripcionServicioProvider;
    private Firestore db;

    /**
     * Realizamos la conexión
     */
    public SuscripcionServicioControler() {
        db = Conexion.getConnection(); //Aquí se establece la conexión con Firestore
        suscripcionServicioProvider = new SuscripcionServicioProvider();
    }

    /** Fijamos al modelo Suscripcion Servicio los valores que recibimos como parametros
     * 
     * @param usuario user
     * @param servicio ser
     * @param inmueble inmueble
     * @param fechaAlta alta
     * @param fechaBaja baja
     * @param isActiva si es activa la suscripcion o no
     * @param idUsuario id user
     * @param idServicio id serv
     * @param idInmueble is inmueble
     */
    public void guardarSuscripcionServicio(
            String usuario,
            String servicio,
            String inmueble,
            Date fechaAlta,
            Date fechaBaja,
            boolean isActiva,
            String idUsuario,
            String idServicio,
            String idInmueble
    ) {

        SuscripcionServicio ss = new SuscripcionServicio();
        ss.setUsuario(usuario);
        ss.setServicio(servicio);
        ss.setInmueble(inmueble);
        ss.setFechaAlta(fechaAlta);
        ss.setFechaBaja(fechaBaja);
        ss.setIsActiva(isActiva);
        ss.setIdUsuario(idUsuario);
        ss.setIdServicio(idServicio);
        ss.setIdInmueble(idInmueble);

        try {
            suscripcionServicioProvider.guardarModeloSuscripcionServicio(ss);
            JOptionPane.showMessageDialog(null, "Suscripción Servicio guardado exitosamente");
            //System.out.println("uscripción Servicio guardado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            //System.out.println("Error al guardar la Suscripción Servicio: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al guardar la Suscripción Servicio: " + ex.getMessage());
        }
    }

    /**Este metodo carga datos en tiempo real todo es gracias a addSnapshotListener 
     * que escucha los cambios en la base de datos
     * 
     * @param tabla tbl
     */
    public static void cargarTablaSuscripcionServicioTR(JTable tabla) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("SuscripcionServicio");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Usuario");
        model.addColumn("Servicio");
        model.addColumn("Inmueble");
        model.addColumn("Fecha alta");
        model.addColumn("Fecha baja");
        model.addColumn("Es activa");
        model.addColumn("ID Usuario");
        model.addColumn("ID Servicio");
        model.addColumn("ID Inmueble");

        gastos.addSnapshotListener((@Nullable QuerySnapshot querySnapshot, @Nullable FirestoreException error) -> {
            if (error != null) {
                System.err.println("Error al escuchar cambios: " + error);
                return;
            }

            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {

                String isActiva = String.valueOf(document.getBoolean("isActiva"));
                if (isActiva.equals("true")) {
                    isActiva = "Si";
                } else {
                    isActiva = "No";
                }
                /*
            En el caso de las fechas se fija por defecto un valor tal cual es 0000-00-00
            en caso de venir vacio.
                 */

                Timestamp fechaAltaTimestamp = document.getTimestamp("fechaAlta");
                Timestamp fechaBajaTimestamp = document.getTimestamp("fechaBaja");
                /*
            Se debe validar que los datos traidos desde la BD no vengan nulos
            eso crea un error y por esa razón no cargará la tabla.
                 */
                Date fechaAlta = fechaAltaTimestamp != null ? fechaAltaTimestamp.toDate() : null;
                Date fechaBaja = fechaBajaTimestamp != null ? fechaBajaTimestamp.toDate() : null;
                /*
            Se puede sustituir los valores que se quiera mostrar en la tabla
            En el caso del valor de importe gasto se mostrará por defecto 0.0
            en caso de venir nulo.
                 */

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                /*
            En el caso de las fechas se fija por defecto un valor tal cual es 0000-00-00
            en caso de venir vacio.
                 */
                String fechaAltaString = fechaAlta != null ? dateFormat.format(fechaAlta) : "0000-00-00";
                String fechaBajaString = fechaBaja != null ? dateFormat.format(fechaBaja) : "";

                model.addRow(new Object[]{
                    document.getId(),
                    /*
                En el caso de las cadenas de texto se fija una cadena sin ningún caracter
                     */
                    document.getString("usuario") != null ? document.getString("usuario") : "",
                    document.getString("servicio") != null ? document.getString("servicio") : "",
                    document.getString("inmueble") != null ? document.getString("inmueble") : "",
                    fechaAltaString,
                    fechaBajaString,
                    isActiva != null ? isActiva : "",
                    document.getString("idUsuario") != null ? document.getString("idUsuario") : "",
                    document.getString("idServicio") != null ? document.getString("idServicio") : "",
                    document.getString("idInmueble") != null ? document.getString("idInmueble") : "",});
            }

            tabla.setModel(model);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(7).setMaxWidth(0);
            tabla.getColumnModel().getColumn(7).setMinWidth(0);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(8).setMaxWidth(0);
            tabla.getColumnModel().getColumn(8).setMinWidth(0);
            tabla.getColumnModel().getColumn(8).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(9).setMaxWidth(0);
            tabla.getColumnModel().getColumn(9).setMinWidth(0);
            tabla.getColumnModel().getColumn(9).setPreferredWidth(0);
        });
    }

    /** Carga la tabla se suscripcion de servicio para el vecino
     *
     * @param tabla tbl
     * @param vecino vecino
     */
    public static void cargarTablaSuscripcionServicioByVecinoTR(JTable tabla, String vecino) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("SuscripcionServicio");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Usuario");
        model.addColumn("Servicio");
        model.addColumn("Inmueble");
        model.addColumn("Fecha alta");
        model.addColumn("Fecha baja");
        model.addColumn("Es activa");
        model.addColumn("ID Usuario");
        model.addColumn("ID Servicio");
        model.addColumn("ID Inmueble");

        gastos.addSnapshotListener((querySnapshot, error) -> {
            if (error != null) {
                System.err.println("Error al escuchar cambios: " + error);
                return;
            }

            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                String documentVecino = document.getString("usuario");

                // Realizar comparación con vecino
                if (documentVecino.equals(vecino)) {
                    String isActiva = String.valueOf(document.getBoolean("isActiva"));
                    if (isActiva.equals("true")) {
                        isActiva = "Si";
                    } else {
                        isActiva = "No";
                    }

                    Timestamp fechaAltaTimestamp = document.getTimestamp("fechaAlta");
                    Timestamp fechaBajaTimestamp = document.getTimestamp("fechaBaja");

                    Date fechaAlta = fechaAltaTimestamp != null ? fechaAltaTimestamp.toDate() : null;
                    Date fechaBaja = fechaBajaTimestamp != null ? fechaBajaTimestamp.toDate() : null;

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    String fechaAltaString = fechaAlta != null ? dateFormat.format(fechaAlta) : "0000-00-00";
                    String fechaBajaString = fechaBaja != null ? dateFormat.format(fechaBaja) : "0000-00-00";

                    model.addRow(new Object[]{
                        document.getId(),
                        document.getString("usuario") != null ? document.getString("usuario") : "",
                        document.getString("servicio") != null ? document.getString("servicio") : "",
                        document.getString("inmueble") != null ? document.getString("inmueble") : "",
                        fechaAltaString,
                        fechaBajaString,
                        isActiva != null ? isActiva : "",
                        document.getString("idUsuario") != null ? document.getString("idUsuario") : "",
                        document.getString("idServicio") != null ? document.getString("idServicio") : "",
                        document.getString("idInmueble") != null ? document.getString("idInmueble") : ""
                    });
                }
            }

            tabla.setModel(model);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(7).setMaxWidth(0);
            tabla.getColumnModel().getColumn(7).setMinWidth(0);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(8).setMaxWidth(0);
            tabla.getColumnModel().getColumn(8).setMinWidth(0);
            tabla.getColumnModel().getColumn(8).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(9).setMaxWidth(0);
            tabla.getColumnModel().getColumn(9).setMinWidth(0);
            tabla.getColumnModel().getColumn(9).setPreferredWidth(0);
        });
    }

    /** Actualiza suscripcion servicio
     *
     * @param idDoc id
     * @param usuario user
     * @param servicio serv
     * @param inmueble inmueble
     * @param fechaAlta alta
     * @param fechaBaja baja
     * @param isActiva activo o no
     * @param idUsuario id user
     * @param idServicio id serv
     * @param idInmueble id inmueble
     */
    public void actualizarServicio(
            String idDoc,
            String usuario,
            String servicio,
            String inmueble,
            Date fechaAlta,
            Date fechaBaja,
            boolean isActiva,
            String idUsuario,
            String idServicio,
            String idInmueble) {

        SuscripcionServicio ss = new SuscripcionServicio();
        ss.setUsuario(usuario);
        ss.setServicio(servicio);
        ss.setInmueble(inmueble);
        ss.setFechaAlta(fechaAlta);
        ss.setFechaBaja(fechaBaja);
        ss.setIsActiva(isActiva);
        ss.setIdUsuario(idUsuario);
        ss.setIdServicio(idServicio);
        ss.setIdInmueble(idInmueble);

        try {
            suscripcionServicioProvider.actualizarModeloSuscripcionServicio(idDoc, ss);
            JOptionPane.showMessageDialog(null, "Suscripción Servicio actualizado exitosamente");
            //System.out.println("Suscripción Servicio actualizado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar Suscripción Servicio: " + ex.getMessage());
            //System.out.println("Error al actualizar Suscripción Servicio: " + ex.getMessage());
        }
    }

    /** Eliminar suscripcion
     *
     * @param idDoc id
     */
    public void eliminarSuscripcionServicio(String idDoc) {
        try {
            suscripcionServicioProvider.eliminarModeloSuscripcionServicio(idDoc);
            JOptionPane.showMessageDialog(null, "Suscripción Servicio eliminado exitosamente");
            //System.out.println("Suscripción Servicio eliminado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el suscripción servicio: " + ex.getMessage());
            // System.out.println("Error al eliminar el suscripción servicio: " + ex.getMessage());
        }
    }

    /** Seleccion de usuario
     *
     * @param comboBox componenete
     * @return valor
     */
    public boolean verificarSeleccionUsuario(JComboBox comboBox) {
        if (!verificarSeleccion(comboBox)) {
            JOptionPane.showMessageDialog(null, "Seleccione una opción en Usuario");
            return false;
        }
        return true;
    }

    /**  Seleccion de servicio
     *
     * @param comboBox componenete
     * @return valor
     */
    public boolean verificarSeleccionServicio(JComboBox comboBox) {
        if (!verificarSeleccion(comboBox)) {
            JOptionPane.showMessageDialog(null, "Seleccione una opción en Servicio");
            return false;
        }
        return true;
    }

    /** Seleccion de inmueble
     *
     * @param comboBox componenete
     * @return valor
     */
    public boolean verificarSeleccionInmueble(JComboBox comboBox) {
        if (!verificarSeleccion(comboBox)) {
            JOptionPane.showMessageDialog(null, "Seleccione una opción en Inmueble");
            return false;
        }
        return true;
    }

    /** Selecicon de usuario
     *
     * @param box componente
     * @throws InterruptedException error
     * @throws ExecutionException error
     * @throws IOException error
     */
    public static void cargarComboUsuario(JComboBox<String> box) throws InterruptedException, ExecutionException, IOException {
        Firestore db = Conexion.getConnection();
        CollectionReference proveedores = db.collection("Usuario");
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Seleccione uno");
        proveedores.addSnapshotListener((querySnapshot, e) -> {
            if (e != null) {
                System.err.println("Ocurrió un error al escuchar los cambios de usuarios: " + e);
                return;
            }
            modelo.removeAllElements();
            modelo.addElement("Seleccione uno");
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                modelo.addElement(document.getString("nombre") + " - " + document.getId());
            }
            box.setModel(modelo);
        });
    }

    /** Seleccion de servicio
     *
     * @param box componente
     * @throws InterruptedException error
     * @throws ExecutionException error
     * @throws IOException error
     */
    public static void cargarComboServicio(JComboBox<String> box) throws InterruptedException, ExecutionException, IOException {
        Firestore db = Conexion.getConnection();
        CollectionReference proveedores = db.collection("Servicio");
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Seleccione uno");
        proveedores.addSnapshotListener((querySnapshot, e) -> {
            if (e != null) {
                System.err.println("Ocurrió un error al escuchar los cambios de servicios: " + e);
                return;
            }
            modelo.removeAllElements();
            modelo.addElement("Seleccione uno");
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                modelo.addElement(document.getString("nombre") + " - " + document.getId());
            }
            box.setModel(modelo);
        });
    }

    /** Seleccion de inmueble
     *
     * @param box componente
     * @throws InterruptedException error
     * @throws ExecutionException error
     * @throws IOException error
     */
    public static void cargarComboInmueble(JComboBox<String> box) throws InterruptedException, ExecutionException, IOException {
        Firestore db = Conexion.getConnection();
        CollectionReference proveedores = db.collection("Inmueble");
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Seleccione uno");
        proveedores.addSnapshotListener((querySnapshot, e) -> {
            if (e != null) {
                System.err.println("Ocurrió un error al escuchar los cambios de inmuebles: " + e);
                return;
            }
            modelo.removeAllElements();
            modelo.addElement("Seleccione uno");
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                modelo.addElement(document.getString("domicilio") + " - " + document.getId());
            }
            box.setModel(modelo);
        });
    }

    /**Seleccion de inmueble pra usuario
     *
     * @param box componente
     * @param tipoUsuario tipo user
     * @param usuario user
     * @throws InterruptedException error
     * @throws ExecutionException error
     * @throws IOException error
     */
    public static void cargarComboInmuebleByUser(JComboBox<String> box, String tipoUsuario, String usuario) throws InterruptedException, ExecutionException, IOException {
        Firestore db = Conexion.getConnection();
        CollectionReference proveedores = db.collection("Inmueble");
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Seleccione uno");

        proveedores.addSnapshotListener((querySnapshot, e) -> {
            if (e != null) {
                System.err.println("Ocurrió un error al escuchar los cambios de inmuebles: " + e);
                return;
            }

            modelo.removeAllElements();
            modelo.addElement("Seleccione uno");

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                String vecino = document.getString("vecino");

                // Realizar comparación con tipoUsuario y usuario
                if (vecino.equals(usuario)) {
                    modelo.addElement(document.getString("domicilio") + " - " + document.getId());
                }
            }

            box.setModel(modelo);
        });
    }

    /**
     * Muestra el menu segun el tipo de usuario
     */
    public void componetesSegunUsuario() {
        try {
            if (VistaLogin.idUser == null) {
                System.out.println("El id esta vacio: " + VistaLogin.idUser);
            } else {
                tipoUser = pc.obtenerTipoUsuario(VistaLogin.idUser);
                User = pc.obtenerUsuario(VistaLogin.idUser);
                if (tipoUser.equals("Administrador")) {
                    /*
                    Es Administrador
                     */
                    verComponentesAdmin();
                    //System.out.println("Soy Administrador");
                } else if (tipoUser.equals("Vecino")) {
                    /*
                    Es Vecino
                     */
                    ocultarComponentesAdmin();
                    //System.out.println("Soy Vecino");
                }
            }

        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(VistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** limpia ventana
     *
     * @param txtId id
     * @param cmbUsuario user
     * @param cmbServicio serv
     * @param cmbInmueble inmueble
     * @param jFechaGasto fecha
     * @param jFechaPago fecha
     * @param cheIsActiva activa o no
     * @param txtNombreUsuario user
     * @param txtNombreServicio serv 
     * @param txtNombreInmueble inmueble
     * @param txtIdUsuario id user
     * @param txtIdServicio id serv 
     * @param txtIdInmueble id inmueble
     */
    public void limpiarCampos(
            JTextField txtId,
            JComboBox cmbUsuario,
            JComboBox cmbServicio,
            JComboBox cmbInmueble,
            JDateChooser jFechaGasto,
            JDateChooser jFechaPago,
            JCheckBox cheIsActiva,
            JTextField txtNombreUsuario,
            JTextField txtNombreServicio,
            JTextField txtNombreInmueble,
            JTextField txtIdUsuario,
            JTextField txtIdServicio,
            JTextField txtIdInmueble
    ) {

        txtId.setText("");
        jFechaGasto.setDate(null);
        jFechaPago.setDate(null);
        cheIsActiva.setSelected(false);
        txtIdUsuario.setText("");
        txtIdServicio.setText("");
        txtIdInmueble.setText("");
        txtNombreUsuario.setText("");
        txtNombreServicio.setText("");
        txtNombreInmueble.setText("");
        cmbUsuario.setSelectedIndex(0);
        cmbServicio.setSelectedIndex(0);
        cmbInmueble.setSelectedIndex(0);
    }

    /**
     * Oculta componentes de administrador
     */
    public void ocultarComponentesAdmin() {
        VistaSuscripcionServicio.panelBtns.setVisible(false);
        VistaSuscripcionServicio.panelInputsDerecha.setVisible(false);
        VistaSuscripcionServicio.panelInputsIzquierda.setVisible(false);
    }

    /**
     * Muestra componentes administrador
     */
    public void verComponentesAdmin() {
        VistaSuscripcionServicio.panelBtns.setVisible(true);
        VistaSuscripcionServicio.panelInputsDerecha.setVisible(true);
        VistaSuscripcionServicio.panelInputsIzquierda.setVisible(true);
    }
}
