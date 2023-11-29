package controlador;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.database.annotations.Nullable;
import com.mycompany.viuproyecto.Conexion;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Inmueble;
import modelo.InmuebleApartamento;
import modelo.InmuebleCasa;
import providers.InmuebleApartamentoProvider;
import providers.InmuebleCasaProvider;
import providers.InmuebleProvider;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */
public class InmuebleControler extends ComponentesGeneralControler {

    private InmuebleApartamentoProvider inmuebleApartamentoProvider;
    private InmuebleCasaProvider inmuebleCasaProvider;
    private Firestore db;

    /**
     * Realizamos la conexión
     */
    public InmuebleControler() {
        db = Conexion.getConnection(); //Aquí se establece la conexión con Firestore
        inmuebleApartamentoProvider = new InmuebleApartamentoProvider();
        inmuebleCasaProvider = new InmuebleCasaProvider();
    }

    /** Fijamos al modelo Inmueble los valores que recibimos como parametros
     * 
     * @param domicilio direcciin
     * @param numero nº
     * @param vecino dueño
     * @param idVecino id
     * @param metros tamaño
     * @param piso piso
     * @param puerta pta
     * @param parcela parcela para casa
     * @param esInmuebleCasa tipo
     */
    public void guardarInmueble(
            String domicilio,
            long numero,
            String vecino,
            String idVecino,
            float metros,
            String piso,
            String puerta,
            String parcela,
            boolean esInmuebleCasa) {

        InmuebleApartamento inmuebleApartamento = new InmuebleApartamento();
        inmuebleApartamento.setDomicilio(domicilio);
        inmuebleApartamento.setNumero(numero);
        inmuebleApartamento.setVecino(vecino);
        inmuebleApartamento.setIdVecino(idVecino);
        inmuebleApartamento.setMetros(metros);
        inmuebleApartamento.setPiso(piso);
        inmuebleApartamento.setPuerta(puerta);

        InmuebleCasa inmuebleCasa = new InmuebleCasa();
        inmuebleCasa.setDomicilio(domicilio);
        inmuebleCasa.setNumero(numero);
        inmuebleCasa.setVecino(vecino);
        inmuebleCasa.setIdVecino(idVecino);
        inmuebleCasa.setMetros(metros);
        inmuebleCasa.setParcela(parcela);

        if (esInmuebleCasa) {
            try {
                inmuebleCasaProvider.guardarModeloInmuebleCasa(inmuebleCasa);
                JOptionPane.showMessageDialog(null, "Inmueble Casa guardado exitosamente");
                //System.out.println("Inmueble guardado exitosamente");
            } catch (InterruptedException | ExecutionException ex) {
                //System.out.println("Error al guardar el inmueble: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "Error al guardar el inmueble casa: " + ex.getMessage());
            }
        } else {
            try {
                inmuebleApartamentoProvider.guardarModeloInmuebleApartamento(inmuebleApartamento);
                JOptionPane.showMessageDialog(null, "Inmueble Apartamento guardado exitosamente");
                //System.out.println("Inmueble guardado exitosamente");
            } catch (InterruptedException | ExecutionException ex) {
                //System.out.println("Error al guardar el inmueble: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "Error al guardar el inmueble apartamento: " + ex.getMessage());
            }
        }

    }

    /**Este metodo carga datos en tiempo real todo es gracias a addSnapshotListener 
     * que escucha los cambios en la base de datos
     * 
     * @param tabla tabla
     */
    public static void cargarTablaInmuebleTR(JTable tabla) {
        Firestore db = Conexion.getConnection();
        CollectionReference inmuebles = db.collection("Inmueble");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Domicilio");
        model.addColumn("Número");
        model.addColumn("Vecino");
        model.addColumn("ID Vecino");
        model.addColumn("Metros");
        model.addColumn("Piso");
        model.addColumn("Puertas");
        model.addColumn("Parcela");

        inmuebles.addSnapshotListener((@Nullable QuerySnapshot querySnapshot, @Nullable FirestoreException error) -> {
            if (error != null) {
                System.err.println("Error al escuchar cambios: " + error);
                return;
            }

            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {

                /*
                En este caso, se utiliza la clase DecimalFormat para formatear 
                el valor del campo metros a un número con dos decimales antes 
                de agregarlo a la tabla. El valor formateado se almacena en la 
                variable metrosFormateados y se utiliza en la llamada al método 
                addRow para agregar una nueva fila a la tabla.
                 */
                DecimalFormat df = new DecimalFormat("#.##");
                Double metros = document.getDouble("metros") != null ? document.getDouble("metros") : 0.0;
                String metrosFormateados = df.format(metros);

                /*
            En el caso de las fechas se fija por defecto un valor tal cual es 0000-00-00
            en caso de venir vacio.
                 */
                model.addRow(new Object[]{
                    document.getId(),
                    document.getString("domicilio") != null ? document.getString("domicilio") : "",
                    document.getLong("numero") != null ? document.getLong("numero") : 0,
                    document.getString("vecino") != null ? document.getString("vecino") : "",
                    document.getString("idVecino") != null ? document.getString("idVecino") : "",
                    metrosFormateados,
                    document.getString("piso") != null ? document.getString("piso") : "",
                    document.getString("puerta") != null ? document.getString("puerta") : "",
                    document.getString("parcela") != null ? document.getString("parcela") : "",});

            }

            tabla.setModel(model);
        });
    }

