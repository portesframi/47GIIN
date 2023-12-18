package controlador;

import providers.GastoProvider;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.database.annotations.Nullable;
import com.mycompany.viuproyecto.Conexion;
import com.toedter.calendar.JDateChooser;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.*;

/** Proyecto asignatura Proyectos de programación 
 *
 * @author frami
 */
public class GastoControler extends ComponentesGeneralControler {

    private GastoProvider gastoProvider;
    private Firestore db;

    /**
     * Realizamos la conexión
     */
    public GastoControler() {
        db = Conexion.getConnection(); //Aquí se establece la conexión con Firestore
        gastoProvider = new GastoProvider();
    }

    /** Fijamos al modelo Gasto los valores que recibimos como parametros
     * 
     * @param inporte valor
     * @param fechaRegistro fecha alta
     * @param fechaPago fecha pago
     * @param numeroComprobante documento
     * @param proveedor proveedore servicio
     * @param concepto concewpto servicio
     * @param servicio serviio
     * @param liquidacion liquidacin
     * @param idVecino vecino
     */
    public void guardarGasto(double inporte, Date fechaRegistro,
            Date fechaPago,
            String numeroComprobante,
            String proveedor,
            String concepto,
            String servicio,
            String liquidacion,
            String idVecino) {

        Gasto gasto = new Gasto();
        gasto.setImporte(inporte);
        gasto.setFechaRegistro(fechaRegistro);
        gasto.setFechaPago(fechaPago);
        gasto.setNumeroComprobante(numeroComprobante);
        gasto.setProveedor(proveedor);
        gasto.setConcepto(concepto);
        gasto.setServicio(servicio);
        gasto.setLiquidacion(liquidacion);
        gasto.setIdServicio(idVecino);

        try {
            gastoProvider.guardarModeloGasto(gasto);
            JOptionPane.showMessageDialog(null, "Gasto guardado exitosamente");
            //System.out.println("Gasto guardado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            //System.out.println("Error al guardar el gasto: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al guardar el gasto: " + ex.getMessage());
        }
    }

    /** Este metodo carga datos en tiempo real todo es gracias a addSnapshotListener 
     * que escucha los cambios en la base de datos
     * 
     * @param tabla tabla
     */
    public static void cargarTablaGastosTR(JTable tabla) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("Gastos");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Importe");
        model.addColumn("Fecha Registro");
        model.addColumn("Fecha Pago");
        model.addColumn("Número Comprobante");
        model.addColumn("Proveedor");
        model.addColumn("Concepto");
        model.addColumn("Servicio");
        model.addColumn("Liquidación");
        model.addColumn("ID Servicio");

