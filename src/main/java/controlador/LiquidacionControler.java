package controlador;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldPath;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.database.annotations.Nullable;
import com.mycompany.viuproyecto.Conexion;
import com.toedter.calendar.JDateChooser;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Liquidacion;
import providers.LiquidacionProvider;
import vista.VistaLiquidacion;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */
public class LiquidacionControler extends ComponentesGeneralControler {

    private LiquidacionProvider liquidacionProvider;
    private Firestore db;

    /**
     * Metodo para obtener el dato del atributo importe total.
     */
    public static double importeFinalTotal;

    /**
     * Realizamos la conexión
     */
    public LiquidacionControler() {
        db = Conexion.getConnection(); //Aquí se establece la conexión con Firestore
        liquidacionProvider = new LiquidacionProvider();
    }

    /** Fijamos al modelo Liquidacion los valores que recibimos como parametros
     * 
     * @param fechaInicio inicio liq
     * @param fechaFin fin liq
     * @param detalle detalles
     * @param periodoTrimestral periodo
     */
    public void guardarLiquidacion(
            Date fechaInicio,
            Date fechaFin,
            String detalle,
            String periodoTrimestral) {

        Liquidacion l = new Liquidacion();
        l.setFechaIncio(fechaInicio);
        l.setFechaFin(fechaFin);
        l.setDetalle(detalle);
        l.setPeriodoTrimestral(periodoTrimestral);

        try {
            liquidacionProvider.guardarModelLiquidacion(l);
            JOptionPane.showMessageDialog(null, "Liquidación guardada exitosamente");
            //System.out.println("Liquidación guardado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            //System.out.println("Error al guardar el liquidación: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al guardar la liquidación: " + ex.getMessage());
        }
    }

