package controlador;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.database.annotations.Nullable;
import com.mycompany.viuproyecto.Conexion;
import java.util.concurrent.ExecutionException;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Servicio;
import providers.ServicioProvider;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */
public class ServicioControler {

    private ServicioProvider servicioProvider;
    private Firestore db;

    /** 
     * Realizamos la conexión
     */
    public ServicioControler() {
        db = Conexion.getConnection(); //Aquí se establece la conexión con Firestore
        servicioProvider = new ServicioProvider();
    }

    /**Fijamos al modelo Servicio los valores que recibimos como parametros
     * 
     * @param nombre nom
     * @param detalle det
     * @param isObligatorio obligacion o no de servicio
     */
    public void guardarServicio(
            String nombre,
            String detalle,
            boolean isObligatorio) {

        Servicio s = new Servicio();
        s.setNombre(nombre);
        s.setDetalle(detalle);
        s.setIsObligatorio(isObligatorio);

        try {
            servicioProvider.guardarModeloServicio(s);
            JOptionPane.showMessageDialog(null, "Servicio guardado exitosamente");
            //System.out.println("Servicio guardado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            //System.out.println("Error al guardar el servicio: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al guardar el servicio: " + ex.getMessage());
        }
    }

    /** Este metodo carga datos en tiempo real todo es gracias a addSnapshotListener 
     * que escucha los cambios en la base de datos
     * 
     * @param tabla tabla
     */
    public static void cargarTablaServicioTR(JTable tabla) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("Servicio");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Detalle");
        model.addColumn("Es obligatorio");

        gastos.addSnapshotListener((@Nullable QuerySnapshot querySnapshot, @Nullable FirestoreException error) -> {
            if (error != null) {
                System.err.println("Error al escuchar cambios: " + error);
                return;
            }

            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {

                String isObligatorio = String.valueOf(document.getBoolean("isObligatorio"));
                if (isObligatorio.equals("true")) {
                    isObligatorio = "Si";
                } else {
                    isObligatorio = "No";
                }
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
                    document.getString("detalle") != null ? document.getString("detalle") : "",
                    isObligatorio != null ? isObligatorio : "",});
            }

            tabla.setModel(model);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
        });
    }

    /** Actualizar servicio
     *
     * @param idDoc id
     * @param nombre nombre
     * @param detalle detalle
     * @param isObligatorio obligacion o no de servicio
     */
    public void actualizarServicio(
            String idDoc,
            String nombre,
            String detalle,
            boolean isObligatorio) {

        Servicio s = new Servicio();
        s.setNombre(nombre);
        s.setDetalle(detalle);
        s.setIsObligatorio(isObligatorio);

        try {
            servicioProvider.actualizarModeloServicio(idDoc, s);
            JOptionPane.showMessageDialog(null, "Servicio actualizado exitosamente");
            //System.out.println("Servicio actualizado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el servicio: " + ex.getMessage());
            //System.out.println("Error al actualizar el servicio: " + ex.getMessage());
        }
    }

    /** Elimianr servicio
     *
     * @param idDoc id
     */
    public void eliminarServicio(String idDoc) {
        try {
            servicioProvider.eliminarModeloServicio(idDoc);
            JOptionPane.showMessageDialog(null, "Servicio eliminado exitosamente");
            //System.out.println("Gasto eliminado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el servicio: " + ex.getMessage());
            // System.out.println("Error al eliminar el gasto: " + ex.getMessage());
        }
    }

    /**Verifica que la longitud sea menor a 30 caractreres
     * 
     * @param servicio el servicio
     * @return valor
     */ 
    public static boolean verificarLongitudServicio(String servicio) {
        if (servicio.length() > 30) {
            JOptionPane.showMessageDialog(null, "El nombre del servicio no puede superar los 30 caracteres");
            return false;
        }
        return true;
    }

    /** limpia los campos de los atributos en la vista 
     *
     * @param txtNombre nombre
     * @param txtAreaDetalle detalle
     * @param checkBoxObligatorio obligacion o no de servicio
     */
    public void limpiarCampos(JTextField txtNombre,
            JTextArea txtAreaDetalle,
            JCheckBox checkBoxObligatorio) {

        txtNombre.setText("");
        txtAreaDetalle.setText("");
        checkBoxObligatorio.setSelected(false);

    }
}