        gastos.addSnapshotListener((@Nullable QuerySnapshot querySnapshot, @Nullable FirestoreException error) -> {
            if (error != null) {
                System.err.println("Error al escuchar cambios: " + error);
                return;
            }

            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                Timestamp fechaRegistroTimestamp = document.getTimestamp("fechaRegistro");
                Timestamp fechaPagoTimestamp = document.getTimestamp("fechaPago");
                /*
            Se debe validar que los datos traidos desde la BD no vengan nulos
            eso crea un error y por esa razón no cargará la tabla.
                 */
                Date fechaRegistro = fechaRegistroTimestamp != null ? fechaRegistroTimestamp.toDate() : null;
                Date fechaPago = fechaPagoTimestamp != null ? fechaPagoTimestamp.toDate() : null;
                /*
            Se puede sustituir los valores que se quiera mostrar en la tabla
            En el caso del valor de importe gasto se mostrará por defecto 0.0
            en caso de venir nulo.
                 */
                Double importeGasto = document.getDouble("importe") != null ? document.getDouble("importe") : 0.0;

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                /*
            En el caso de las fechas se fija por defecto un valor tal cual es 0000-00-00
            en caso de venir vacio.
                 */
                String fechaRegistroString = fechaRegistro != null ? dateFormat.format(fechaRegistro) : "0000-00-00";
                String fechaPagoString = fechaPago != null ? dateFormat.format(fechaPago) : "0000-00-00";
                model.addRow(new Object[]{
                    document.getId(),
                    /*
                En el caso de las cadenas de texto se fija una cadena sin ningún caracter
                     */
                    importeGasto,
                    fechaRegistroString,
                    fechaPagoString,
                    document.getString("numeroComprobante") != null ? document.getString("numeroComprobante") : "",
                    document.getString("proveedor") != null ? document.getString("proveedor") : "",
                    document.getString("concepto") != null ? document.getString("concepto") : "",
                    document.getString("servicio") != null ? document.getString("servicio") : "",
                    document.getString("liquidacion") != null ? document.getString("liquidacion") : "",
                    document.getString("idServicio") != null ? document.getString("idServicio") : "",});
            }

            tabla.setModel(model);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(9).setMaxWidth(0);
            tabla.getColumnModel().getColumn(9).setMinWidth(0);
            tabla.getColumnModel().getColumn(9).setPreferredWidth(0);
        });
    }

    /** Actualiza gasto
     *
     * @param idDoc id
     * @param inporte saldo
     * @param fechaRegistro registro
     * @param fechaPago pago
     * @param numeroComprobante documento
     * @param proveedor proveedor
     * @param concepto concepto
     * @param servicio seervicio
     * @param liquidacion liquidacion
     * @param idServicio id 
     */
    public void actualizarGasto(String idDoc,
            double inporte,
            Date fechaRegistro,
            Date fechaPago,
            String numeroComprobante,
            String proveedor,
            String concepto,
            String servicio,
            String liquidacion,
            String idServicio) {

        Gasto gasto = new Gasto();
        gasto.setImporte(inporte);
        gasto.setFechaRegistro(fechaRegistro);
        gasto.setFechaPago(fechaPago);
        gasto.setNumeroComprobante(numeroComprobante);
        gasto.setProveedor(proveedor);
        gasto.setConcepto(concepto);
        gasto.setServicio(servicio);
        gasto.setLiquidacion(liquidacion);
        gasto.setIdServicio(idServicio);

        try {
            gastoProvider.actualizarModeloGasto(idDoc, gasto);
            JOptionPane.showMessageDialog(null, "Gasto actualizado exitosamente");
            System.out.println("Gasto actualizado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el gasto: " + ex.getMessage());
            //System.out.println("Error al actualizar el gasto: " + ex.getMessage());
        }
    }

    /** Seleccion de concepto
     *
     * @param box componente
     * @throws InterruptedException error
     * @throws ExecutionException error
     * @throws IOException error
     */
    public static void cargarComboConcepto(JComboBox<String> box) throws InterruptedException, ExecutionException, IOException {
        Firestore db = Conexion.getConnection();
        CollectionReference proveedores = db.collection("Concepto");
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Seleccione uno");
        proveedores.addSnapshotListener((querySnapshot, e) -> {
            if (e != null) {
                System.err.println("Ocurrió un error al escuchar los cambios de proveedores: " + e);
                return;
            }
            modelo.removeAllElements();
            modelo.addElement("Seleccione uno");
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                modelo.addElement(document.getString("nombreConcepto"));
            }
            box.setModel(modelo);
        });
    }

    /** Seleccion de proveedor de servicio
     *
     * @param box componente
     * @throws InterruptedException error
     * @throws ExecutionException error
     * @throws IOException error
     */
    public static void cargarComboProveedor(JComboBox<String> box) throws InterruptedException, ExecutionException, IOException {
        Firestore db = Conexion.getConnection();
        CollectionReference proveedores = db.collection("Proveedor");
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Seleccione uno");
        proveedores.addSnapshotListener((querySnapshot, e) -> {
            if (e != null) {
                System.err.println("Ocurrió un error al escuchar los cambios de proveedores: " + e);
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
                System.err.println("Ocurrió un error al escuchar los cambios de proveedores: " + e);
                return;
            }
            modelo.removeAllElements();
            modelo.addElement("Seleccione uno");
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                modelo.addElement(document.getString("nombre")+
                        " - "+document.getId());
            }
            box.setModel(modelo);
        });
    }

    /** Seleccion de liquidacion
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

    /** eliminar gasto
     *
     * @param idDoc id
     */
    public void eliminarGasto(String idDoc) {
        try {
            gastoProvider.eliminarModeloGasto(idDoc);
            JOptionPane.showMessageDialog(null, "Gasto eliminado exitosamente");
            //System.out.println("Gasto eliminado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el gasto: " + ex.getMessage());
            // System.out.println("Error al eliminar el gasto: " + ex.getMessage());
        }
    }

    /** Consulta 
     *
     * @param id id
     * @return devuelve inmueble
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

    /** Seleccion proveedor
     *
     * @param comboBox componenete
     * @return devuelve valor
     */
    public boolean verificarSeleccionProveedor(JComboBox comboBox) {
        if (!verificarSeleccion(comboBox)) {
            JOptionPane.showMessageDialog(null, "Seleccione una opción en Proveedor");
            return false;
        }
        return true;
    }

    /** Seleccion concepto
     *
     * @param comboBox componente
     * @return devuelve valor
     */
    public boolean verificarSeleccionConcepto(JComboBox comboBox) {
        if (!verificarSeleccion(comboBox)) {
            JOptionPane.showMessageDialog(null, "Seleccione una opción en Concepto");
            return false;
        }
        return true;
    }

    /** Seleccion sercicio
     *
     * @param comboBox componenete
     * @return devuelve valor
     */
    public boolean verificarSeleccionServicio(JComboBox comboBox) {
        if (!verificarSeleccion(comboBox)) {
            JOptionPane.showMessageDialog(null, "Seleccione una opción en Servicio");
            return false;
        }
        return true;
    }

    /** Seleccion de liquidacion
     *
     * @param comboBox componenete
     * @return devuelve
     */
    public boolean verificarSeleccionLiquidacion(JComboBox comboBox) {
        if (!verificarSeleccion(comboBox)) {
            JOptionPane.showMessageDialog(null, "Seleccione una opción en Liquidación");
            return false;
        }
        return true;
    }

    /** Seleccion de liquidacion
     *
     * @param comboBox componenete
     * @return devuelve valor
     */
    public boolean verificarSeleccionPeriodoLiquidacion(JComboBox comboBox) {
        if (!verificarSeleccion(comboBox)) {
            JOptionPane.showMessageDialog(null, "Seleccione una opción en Periodo Liquidación");
            return false;
        }
        return true;
    }


    /** Limpia campos de la vista
     *
     * @param txtImporte importe
     * @param txtNroComprobante nº
     * @param jFechaGasto fecha gasto
     * @param jFechaPago fecha pago
     * @param cmbProveedor proveedore de servicio
     * @param cmbConcepto concepto de servico 
     * @param cmbLiquidacion liquidacion
     */
    public void limpiarCampos(
            JTextField txtImporte,
            JTextField txtNroComprobante,
            JDateChooser jFechaGasto,
            JDateChooser jFechaPago,
            JComboBox cmbProveedor,
            JComboBox cmbConcepto,
            JComboBox cmbLiquidacion) {

        txtImporte.setText("");
        txtNroComprobante.setText("");
        jFechaGasto.setDate(null);
        jFechaPago.setDate(null);
        cmbProveedor.setSelectedIndex(0);
        cmbConcepto.setSelectedIndex(0);
        cmbLiquidacion.setSelectedIndex(0);
    }
}