    /**Este metodo carga datos en tiempo real todo es gracias a addSnapshotListener 
     * que escucha los cambios en la base de datos
     * 
     * @param tabla datos
     */
    public static void cargarTablaLiquidacionTR(JTable tabla) {
        Firestore db = Conexion.getConnection();
        CollectionReference liquidacion = db.collection("Liquidacion");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Fecha inicio");
        model.addColumn("Fecha Fin");
        model.addColumn("Detalle");
        model.addColumn("Periodo Trimestral");

        liquidacion.addSnapshotListener((@Nullable QuerySnapshot querySnapshot, @Nullable FirestoreException error) -> {
            if (error != null) {
                System.err.println("Error al escuchar cambios: " + error);
                return;
            }

            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                Timestamp fechaInicioTimestamp = document.getTimestamp("fechaInicio");
                Timestamp fechaFinTimestamp = document.getTimestamp("fechaFin");
                /*
            Se debe validar que los datos traidos desde la BD no vengan nulos
            eso crea un error y por esa razón no cargará la tabla.
                 */
                Date fechaInicio = fechaInicioTimestamp != null ? fechaInicioTimestamp.toDate() : null;
                Date fechaFin = fechaFinTimestamp != null ? fechaFinTimestamp.toDate() : null;
                /*
            Se puede sustituir los valores que se quiera mostrar en la tabla
            En el caso del valor de importe gasto se mostrará por defecto 0.0
            en caso de venir nulo.
                 */
                String detalle = document.getString("detalle") != null ? document.getString("detalle") : "";
                String periodoTrimestral = document.getString("periodoTrimestral") != null ? document.getString("periodoTrimestral") : "";

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                /*
            En el caso de las fechas se fija por defecto un valor tal cual es 0000-00-00
            en caso de venir vacio.
                 */
                String fechaInicioString = fechaInicio != null ? dateFormat.format(fechaInicio) : "0000-00-00";
                String fechaFinString = fechaFin != null ? dateFormat.format(fechaFin) : "0000-00-00";
                model.addRow(new Object[]{
                    document.getId(),
                    /*
                En el caso de las cadenas de texto se fija una cadena sin ningún caracter
                     */
                    fechaInicioString,
                    fechaFinString,
                    detalle,
                    periodoTrimestral
                });
            }

            tabla.setModel(model);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
        });
    }

    /** Actualiza la liquidacion
     *
     * @param idDoc id
     * @param fechaInicio inicio
     * @param fechaFin fin
     * @param detalle detalle
     * @param periodoTrimestral periodo
     */
    public void actualizarLiquidacion(
            String idDoc,
            Date fechaInicio,
            Date fechaFin,
            String detalle,
            String periodoTrimestral) {

        Liquidacion l = new Liquidacion();
        l.setFechaIncio(fechaInicio);
        l.setFechaFin(fechaFin);
        l.setDetalle(detalle);
        l.setPeriodoTrimestral(periodoTrimestral);

        try {
            liquidacionProvider.actualizarModeloLiquidacion(idDoc, l);
            JOptionPane.showMessageDialog(null, "Liquidación actualizado exitosamente");
            //System.out.println("Liquidación actualizado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la liquidación: " + ex.getMessage());
            //System.out.println("Error al actualizar el liquidación: " + ex.getMessage());
        }
    }

    /** Elimina la liquidacion
     *
     * @param idDoc id
     */
    public void eliminarLiquidacion(String idDoc) {
        try {
            liquidacionProvider.eliminarModeloLiquidacion(idDoc);
            JOptionPane.showMessageDialog(null, "Liquidación eliminado exitosamente");
            //System.out.println("Liquidación eliminado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la liquidación: " + ex.getMessage());
            // System.out.println("Error al eliminar la liquidación: " + ex.getMessage());
        }
    }

    /** Selecciona periodo
     * 
     * @param comboBox compoente
     * @return devuelve valor
     */
    public boolean verificarSeleccionPeriodoLiquidacion(JComboBox comboBox) {
        if (!verificarSeleccion(comboBox)) {
            JOptionPane.showMessageDialog(null, "Seleccione una opción en Periodo de Liquidación");
            return false;
        }
        return true;
    }
 
    /** Selecicon gastos
     *
     * @param comboBox componenete
     * @return devuelve valor
     */
    public boolean verificarSeleccionGastos(JComboBox comboBox) {
        if (!verificarSeleccion(comboBox)) {
            JOptionPane.showMessageDialog(null, "Seleccione una opción en Gastos");
            return false;
        }
        return true;
    }

    /** Seleccion periodo
     *
     * @param comboBox componente
     * @return devuelve valor
     */
    public boolean verificarSeleccionPeriodoLiquidacionVacio(JComboBox comboBox) {
        if (comboBox.getItemCount() == 0) {
            JOptionPane.showMessageDialog(null, "El Periodo de Liquidación está vacío");
            return false;
        }
        return true;
    }

    /** Selecicon suscripcion
     *
     * @param comboBox componente
     * @return valor
     */
    public boolean verificarSeleccionSuscripcion(JComboBox comboBox) {
        if (!verificarSeleccion(comboBox)) {
            JOptionPane.showMessageDialog(null, "Seleccione una opción en Suscripción");
            return false;
        }
        return true;
    }

    /** Consulta el importe para el peridodo 
     *
     * @param liquidacion liquidacion
     * @param idVecino vecino de la liquidacion
     * @return valor
     */
    public CompletableFuture<Double> consultarImportePorPeriodoLiquidacion(String liquidacion,
            String idVecino) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("Gastos");

        System.out.println("Período de liquidación: " + liquidacion);

        CompletableFuture<Double> future = new CompletableFuture<>();

        gastos.whereEqualTo("liquidacion", liquidacion).
                whereEqualTo("idVecino", idVecino)
                .addSnapshotListener((querySnapshot, error) -> {
                    if (error != null) {
                        System.err.println("Error al realizar la consulta: " + error);
                        future.completeExceptionally(error);
                        return;
                    }

                    double totalImporte = 0.0;

                    for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                        double importe = document.getDouble("importe");
                        totalImporte += importe;
                    }

                    if (querySnapshot.isEmpty()) {
                        System.out.println("No se encontraron documentos para el periodo de liquidación: " + liquidacion);
                    } else {
                        System.out.println("Total importe para el periodo de liquidación " + liquidacion + ": " + totalImporte);
                    }

                    future.complete(totalImporte);
                });

        return future;
    }

    /** Conculta de los gastos para un periodo de liquidacion
     * 
     * @param periodoLiquidacion periodos
     * @return valor
     */
    public CompletableFuture<List<Map<String, Object>>> consultarDatosGastosPorPeriodoLiquidacion(String periodoLiquidacion) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastosCollection = db.collection("Gastos");

        CompletableFuture<List<Map<String, Object>>> datosGastos = new CompletableFuture<>();

        Query query = gastosCollection.whereEqualTo("liquidacion", periodoLiquidacion);

        query.addSnapshotListener((querySnapshot, error) -> {
            if (error != null) {
                System.err.println("Error al realizar la consulta por periodo de liquidación: " + error);
                datosGastos.completeExceptionally(error);
                return;
            }

            List<Map<String, Object>> datos = new ArrayList<>();
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                datos.add(document.getData());
            }

            if (!datos.isEmpty()) {
                datosGastos.complete(datos);
            } else {
                datosGastos.completeExceptionally(new RuntimeException("No se encontró ningún documento"));
            }
        });

        return datosGastos;
    }

    /** Conculta lo datos del servico y si tiene suscripcion
     *
     * @param idServicio id
     * @return valor
     */
    public CompletableFuture<List<Map<String, Object>>> consultarDatosSuscripcionServicioPorIdServicio(String idServicio) {
        Firestore db = Conexion.getConnection();
        CollectionReference suscripcionServicioCollection = db.collection("SuscripcionServicio");

        CompletableFuture<List<Map<String, Object>>> datosSuscripcionServicio = new CompletableFuture<>();

        Query query = suscripcionServicioCollection.whereEqualTo("idServicio", idServicio);

        query.addSnapshotListener((querySnapshot, error) -> {
            if (error != null) {
                System.err.println("Error al realizar la consulta por idServicio: " + error);
                datosSuscripcionServicio.completeExceptionally(error);
                return;
            }

            List<Map<String, Object>> datos = new ArrayList<>();
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                datos.add(document.getData());
            }

            if (!datos.isEmpty()) {
                datosSuscripcionServicio.complete(datos);
            } else {
                datosSuscripcionServicio.completeExceptionally(new RuntimeException("No se encontró ningún documento"));
            }
        });

        return datosSuscripcionServicio;
    }

    /** Concuta los datos del inmubele suscrito
     *
     * @param id id
     * @return valor
     */
    public CompletableFuture<Map<String, Object>> consultarDatosInmueblePorId(String id) {
        Firestore db = Conexion.getConnection();
        DocumentReference docRef = db.collection("Inmueble").document(id);

        CompletableFuture<Map<String, Object>> datosInmueble = new CompletableFuture<>();

        docRef.addSnapshotListener((snapshot, error) -> {
            if (error != null) {
                System.err.println("Error al realizar la consulta por ID: " + error);
                datosInmueble.completeExceptionally(error);
                return;
            }

            if (snapshot.exists()) {
                Map<String, Object> datos = snapshot.getData();
                datosInmueble.complete(datos);
            } else {
                System.out.println("No se encontró el documento con el ID: " + id);
                datosInmueble.completeExceptionally(new RuntimeException("No se encontró el documento"));
            }
        });

        return datosInmueble;
    }

    /** Consulta los datos del usuariuo suscrito
     *
     * @param id id
     * @return valor
     */
    public CompletableFuture<Map<String, Object>> consultarDatosUsuarioPorId(String id) {
        Firestore db = Conexion.getConnection();
        DocumentReference docRef = db.collection("Usuario").document(id);

        CompletableFuture<Map<String, Object>> datosInmueble = new CompletableFuture<>();

        docRef.addSnapshotListener((snapshot, error) -> {
            if (error != null) {
                System.err.println("Error al realizar la consulta por ID: " + error);
                datosInmueble.completeExceptionally(error);
                return;
            }

            if (snapshot.exists()) {
                Map<String, Object> datos = snapshot.getData();
                datosInmueble.complete(datos);
            } else {
                System.out.println("No se encontró el documento con el ID: " + id);
                datosInmueble.completeExceptionally(new RuntimeException("No se encontró el documento"));
            }
        });

        return datosInmueble;
    }

    /** consulta datos del servicio
     *
     * @param id id
     * @return valor
     */
    public CompletableFuture<Map<String, Object>> consultarDatosServicioPorId(String id) {
        Firestore db = Conexion.getConnection();
        DocumentReference docRef = db.collection("Servicio").document(id);

        CompletableFuture<Map<String, Object>> datosServicio = new CompletableFuture<>();

        docRef.addSnapshotListener((snapshot, error) -> {
            if (error != null) {
                System.err.println("Error al realizar la consulta por ID: " + error);
                datosServicio.completeExceptionally(error);
                return;
            }

            if (snapshot.exists()) {
                Map<String, Object> datos = snapshot.getData();
                datosServicio.complete(datos);
            } else {
                System.out.println("No se encontró el documento con el ID: " + id);
                datosServicio.completeExceptionally(new RuntimeException("No se encontró el documento"));
            }
        });

        return datosServicio;
    }

    /** relaciona el servcicio con la liquidacion
     *
     * @param liquidacion liquidacin
     * @return valor
     */
    public CompletableFuture<String> obtenerServiciosPorPeriodoLiquidacion(String liquidacion) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("Gastos");

        System.out.println("Período de liquidación: " + liquidacion);

        CompletableFuture<String> future = new CompletableFuture<>();

        gastos.whereEqualTo("liquidacion", liquidacion)
                .addSnapshotListener((querySnapshot, error) -> {
                    if (error != null) {
                        System.err.println("Error al realizar la consulta: " + error);
                        future.completeExceptionally(error);
                        return;
                    }

                    List<String> servicios = new ArrayList<>();

                    for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                        String servicio = document.getString("servicio");
                        servicios.add(servicio);
                    }

                    if (querySnapshot.isEmpty()) {
                        System.out.println("No se encontraron documentos para el periodo de liquidación: " + liquidacion);
                    } else {
                        String serviciosString = String.join(", ", servicios);
                        //System.out.println("Servicios para el periodo de liquidación " + liquidacion + ": " + serviciosString);
                        future.complete(serviciosString);
                    }
                });

        return future;
    }

    /** Selecicon de suscripcion
     *
     * @param box componenete
     * @throws InterruptedException error
     * @throws ExecutionException error
     * @throws IOException error
     */
    public static void cargarComboIdsSuscripcion(JComboBox<String> box) throws InterruptedException, ExecutionException, IOException {
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
                String idDoc = document.getId();
//                String usuario = document.getString("usuario");
//                String id = document.getString("idUsuario");
                String elemento = idDoc;
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

    /** Seleccion de gastos
     *
     * @param box componente
     * @throws InterruptedException error
     * @throws ExecutionException error
     * @throws IOException error
     */
    public static void cargarComboIdsGastos(JComboBox<String> box) throws InterruptedException, ExecutionException, IOException {
        Firestore db = Conexion.getConnection();
        CollectionReference suscripciones = db.collection("Gastos");
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
                String idDoc = document.getId();
//                String usuario = document.getString("usuario");
//                String id = document.getString("idUsuario");
                String elemento = idDoc;
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

    /** Seleccion deliquidacion
     *
     * @param box compomnenete
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

    /** Limpia campos de la vista
     *
     * @param txtId id
     * @param mFechaInicio fecha
     * @param mFechFin fecha
     * @param txtDetalle detalla
     * @param cmbPeriodoTrimestral periodo
     */
    public void limpiarCamposLiquidacion(
            JTextField txtId,
            JDateChooser mFechaInicio,
            JDateChooser mFechFin,
            JTextArea txtDetalle,
            JComboBox cmbPeriodoTrimestral) {

        txtId.setText("");
        mFechaInicio.setDate(null);
        mFechFin.setDate(null);
        txtDetalle.setText("");
        cmbPeriodoTrimestral.setSelectedIndex(0);
    }

    /** Limpia campos de la vista
     *
     * @param txtIdUser usuario
     * @param txtNombre nombre
     * @param txtNombreUser user
     * @param txtImporteTotal importe
     * @param mFechaInicio fecha
     * @param mFechFin fecha 
     * @param txtDetalle detalle
     * @param cmbLiquidacion liq
     * @param cmbSuscripcion suscripcion
     */
    public void limpiarCamposDetalleLiquidacion(
            JTextField txtIdUser,
            JTextField txtNombre,
            JTextField txtNombreUser,
            JTextField txtImporteTotal,
            JDateChooser mFechaInicio,
            JDateChooser mFechFin,
            JTextArea txtDetalle,
            JComboBox cmbLiquidacion,
            JComboBox cmbSuscripcion
    ) {

        txtIdUser.setText("");
        txtNombre.setText("");
        txtNombreUser.setText("");
        txtImporteTotal.setText("");
        mFechaInicio.setDate(null);
        mFechFin.setDate(null);
        txtDetalle.setText("");
        cmbLiquidacion.setSelectedIndex(0);
        cmbSuscripcion.setSelectedIndex(0);
    }

    /**
     * Bloquea una liquidacion calculada
     */
    public void bloquearLiquidacion() {
        VistaLiquidacion.jFechaInicio.setEnabled(false);
        VistaLiquidacion.jFechaFin.setEnabled(false);
        //VistaLiquidacion.cmbLiquidacion.setEnabled(false);
        VistaLiquidacion.txtId.setEnabled(false);
        VistaLiquidacion.txtAreaDetalle.setEnabled(false);
        VistaLiquidacion.btnCrear.setEnabled(false);
        VistaLiquidacion.btnModificar.setEnabled(false);
        VistaLiquidacion.btnEliminar.setEnabled(false);
        VistaLiquidacion.btnLimpiar.setEnabled(false);
    }

    /**
     * Desbloquea una liquidacion calculada
     */
    public void desbloquearLiquidacion() {
        VistaLiquidacion.jFechaInicio.setEnabled(true);
        VistaLiquidacion.jFechaFin.setEnabled(true);
        //VistaLiquidacion.cmbLiquidacion.setEnabled(true);
        VistaLiquidacion.txtId.setEnabled(true);
        VistaLiquidacion.txtAreaDetalle.setEnabled(true);
        VistaLiquidacion.btnCrear.setEnabled(true);
        VistaLiquidacion.btnModificar.setEnabled(true);
        VistaLiquidacion.btnEliminar.setEnabled(true);
        VistaLiquidacion.btnLimpiar.setEnabled(true);
    }

    /**
     * Bloquea el detalle liquidacion
     */
    public void bloquearDetalleLiquidacion() {
        VistaLiquidacion.btnCrearDetalleLiq.setEnabled(false);
    }

    /**
     * Desbloquea el detalle liquidacion
     */
    public void desbloquearDetalleLiquidacion() {
        VistaLiquidacion.btnCrearDetalleLiq.setEnabled(true);
    }
}
