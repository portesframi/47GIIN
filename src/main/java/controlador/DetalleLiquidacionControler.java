package controlador;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.mycompany.viuproyecto.Conexion;
import static controlador.PrincipalControler.User;
import static controlador.PrincipalControler.tipoUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.DetalleLiquidacion;
import providers.DetalleLiquidacionProvider;
import vista.VistaDetalleLiquidacion;
import vista.VistaLogin;
import vista.VistaPrincipal;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */
public class DetalleLiquidacionControler extends ComponentesGeneralControler {

    PrincipalControler pc = new PrincipalControler();
    private DetalleLiquidacionProvider detalleLiquidacionProvider;
    private Firestore db;
    // Declarar una variable miembro para querySnapshot
    private static QuerySnapshot querySnapshot;


    /**
     * Realizamos la conexión
     */

    public DetalleLiquidacionControler() {
        db = Conexion.getConnection(); //Aquí se establece la conexión con Firestore
        detalleLiquidacionProvider = new DetalleLiquidacionProvider();
    }

    /** Fijamos al modelo Detalle de liquidacion los valores que recibimos como parametros
     * 
     * @param idInmueble id
     * @param nombreInmueble nombre
     * @param idUsuario id usuario
     * @param nombreUsuario nombre
     * @param idServicio id servicio
     * @param nombreSuscripcionServicio nombre
     * @param nombre nombre servicio
     * @param inporteFinal importe suscripcion
     * @param concepto concepto suscripcion
     * @param periodoLiquidacuion periodo liquidacion suscripcion
     */
    public void guardarDetalleLiquidacion(
            String idInmueble,
            String nombreInmueble,
            String idUsuario,
            String nombreUsuario,
            String idServicio,
            String nombreSuscripcionServicio,
            String nombre,
            double inporteFinal,
            String concepto,
            String periodoLiquidacuion
    ) {

        DetalleLiquidacion dl = new DetalleLiquidacion();
        dl.setIdInmueble(idInmueble);
        dl.setNombreInmueble(nombreInmueble);
        dl.setIdUsuario(idUsuario);
        dl.setNombreUsuario(nombreUsuario);
        dl.setIdServicio(idServicio);
        dl.setNombreSuscripcionServicio(nombreSuscripcionServicio);
        dl.setNombre(nombre);
        dl.setImporteFinal(inporteFinal);
        dl.setConcepto(concepto);
        dl.setPeriodoLiquidacion(periodoLiquidacuion);

        try {
            detalleLiquidacionProvider.guardarModeloDetalleLiquidacion(dl);
            //  JOptionPane.showMessageDialog(null, "Detalle de Liquidación guardado exitosamente");
            //System.out.println("Gasto guardado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            //System.out.println("Error al guardar el gasto: " + ex.getMessage());
            //JOptionPane.showMessageDialog(null, "Error al guardar el detalle de liquidación: " + ex.getMessage());
        }
    }

    /**Este metodo carga datos en tiempo real todo es gracias a addSnapshotListener 
     * que escucha los cambios en la base de datos
     * 
     * @param tabla carga
     */
    public static void cargarAllTablaDetalleLiquidacionTR(JTable tabla) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("DetalleLiquidacion");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre clave");
        model.addColumn("Nombre del Vecino");
        model.addColumn("ID Vecino");
        model.addColumn("Suscripción Servicio");
        model.addColumn("ID Servicio");
        model.addColumn("Nombre del Inmueble");
        model.addColumn("ID Inmueble");

        model.addColumn("Concepto");
        model.addColumn("Importe Final");
        model.addColumn("Periodo de Liquidación");