    /** Actualiza inmueble
     *
     * @param idDoc id
     * @param domicilio direccion
     * @param numero nº
     * @param vecino dueño
     * @param idVecino id
     * @param metros tamaño
     * @param piso piso
     * @param puerta pta
     * @param parcela para casa
     * @param esInmuebleCasa tipo
     */
    public void actualizarInmueble(String idDoc,
            String domicilio,
            long numero,
            String vecino,
            String idVecino,
            float metros,
            String piso,
            String puerta,
            String parcela,
            boolean esInmuebleCasa) {

        InmuebleApartamento inmuebleApartamento = new InmuebleApartamento();
        inmuebleApartamento.setDomicilio(domicilio);
        inmuebleApartamento.setNumero(numero);
        inmuebleApartamento.setVecino(vecino);
        inmuebleApartamento.setIdVecino(idVecino);
        inmuebleApartamento.setMetros(metros);
        inmuebleApartamento.setPiso(piso);
        inmuebleApartamento.setPuerta(puerta);

        InmuebleCasa inmuebleCasa = new InmuebleCasa();
        inmuebleCasa.setDomicilio(domicilio);
        inmuebleCasa.setNumero(numero);
        inmuebleCasa.setVecino(vecino);
        inmuebleCasa.setIdVecino(idVecino);
        inmuebleCasa.setMetros(metros);
        inmuebleCasa.setParcela(parcela);

        if (esInmuebleCasa) {
            try {
                inmuebleCasaProvider.actualizarModeloInmuebleCasa(idDoc, inmuebleCasa);
                JOptionPane.showMessageDialog(null, "Inmueble Casa actualizar exitosamente");
                //System.out.println("Inmueble guardado exitosamente");
            } catch (InterruptedException | ExecutionException ex) {
                //System.out.println("Error al guardar el inmueble: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "Error al actualizar el inmueble casa: " + ex.getMessage());
            }
        } else {
            try {
                inmuebleApartamentoProvider.actualizarModeloInmuebleApartamento(idDoc, inmuebleApartamento);
                JOptionPane.showMessageDialog(null, "Inmueble Apartamento actualizado exitosamente");
                //System.out.println("Inmueble guardado exitosamente");
            } catch (InterruptedException | ExecutionException ex) {
                //System.out.println("Error al guardar el inmueble: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "Error al actualizar el inmueble apartamento: " + ex.getMessage());
            }
        }

    }

    /** carga seleccion vecino
     *
     * @param box coponente
     * @throws InterruptedException error
     * @throws ExecutionException error
     * @throws IOException error
     */
    public static void cargarComboVecino(JComboBox<String> box) throws InterruptedException, ExecutionException, IOException {
        Firestore db = Conexion.getConnection();
        CollectionReference proveedores = db.collection("Vecino");
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
                modelo.addElement(document.getString("nombre") + " - " + document.getId());
            }
            box.setModel(modelo);
        });
    }

    /** Eliminar inmueble
     *
     * @param idDoc id
     * @param esInmuebleCasa tipo
     */
    public void eliminarInmueble(String idDoc,
            boolean esInmuebleCasa) {

        if (esInmuebleCasa) {
            try {
                inmuebleCasaProvider.eliminarModeloInmueble(idDoc);
                JOptionPane.showMessageDialog(null, "Inmueble Casa eliminado exitosamente");
                //System.out.println("Inmueble eliminado exitosamente");
            } catch (InterruptedException | ExecutionException ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar el inmueble casa: " + ex.getMessage());
                // System.out.println("Error al eliminar el inmueble: " + ex.getMessage());
            }
        } else {
            try {
                inmuebleApartamentoProvider.eliminarModeloInmuebleApartamento(idDoc);
                JOptionPane.showMessageDialog(null, "Inmueble Apartamento eliminado exitosamente");
                //System.out.println("Inmueble eliminado exitosamente");
            } catch (InterruptedException | ExecutionException ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar el inmueble apartamento: " + ex.getMessage());
                // System.out.println("Error al eliminar el inmueble: " + ex.getMessage());
            }
        }

    }

    /** Consulta la suscripcion del inmueble
     *
     * @param id id
     * @return devuelve valor
     */
    public CompletableFuture<Map<String, Object>> consultarDatosSuscripcionInmueblePorId(String id) {
        Firestore db = Conexion.getConnection();
        DocumentReference docRef = db.collection("Vecino").document(id);

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

    /** Seleccion vecino
     *
     * @param comboBox componente
     * @return devuelve valor
     */
    public boolean verificarSeleccionVecino(JComboBox comboBox) {
        if (!verificarSeleccion(comboBox)) {
            JOptionPane.showMessageDialog(null, "Seleccione una opción en Vecino");
            return false;
        }
        return true;
    }

    /** Limpia campos de la vista
     *
     * @param txtId id
     * @param txtDomicilio direccion
     * @param txtTelefono tel 
     * @param txtIdVecino id dueño
     * @param cmbVecino dueño
     * @param txtMetros tamaño
     * @param txtPiso piso
     * @param txtPuerta pta
     * @param txtParcela casa
     * @param cheIsInmuCasa tipo
     */
    public void limpiarCampos(
            JTextField txtId,
            JTextField txtDomicilio,
            JTextField txtTelefono,
            JTextField txtIdVecino,
            JComboBox cmbVecino,
            JTextField txtMetros,
            JTextField txtPiso,
            JTextField txtPuerta,
            JTextField txtParcela,
            JCheckBox cheIsInmuCasa) {

        txtId.setText("");
        txtDomicilio.setText("");
        txtTelefono.setText("");
        txtIdVecino.setText("");
        cmbVecino.setSelectedIndex(0);
        txtMetros.setText("");
        txtPiso.setText("");
        txtPuerta.setText("");
        txtParcela.setText("");
        cheIsInmuCasa.setSelected(false);
    }
}
