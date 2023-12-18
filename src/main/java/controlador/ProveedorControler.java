package controlador;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.database.annotations.Nullable;
import com.mycompany.viuproyecto.Conexion;
import java.util.concurrent.ExecutionException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Proveedor;
import providers.ProveedorProvider;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */
public class ProveedorControler {
    
    private ProveedorProvider proveedorProvider;
    private Firestore db;

    /**
     * Realizamos la conexión
     */
    public ProveedorControler() {
        db = Conexion.getConnection(); //Aquí se establece la conexión con Firestore
        proveedorProvider = new ProveedorProvider();
    }

    /** Fijamos al modelo Proveedor los valores que recibimos como parametros
     * 
     * @param nombre nombre
     * @param telefono tel
     * @param email email
     * @param direccion direcc
     */
    public void guardarProveedor(String nombre,
            String telefono,
            String email,
            String direccion) {

        Proveedor p = new Proveedor();
        p.setNombre(nombre);
        p.setTelefono(telefono);
        p.setEmail(email);
        p.setDireccion(direccion);
        try {
            proveedorProvider.guardarModeloProveedor(p);
            JOptionPane.showMessageDialog(null, "Proveedor guardado exitosamente");
            //System.out.println("Gasto guardado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            //System.out.println("Error al guardar el gasto: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al guardar el proveedor: " + ex.getMessage());
        }
    }

    /** Este metodo carga datos en tiempo real todo es gracias a addSnapshotListener 
     * que escucha los cambios en la base de datos
     * 
     * @param tabla tabla
     */
    public static void cargarTablaProveedorTR(JTable tabla) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("Proveedor");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Telefono");
        model.addColumn("E-mail");
        model.addColumn("Dirección");

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
                    document.getString("direccion") != null ? document.getString("direccion") : "",});
            }

            tabla.setModel(model);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
        });
    }

    /** Actualiza el porveedor de servicio
     *
     * @param idDoc id
     * @param nombre nombre
     * @param telefono tel
     * @param email email
     * @param direccion direc
     */
    public void actualizarProveedor(String idDoc, String nombre,
            String telefono,
            String email,
            String direccion) {
        Proveedor p = new Proveedor();
        p.setNombre(nombre);
        p.setTelefono(telefono);
        p.setEmail(email);
        p.setDireccion(direccion);
        try {
            proveedorProvider.actualizarModeloProveedor(idDoc, p);
            JOptionPane.showMessageDialog(null, "Proveedor actualizado exitosamente");
            //System.out.println("Proveedor actualizado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el proveedor: " + ex.getMessage());
            //System.out.println("Error al actualizar el proveedor: " + ex.getMessage());
        }
    }

    /** Elimina el gasto
     *
     * @param idDoc id
     */
    public void eliminarGasto(String idDoc) {
        try {
            proveedorProvider.eliminarModeloProveedor(idDoc);
            JOptionPane.showMessageDialog(null, "Proveedor eliminado exitosamente");
            //System.out.println("Gasto eliminado exitosamente");
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el proveedor: " + ex.getMessage());
            // System.out.println("Error al eliminar el gasto: " + ex.getMessage());
        }
    }

    /**limpia los campos de los atributos en la vista 
     * 
     * @param txtNombre nombre
     * @param txtTelefono tel
     * @param txtEmail email
     * @param txtDireccion direc
     */
    public void limpiarCampos(JTextField txtNombre,
            JTextField txtTelefono,
            JTextField txtEmail,
            JTextField txtDireccion) {
        
        txtNombre.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtDireccion.setText("");
    }
}