        gastos.addSnapshotListener((querySnapshot, error) -> {
            if (error != null) {
                System.err.println("Error al escuchar cambios: " + error);
                return;
            }

            model.setRowCount(0); // Limpia el modelo de datos

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {

                // Agrega una fila al modelo de datos
                model.addRow(new Object[]{
                    document.getId(),
                    document.getString("nombre") != null ? document.getString("nombre") : "",
                    document.getString("nombreUsuario") != null ? document.getString("nombreUsuario") : "",
                    document.getString("idUsuario") != null ? document.getString("idUsuario") : "",
                    document.getString("nombreSuscripcionServicio") != null ? document.getString("nombreSuscripcionServicio") : "",
                    document.getString("idServicio") != null ? document.getString("idServicio") : "",
                    document.getString("nombreInmueble") != null ? document.getString("nombreInmueble") : "",
                    document.getString("idInmueble") != null ? document.getString("idInmueble") : "",
                    document.getString("concepto") != null ? document.getString("concepto") : "",
                    document.getDouble("importeFinal") != null ? document.getDouble("importeFinal") : "",
                    document.getString("periodoLiquidacion") != null ? document.getString("periodoLiquidacion") : "",});
            }

            tabla.setModel(model);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(1).setMaxWidth(0);
            tabla.getColumnModel().getColumn(1).setMinWidth(0);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(3).setMaxWidth(0);
            tabla.getColumnModel().getColumn(3).setMinWidth(0);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(5).setMaxWidth(0);
            tabla.getColumnModel().getColumn(5).setMinWidth(0);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(7).setMaxWidth(0);
            tabla.getColumnModel().getColumn(7).setMinWidth(0);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(0);
        });
    }

    /** permite cargar la tabla del detalle de liquidacion
     * 
     * @param tabla carga tabla
     * @param periodoLiquidacion carga periodo
     */
    public static void cargarTablaDetalleLiquidacionTR(JTable tabla, String periodoLiquidacion) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("DetalleLiquidacion");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre clave");
        model.addColumn("Nombre del Vecino");
        model.addColumn("ID Vecino");
        model.addColumn("Suscripción Servicio");
        model.addColumn("ID Servicio");
        model.addColumn("Nombre del Inmueble");
        model.addColumn("ID Inmueble");

        model.addColumn("Concepto");
        model.addColumn("Importe Final");
        model.addColumn("Periodo de Liquidación");

        Query query = gastos.whereEqualTo("periodoLiquidacion", periodoLiquidacion);

        query.addSnapshotListener((querySnapshot, error) -> {
            if (error != null) {
                System.err.println("Error al escuchar cambios: " + error);
                return;
            }

            if (querySnapshot.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontraron documentos para el periodo de liquidación: " + periodoLiquidacion);
            }

            model.setRowCount(0); // Limpia el modelo de datos

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                // Agrega una fila al modelo de datos
                model.addRow(new Object[]{
                    document.getId(),
                    document.getString("nombre") != null ? document.getString("nombre") : "",
                    document.getString("nombreUsuario") != null ? document.getString("nombreUsuario") : "",
                    document.getString("idUsuario") != null ? document.getString("idUsuario") : "",
                    document.getString("nombreSuscripcionServicio") != null ? document.getString("nombreSuscripcionServicio") : "",
                    document.getString("idServicio") != null ? document.getString("idServicio") : "",
                    document.getString("nombreInmueble") != null ? document.getString("nombreInmueble") : "",
                    document.getString("idInmueble") != null ? document.getString("idInmueble") : "",
                    document.getString("concepto") != null ? document.getString("concepto") : "",
                    document.getDouble("importeFinal") != null ? document.getDouble("importeFinal") : 0.0,
                    document.getString("periodoLiquidacion") != null ? document.getString("periodoLiquidacion") : "",});
            }

            tabla.setModel(model);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(1).setMaxWidth(0);
            tabla.getColumnModel().getColumn(1).setMinWidth(0);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(3).setMaxWidth(0);
            tabla.getColumnModel().getColumn(3).setMinWidth(0);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(5).setMaxWidth(0);
            tabla.getColumnModel().getColumn(5).setMinWidth(0);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(7).setMaxWidth(0);
            tabla.getColumnModel().getColumn(7).setMinWidth(0);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(0);
        });
    }

    /** carga tabla servicio
     *
     * @param tabla tabla
     * @param servicio servicio
     */
    public static void cargarTablaServicioTR(JTable tabla, String servicio) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("DetalleLiquidacion");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre clave");
        model.addColumn("Nombre del Vecino");
        model.addColumn("ID Vecino");
        model.addColumn("Suscripción Servicio");
        model.addColumn("ID Servicio");
        model.addColumn("Nombre del Inmueble");
        model.addColumn("ID Inmueble");

        model.addColumn("Concepto");
        model.addColumn("Importe Final");
        model.addColumn("Periodo de Liquidación");

        Query query = gastos.whereEqualTo("nombreSuscripcionServicio", servicio);

        query.addSnapshotListener((querySnapshot, error) -> {
            if (error != null) {
                System.err.println("Error al escuchar cambios: " + error);
                return;
            }

            if (querySnapshot.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontraron documentos para el servicio: " + servicio);
            }

            model.setRowCount(0); // Limpia el modelo de datos

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                // Agrega una fila al modelo de datos
                model.addRow(new Object[]{
                    document.getId(),
                    document.getString("nombre") != null ? document.getString("nombre") : "",
                    document.getString("nombreUsuario") != null ? document.getString("nombreUsuario") : "",
                    document.getString("idUsuario") != null ? document.getString("idUsuario") : "",
                    document.getString("nombreSuscripcionServicio") != null ? document.getString("nombreSuscripcionServicio") : "",
                    document.getString("idServicio") != null ? document.getString("idServicio") : "",
                    document.getString("nombreInmueble") != null ? document.getString("nombreInmueble") : "",
                    document.getString("idInmueble") != null ? document.getString("idInmueble") : "",
                    document.getString("concepto") != null ? document.getString("concepto") : "",
                    document.getDouble("importeFinal") != null ? document.getDouble("importeFinal") : 0.0,
                    document.getString("periodoLiquidacion") != null ? document.getString("periodoLiquidacion") : "",});
            }

            tabla.setModel(model);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(1).setMaxWidth(0);
            tabla.getColumnModel().getColumn(1).setMinWidth(0);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(3).setMaxWidth(0);
            tabla.getColumnModel().getColumn(3).setMinWidth(0);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(5).setMaxWidth(0);
            tabla.getColumnModel().getColumn(5).setMinWidth(0);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(7).setMaxWidth(0);
            tabla.getColumnModel().getColumn(7).setMinWidth(0);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(0);
        });
    }

    /** cargar tabla inmueble
     *
     * @param tabla tabla 
     * @param domicilio direccion
     */
    public static void cargarTablaInmuebleTR(JTable tabla, String domicilio) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("DetalleLiquidacion");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre clave");
        model.addColumn("Nombre del Vecino");
        model.addColumn("ID Vecino");
        model.addColumn("Suscripción Servicio");
        model.addColumn("ID Servicio");
        model.addColumn("Nombre del Inmueble");
        model.addColumn("ID Inmueble");

        model.addColumn("Concepto");
        model.addColumn("Importe Final");
        model.addColumn("Periodo de Liquidación");

        Query query = gastos.whereEqualTo("nombreInmueble", domicilio);

        query.addSnapshotListener((querySnapshot, error) -> {
            if (error != null) {
                System.err.println("Error al escuchar cambios: " + error);
                return;
            }

            if (querySnapshot.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontraron documentos para el inmueble: " + domicilio);
            }

            model.setRowCount(0); // Limpia el modelo de datos

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                // Agrega una fila al modelo de datos
                model.addRow(new Object[]{
                    document.getId(),
                    document.getString("nombre") != null ? document.getString("nombre") : "",
                    document.getString("nombreUsuario") != null ? document.getString("nombreUsuario") : "",
                    document.getString("idUsuario") != null ? document.getString("idUsuario") : "",
                    document.getString("nombreSuscripcionServicio") != null ? document.getString("nombreSuscripcionServicio") : "",
                    document.getString("idServicio") != null ? document.getString("idServicio") : "",
                    document.getString("nombreInmueble") != null ? document.getString("nombreInmueble") : "",
                    document.getString("idInmueble") != null ? document.getString("idInmueble") : "",
                    document.getString("concepto") != null ? document.getString("concepto") : "",
                    document.getDouble("importeFinal") != null ? document.getDouble("importeFinal") : 0.0,
                    document.getString("periodoLiquidacion") != null ? document.getString("periodoLiquidacion") : "",});
            }

            tabla.setModel(model);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(1).setMaxWidth(0);
            tabla.getColumnModel().getColumn(1).setMinWidth(0);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(3).setMaxWidth(0);
            tabla.getColumnModel().getColumn(3).setMinWidth(0);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(5).setMaxWidth(0);
            tabla.getColumnModel().getColumn(5).setMinWidth(0);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(7).setMaxWidth(0);
            tabla.getColumnModel().getColumn(7).setMinWidth(0);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(0);
        });
    }

    /** Carga tabla vecino
     *
     * @param tabla tabla
     * @param vecino vecino
     */
    public static void cargarTablaVecinoTR(JTable tabla, String vecino) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("DetalleLiquidacion");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre clave");
        model.addColumn("Nombre del Vecino");
        model.addColumn("ID Vecino");
        model.addColumn("Suscripción Servicio");
        model.addColumn("ID Servicio");
        model.addColumn("Nombre del Inmueble");
        model.addColumn("ID Inmueble");

        model.addColumn("Concepto");
        model.addColumn("Importe Final");
        model.addColumn("Periodo de Liquidación");

        Query query = gastos.whereEqualTo("nombreUsuario", vecino);

        query.addSnapshotListener((querySnapshot, error) -> {
            if (error != null) {
                System.err.println("Error al escuchar cambios: " + error);
                return;
            }

            if (querySnapshot.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontraron documentos para la vecino: " + vecino);
            }

            model.setRowCount(0); // Limpia el modelo de datos

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                // Agrega una fila al modelo de datos
                model.addRow(new Object[]{
                    document.getId(),
                    document.getString("nombre") != null ? document.getString("nombre") : "",
                    document.getString("nombreUsuario") != null ? document.getString("nombreUsuario") : "",
                    document.getString("idUsuario") != null ? document.getString("idUsuario") : "",
                    document.getString("nombreSuscripcionServicio") != null ? document.getString("nombreSuscripcionServicio") : "",
                    document.getString("idServicio") != null ? document.getString("idServicio") : "",
                    document.getString("nombreInmueble") != null ? document.getString("nombreInmueble") : "",
                    document.getString("idInmueble") != null ? document.getString("idInmueble") : "",
                    document.getString("concepto") != null ? document.getString("concepto") : "",
                    document.getDouble("importeFinal") != null ? document.getDouble("importeFinal") : 0.0,
                    document.getString("periodoLiquidacion") != null ? document.getString("periodoLiquidacion") : "",});
            }

            tabla.setModel(model);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(1).setMaxWidth(0);
            tabla.getColumnModel().getColumn(1).setMinWidth(0);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(3).setMaxWidth(0);
            tabla.getColumnModel().getColumn(3).setMinWidth(0);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(5).setMaxWidth(0);
            tabla.getColumnModel().getColumn(5).setMinWidth(0);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(7).setMaxWidth(0);
            tabla.getColumnModel().getColumn(7).setMinWidth(0);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(0);
        });
    }

    /** elimina detalle
     *
     * @param idDoc id detalle
     */
    public void eliminarDetalleLiquidacion(String idDoc) {

        try {
            detalleLiquidacionProvider.eliminarModeloDetalleLiquidacion(idDoc);
            JOptionPane.showMessageDialog(null, "Detalle Liquidación eliminado exitosamente");
            //System.out.println("Detalle Liquidación eliminado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            //System.out.println("Error al eliminar el Detalle de Liquidación: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al eliminar el Detalle de Liquidación: " + ex.getMessage());
        }
    }

    /** carga seleccion inmueble
     *
     * @param box componente
     * @throws InterruptedException  error
     * @throws ExecutionException error
     * @throws IOException error
     */
    public static void cargarComboInmueble(JComboBox<String> box) throws InterruptedException, ExecutionException, IOException {
        Firestore db = Conexion.getConnection();
        CollectionReference proveedores = db.collection("Inmueble");
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Seleccione uno");

        proveedores.orderBy("domicilio", Query.Direction.ASCENDING)//Esto trae los elementos ordenados de forma ascendente
                .addSnapshotListener((querySnapshot, e) -> {
                    if (e != null) {
                        System.err.println("Ocurrió un error al escuchar los cambios de Inmueble: " + e);
                        return;
                    }
                    modelo.removeAllElements();
                    modelo.addElement("Seleccione uno");
                    for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                        modelo.addElement(document.getString("domicilio"));
                    }
                    box.setModel(modelo);
                });
    }

    /** Seleccion de vecino
     *
     * @param box componente
     * @throws InterruptedException error
     * @throws ExecutionException error
     * @throws IOException error
     */
    public static void cargarComboVecino(JComboBox<String> box) throws InterruptedException, ExecutionException, IOException {
        Firestore db = Conexion.getConnection();
        CollectionReference proveedores = db.collection("Vecino");
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Seleccione uno");

        proveedores.orderBy("usuario", Query.Direction.ASCENDING)//Esto trae los elementos ordenados de forma ascendente
                .addSnapshotListener((querySnapshot, e) -> {
                    if (e != null) {
                        System.err.println("Ocurrió un error al escuchar los cambios de Vecino: " + e);
                        return;
                    }
                    modelo.removeAllElements();
                    modelo.addElement("Seleccione uno");
                    for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                        modelo.addElement(document.getString("usuario"));
                    }
                    box.setModel(modelo);
                });
    }

    /** Seleccion liquidacion
     *
     * @param box componente
     * @throws InterruptedException error
     * @throws ExecutionException error
     * @throws IOException error
     */
    public static void cargarComboLiquidacion(JComboBox<String> box) throws InterruptedException, ExecutionException, IOException {
        Firestore db = Conexion.getConnection();
        CollectionReference proveedores = db.collection("Liquidacion");
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Seleccione uno");

        proveedores.orderBy("periodoTrimestral", Query.Direction.ASCENDING)//Esto trae los elementos ordenados de forma ascendente
                .addSnapshotListener((querySnapshot, e) -> {
                    if (e != null) {
                        System.err.println("Ocurrió un error al escuchar los cambios de proveedores: " + e);
                        return;
                    }
                    modelo.removeAllElements();
                    modelo.addElement("Seleccione uno");
                    for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                        modelo.addElement(document.getString("periodoTrimestral"));
                    }
                    box.setModel(modelo);
                });
    }

    /** Seleccion de servicio
     *
     * @param box componenet 
     * @throws InterruptedException error
     * @throws ExecutionException error
     * @throws IOException error
     */
    public static void cargarComboServicio(JComboBox<String> box) throws InterruptedException, ExecutionException, IOException {
        Firestore db = Conexion.getConnection();
        CollectionReference proveedores = db.collection("Servicio");
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Seleccione uno");

        proveedores.orderBy("nombre", Query.Direction.ASCENDING)//Esto trae los elementos ordenados de forma ascendente
                .addSnapshotListener((querySnapshot, e) -> {
                    if (e != null) {
                        System.err.println("Ocurrió un error al escuchar los cambios de servicios: " + e);
                        return;
                    }
                    modelo.removeAllElements();
                    modelo.addElement("Seleccione uno");
                    for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                        modelo.addElement(document.getString("nombre"));
                    }
                    box.setModel(modelo);
                });
    }

    /** comprueba vecino
     *
     * @param comboBox componenete
     * @return devuelve falso
     */
    public boolean verificarSeleccionVecino(JComboBox comboBox) {
        if (!verificarSeleccion(comboBox)) {
            JOptionPane.showMessageDialog(null, "Seleccione una opción en Vecino");
            return false;
        }
        return true;
    }

    /** comprueba Seleccion de inmueble
     *
     * @param comboBox componenet
     * @return falso
     */
    public boolean verificarSeleccionInmueble(JComboBox comboBox) {
        if (!verificarSeleccion(comboBox)) {
            JOptionPane.showMessageDialog(null, "Seleccione una opción en Inmueble");
            return false;
        }
        return true;
    }

    /** verifica liquidacion
     *
     * @param comboBox componente
     * @return falso
     */
    public boolean verificarSeleccionLiquidacion(JComboBox comboBox) {
        if (!verificarSeleccion(comboBox)) {
            JOptionPane.showMessageDialog(null, "Seleccione una opción en Liquidación");
            return false;
        }
        return true;
    }

    /** verifica servicio
     *
     * @param comboBox componente
     * @return falso
     */
    public boolean verificarSeleccionServicio(JComboBox comboBox) {
        if (!verificarSeleccion(comboBox)) {
            JOptionPane.showMessageDialog(null, "Seleccione una opción en Servicio");
            return false;
        }
        return true;
    }

    /** Carga la suscripcion
     *
     * @param box componnete
     * @throws InterruptedException error
     * @throws ExecutionException error
     * @throws IOException error
     */
    public static void cargarComboSuscripcion(JComboBox<String> box) throws InterruptedException, ExecutionException, IOException {
        Firestore db = Conexion.getConnection();
        CollectionReference suscripciones = db.collection("SuscripcionServicio");
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Seleccione uno");
        suscripciones.addSnapshotListener((querySnapshot, e) -> {
            if (e != null) {
                System.err.println("Ocurrió un error al escuchar los cambios de suscripción: " + e);
                return;
            }
            modelo.removeAllElements();
            modelo.addElement("Seleccione uno");
            List<String> elementos = new ArrayList<>();
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                String usuario = document.getString("usuario");
                String id = document.getId();
                String elemento = usuario + " - " + id;
                elementos.add(elemento);
            }
            // Eliminar duplicados antes de agregarlos al modelo
            Set<String> elementosUnicos = new HashSet<>(elementos);
            for (String elemento : elementosUnicos) {
                modelo.addElement(elemento);
            }
            box.setModel(modelo);
        });
    }

    /** verifica suscripcion
     *
     * @param comboBox componente
     * @return falso
     */
    public boolean verificarSeleccionSuscripcion(JComboBox comboBox) {
        if (!verificarSeleccion(comboBox)) {
            JOptionPane.showMessageDialog(null, "Seleccione una opción en Suscripción");
            return false;
        }
        return true;
    }

    /** Carga tabla liquidacion
     *
     * @param tabla tabla
     * @param idUsuario usuario
     * @param periodoLiquidacion periodo
     */
    public static void cargarTablaByIdUserAndByLiquidacion(JTable tabla, String idUsuario, String periodoLiquidacion) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("DetalleLiquidacion");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre clave");
        model.addColumn("Nombre del Vecino");
        model.addColumn("ID Vecino");
        model.addColumn("Suscripción Servicio");
        model.addColumn("ID Servicio");
        model.addColumn("Nombre del Inmueble");
        model.addColumn("ID Inmueble");

        model.addColumn("Concepto");
        model.addColumn("Importe Final");
        model.addColumn("Periodo de Liquidación");

        Query query = gastos.whereEqualTo("idUsuario", idUsuario)
                .whereEqualTo("periodoLiquidacion", periodoLiquidacion);

        query.addSnapshotListener((querySnapshot, error) -> {
            if (error != null) {
                System.err.println("Error al escuchar cambios: " + error);
                return;
            }

            if (querySnapshot.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontraron documentos para el periodo de liquidación: " + periodoLiquidacion);
            }

            model.setRowCount(0); // Limpia el modelo de datos

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                // Agrega una fila al modelo de datos
                model.addRow(new Object[]{
                    document.getId(),
                    document.getString("nombre") != null ? document.getString("nombre") : "",
                    document.getString("nombreUsuario") != null ? document.getString("nombreUsuario") : "",
                    document.getString("idUsuario") != null ? document.getString("idUsuario") : "",
                    document.getString("nombreSuscripcionServicio") != null ? document.getString("nombreSuscripcionServicio") : "",
                    document.getString("idServicio") != null ? document.getString("idServicio") : "",
                    document.getString("nombreInmueble") != null ? document.getString("nombreInmueble") : "",
                    document.getString("idInmueble") != null ? document.getString("idInmueble") : "",
                    document.getString("concepto") != null ? document.getString("concepto") : "",
                    document.getDouble("importeFinal") != null ? document.getDouble("importeFinal") : 0.0,
                    document.getString("periodoLiquidacion") != null ? document.getString("periodoLiquidacion") : "",});
            }

            tabla.setModel(model);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(1).setMaxWidth(0);
            tabla.getColumnModel().getColumn(1).setMinWidth(0);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(3).setMaxWidth(0);
            tabla.getColumnModel().getColumn(3).setMinWidth(0);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(5).setMaxWidth(0);
            tabla.getColumnModel().getColumn(5).setMinWidth(0);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(7).setMaxWidth(0);
            tabla.getColumnModel().getColumn(7).setMinWidth(0);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(0);
        });
    }

    /** carga tabla inmueble
     *
     * @param tabla tabla 
     * @param idUsuario id
     * @param inmueble inmueble
     */
    public static void cargarTablaByIdUserAndByInmueble(JTable tabla, String idUsuario, String inmueble) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("DetalleLiquidacion");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre clave");
        model.addColumn("Nombre del Vecino");
        model.addColumn("ID Vecino");
        model.addColumn("Suscripción Servicio");
        model.addColumn("ID Servicio");
        model.addColumn("Nombre del Inmueble");
        model.addColumn("ID Inmueble");

        model.addColumn("Concepto");
        model.addColumn("Importe Final");
        model.addColumn("Periodo de Liquidación");

        Query query = gastos.whereEqualTo("idUsuario", idUsuario)
                .whereEqualTo("nombreInmueble", inmueble);

        query.addSnapshotListener((querySnapshot, error) -> {
            if (error != null) {
                System.err.println("Error al escuchar cambios: " + error);
                return;
            }

            if (querySnapshot.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontraron documentos para el inmueble: " + inmueble);
            }

            model.setRowCount(0); // Limpia el modelo de datos

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                // Agrega una fila al modelo de datos
                model.addRow(new Object[]{
                    document.getId(),
                    document.getString("nombre") != null ? document.getString("nombre") : "",
                    document.getString("nombreUsuario") != null ? document.getString("nombreUsuario") : "",
                    document.getString("idUsuario") != null ? document.getString("idUsuario") : "",
                    document.getString("nombreSuscripcionServicio") != null ? document.getString("nombreSuscripcionServicio") : "",
                    document.getString("idServicio") != null ? document.getString("idServicio") : "",
                    document.getString("nombreInmueble") != null ? document.getString("nombreInmueble") : "",
                    document.getString("idInmueble") != null ? document.getString("idInmueble") : "",
                    document.getString("concepto") != null ? document.getString("concepto") : "",
                    document.getDouble("importeFinal") != null ? document.getDouble("importeFinal") : 0.0,
                    document.getString("periodoLiquidacion") != null ? document.getString("periodoLiquidacion") : "",});
            }

            tabla.setModel(model);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(1).setMaxWidth(0);
            tabla.getColumnModel().getColumn(1).setMinWidth(0);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(3).setMaxWidth(0);
            tabla.getColumnModel().getColumn(3).setMinWidth(0);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(5).setMaxWidth(0);
            tabla.getColumnModel().getColumn(5).setMinWidth(0);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(7).setMaxWidth(0);
            tabla.getColumnModel().getColumn(7).setMinWidth(0);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(0);
        });
    }

    /** carga tabla vecino
     *
     * @param tabla tabla 
     * @param idUsuario id
     * @param vecino vecino
     */
    public static void cargarTablaByIdUserAndByVecino(JTable tabla, String idUsuario, String vecino) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("DetalleLiquidacion");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre clave");
        model.addColumn("Nombre del Vecino");
        model.addColumn("ID Vecino");
        model.addColumn("Suscripción Servicio");
        model.addColumn("ID Servicio");
        model.addColumn("Nombre del Inmueble");
        model.addColumn("ID Inmueble");

        model.addColumn("Concepto");
        model.addColumn("Importe Final");
        model.addColumn("Periodo de Liquidación");

        Query query = gastos.whereEqualTo("idUsuario", idUsuario)
                .whereEqualTo("nombreUsuario", vecino);

        query.addSnapshotListener((querySnapshot, error) -> {
            if (error != null) {
                System.err.println("Error al escuchar cambios: " + error);
                return;
            }

            if (querySnapshot.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontraron documentos para el vecino: " + vecino);
            }

            model.setRowCount(0); // Limpia el modelo de datos

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                // Agrega una fila al modelo de datos
                model.addRow(new Object[]{
                    document.getId(),
                    document.getString("nombre") != null ? document.getString("nombre") : "",
                    document.getString("nombreUsuario") != null ? document.getString("nombreUsuario") : "",
                    document.getString("idUsuario") != null ? document.getString("idUsuario") : "",
                    document.getString("nombreSuscripcionServicio") != null ? document.getString("nombreSuscripcionServicio") : "",
                    document.getString("idServicio") != null ? document.getString("idServicio") : "",
                    document.getString("nombreInmueble") != null ? document.getString("nombreInmueble") : "",
                    document.getString("idInmueble") != null ? document.getString("idInmueble") : "",
                    document.getString("concepto") != null ? document.getString("concepto") : "",
                    document.getDouble("importeFinal") != null ? document.getDouble("importeFinal") : 0.0,
                    document.getString("periodoLiquidacion") != null ? document.getString("periodoLiquidacion") : "",});
            }

            tabla.setModel(model);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(1).setMaxWidth(0);
            tabla.getColumnModel().getColumn(1).setMinWidth(0);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(3).setMaxWidth(0);
            tabla.getColumnModel().getColumn(3).setMinWidth(0);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(5).setMaxWidth(0);
            tabla.getColumnModel().getColumn(5).setMinWidth(0);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(7).setMaxWidth(0);
            tabla.getColumnModel().getColumn(7).setMinWidth(0);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(0);
        });
    }

    /** carga tabla servicio
     *
     * @param tabla tabla
     * @param idUsuario usuario 
     * @param servicio servicio
     */
    public static void cargarTablaByTtlAndByServicioByServicio(JTable tabla, String idUsuario, String servicio) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("DetalleLiquidacion");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre clave");
        model.addColumn("Nombre del Vecino");
        model.addColumn("ID Vecino");
        model.addColumn("Suscripción Servicio");
        model.addColumn("ID Servicio");
        model.addColumn("Nombre del Inmueble");
        model.addColumn("ID Inmueble");

        model.addColumn("Concepto");
        model.addColumn("Importe Final");
        model.addColumn("Periodo de Liquidación");

        Query query = gastos.whereEqualTo("idUsuario", idUsuario)
                .whereEqualTo("nombreSuscripcionServicio", servicio);

        query.addSnapshotListener((querySnapshot, error) -> {
            if (error != null) {
                System.err.println("Error al escuchar cambios: " + error);
                return;
            }

            if (querySnapshot.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontraron documentos para el servicio: " + servicio);
            }

            model.setRowCount(0); // Limpia el modelo de datos

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                // Agrega una fila al modelo de datos
                model.addRow(new Object[]{
                    document.getId(),
                    document.getString("nombre") != null ? document.getString("nombre") : "",
                    document.getString("nombreUsuario") != null ? document.getString("nombreUsuario") : "",
                    document.getString("idUsuario") != null ? document.getString("idUsuario") : "",
                    document.getString("nombreSuscripcionServicio") != null ? document.getString("nombreSuscripcionServicio") : "",
                    document.getString("idServicio") != null ? document.getString("idServicio") : "",
                    document.getString("nombreInmueble") != null ? document.getString("nombreInmueble") : "",
                    document.getString("idInmueble") != null ? document.getString("idInmueble") : "",
                    document.getString("concepto") != null ? document.getString("concepto") : "",
                    document.getDouble("importeFinal") != null ? document.getDouble("importeFinal") : 0.0,
                    document.getString("periodoLiquidacion") != null ? document.getString("periodoLiquidacion") : "",});
            }

            tabla.setModel(model);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(1).setMaxWidth(0);
            tabla.getColumnModel().getColumn(1).setMinWidth(0);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(3).setMaxWidth(0);
            tabla.getColumnModel().getColumn(3).setMinWidth(0);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(5).setMaxWidth(0);
            tabla.getColumnModel().getColumn(5).setMinWidth(0);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(7).setMaxWidth(0);
            tabla.getColumnModel().getColumn(7).setMinWidth(0);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(0);
        });
    }

    /** carga tabla usuario
     *
     * @param tabla tabla 
     * @param idUsuario usuario
     */
    public void cargarTablaByIdUser(JTable tabla, String idUsuario) {
        Firestore db = Conexion.getConnection();
        CollectionReference detalles = db.collection("DetalleLiquidacion");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre clave");
        model.addColumn("Nombre del Vecino");
        model.addColumn("ID Vecino");
        model.addColumn("Suscripción Servicio");
        model.addColumn("ID Servicio");
        model.addColumn("Nombre del Inmueble");
        model.addColumn("ID Inmueble");

        model.addColumn("Concepto");
        model.addColumn("Importe Final");
        model.addColumn("Periodo de Liquidación");

        detalles.whereEqualTo("idUsuario", idUsuario)
                .addSnapshotListener((querySnapshot, error) -> {
                    if (error != null) {
                        System.err.println("Error al escuchar cambios: " + error);
                        return;
                    }

                    model.setRowCount(0); // Limpia el modelo de datos

                    for (DocumentSnapshot document : querySnapshot.getDocuments()) {

                        // Agrega una fila al modelo de datos
                        model.addRow(new Object[]{
                            document.getId(),
                            document.getString("nombre") != null ? document.getString("nombre") : "",
                            document.getString("nombreUsuario") != null ? document.getString("nombreUsuario") : "",
                            document.getString("idUsuario") != null ? document.getString("idUsuario") : "",
                            document.getString("nombreSuscripcionServicio") != null ? document.getString("nombreSuscripcionServicio") : "",
                            document.getString("idServicio") != null ? document.getString("idServicio") : "",
                            document.getString("nombreInmueble") != null ? document.getString("nombreInmueble") : "",
                            document.getString("idInmueble") != null ? document.getString("idInmueble") : "",
                            document.getString("concepto") != null ? document.getString("concepto") : "",
                            document.getDouble("importeFinal") != null ? document.getDouble("importeFinal") : "",
                            document.getString("periodoLiquidacion") != null ? document.getString("periodoLiquidacion") : "",});
                    }

                    tabla.setModel(model);
                    tabla.getColumnModel().getColumn(0).setMaxWidth(0);
                    tabla.getColumnModel().getColumn(0).setMinWidth(0);
                    tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
                    tabla.getColumnModel().getColumn(1).setMaxWidth(0);
                    tabla.getColumnModel().getColumn(1).setMinWidth(0);
                    tabla.getColumnModel().getColumn(1).setPreferredWidth(0);
                    tabla.getColumnModel().getColumn(3).setMaxWidth(0);
                    tabla.getColumnModel().getColumn(3).setMinWidth(0);
                    tabla.getColumnModel().getColumn(3).setPreferredWidth(0);
                    tabla.getColumnModel().getColumn(5).setMaxWidth(0);
                    tabla.getColumnModel().getColumn(5).setMinWidth(0);
                    tabla.getColumnModel().getColumn(5).setPreferredWidth(0);
                    tabla.getColumnModel().getColumn(7).setMaxWidth(0);
                    tabla.getColumnModel().getColumn(7).setMinWidth(0);
                    tabla.getColumnModel().getColumn(7).setPreferredWidth(0);
                });
    }

    /** Filtro
     *
     * @param table vecino
     */
    public void tablaAndFiltroVecinoSegunUsuario(JTable table) {
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

                    cargarAllTablaDetalleLiquidacionTR(table);
                    verFiltroVecino();
                    //System.out.println("Soy Administrador");

                } else if (tipoUser.equals("Vecino")) {
                    /*
                    Es Vecino
                     */

                    cargarTablaByIdUser(table, VistaLogin.idUser);
                    ocultarFiltroVecino();
                    //System.out.println("Soy Vecino");
                }
            }

        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(VistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Permite ocultar el filtro de vecino cuando el usuario no es administrador
     */
    public void ocultarFiltroVecino() {
        VistaDetalleLiquidacion.lblVecino.setVisible(false);
        VistaDetalleLiquidacion.cmbVecino.setVisible(false);
        VistaDetalleLiquidacion.btnFiltroByVecino.setVisible(false);
    }

    /**
     * Permite ver el filtro de vecino para el usuario administrador 
     */
    public void verFiltroVecino() {
        VistaDetalleLiquidacion.lblVecino.setVisible(true);
        VistaDetalleLiquidacion.cmbVecino.setVisible(true);
        VistaDetalleLiquidacion.btnFiltroByVecino.setVisible(true);
    }

    /** limpiar campos
     *
     * @param txtId id
     * @param txtNombre nombre
     * @param txtIdUsuario user
     * @param txtNombreUsuario nombre user
     * @param cmbSuscripcion suscripcion
     * @param txtImporteTotal importe
     * @param cmbLiquidacion liquidacin
     */
    public void limpiarCampos(
            JTextField txtId,
            JTextField txtNombre,
            JTextField txtIdUsuario,
            JTextField txtNombreUsuario,
            JComboBox cmbSuscripcion,
            JTextField txtImporteTotal,
            JComboBox cmbLiquidacion) {

        txtId.setText("");
        txtNombre.setText("");
        txtIdUsuario.setText("");
        txtNombre.setText("");
        cmbSuscripcion.setSelectedIndex(0);
        txtImporteTotal.setText("");
        cmbLiquidacion.setSelectedIndex(0);
    }
}
