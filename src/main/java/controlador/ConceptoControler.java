package controlador;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.mycompany.viuproyecto.Conexion;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Concepto;
import modelo.ConceptoMultiple;
import modelo.ConceptoSimple;
import providers.ConceptoMultipleProvider;
import providers.ConceptoSimpleProvider;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */
public class ConceptoControler {

    private ConceptoMultipleProvider conceptoMultipleProvider;
    private ConceptoSimpleProvider conceptoSimpleProvider;
    private Firestore db;


    /**
     * Realizamos la conexión
     */

    public ConceptoControler() {
        db = Conexion.getConnection(); //Aquí se establece la conexión con Firestore
        conceptoMultipleProvider = new ConceptoMultipleProvider();
        conceptoSimpleProvider = new ConceptoSimpleProvider();
    }


    /** Fijamos al modelo Concepto los valores que recibimos como parametros
     * 
     * @param nombreCS variable para nombre concepto simple
     * @param nombreCM variable para nombre concepto multiple
     * @param esConceptoMultiple valida el tipo de concewpto
     * @param arrayConcepto lista de concepto
     */

    public void guardarConcepto(
            String nombreCS,
            String nombreCM,
            boolean esConceptoMultiple,
            ArrayList arrayConcepto) {

        ConceptoSimple cs = new ConceptoSimple();
        cs.setNombre(nombreCS);
        cs.setEsConceptoMultiple(esConceptoMultiple);

        ConceptoMultiple cm = new ConceptoMultiple();
        cm.setNombre(nombreCM);
        cm.setConceptoMultiple(arrayConcepto);

        if (esConceptoMultiple) {
            try {
                conceptoMultipleProvider.guardarModeloConceptoMultiple(cm);
                JOptionPane.showMessageDialog(null, "Concepto Multiple guardado exitosamente");
                //System.out.println("Gasto guardado exitosamente");
            } catch (InterruptedException | ExecutionException ex) {
                //System.out.println("Error al guardar el gasto: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "Error al guardar el concepto multiple: " + ex.getMessage());
            }
        } else {
            try {
                conceptoSimpleProvider.guardarModeloConceptoSimple(cs);
                JOptionPane.showMessageDialog(null, "Concepto Simple guardado exitosamente");
                //System.out.println("Gasto guardado exitosamente");
            } catch (InterruptedException | ExecutionException ex) {
                //System.out.println("Error al guardar el gasto: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "Error al guardar el concepto simple: " + ex.getMessage());
            }
        }
    }

    /**Este metodo carga datos en tiempo real todo es gracias a addSnapshotListener 
     * que escucha los cambios en la base de datos
     * 
     * @param tabla carga tabla
     */

    public static void cargarTablaConceptoTR(JTable tabla) {
        Firestore db = Conexion.getConnection();
        CollectionReference gastos = db.collection("Concepto");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre del Concepto");
        model.addColumn("Lista de Conceptos");
        model.addColumn("Es C.M");

        gastos.addSnapshotListener((querySnapshot, error) -> {
            if (error != null) {
                System.err.println("Error al escuchar cambios: " + error);
                return;
            }

            model.setRowCount(0); // Limpia el modelo de datos

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                String nombreCS = document.getString("nombreConcepto");
                //String nombreCM = document.getString("nombreCM");
                Boolean esConceptoMultiple = document.getBoolean("esConceptoMultiple");
                Object arrayConceptoObj = document.get("arrayConcepto");

                // Valida y asigna valores predeterminados para los campos que pueden ser nulos o vacíos
                if (nombreCS == null) {
                    nombreCS = "";
                }
//                if (nombreCM == null) {
//                    nombreCM = "";
//                }
                if (esConceptoMultiple == null) {
                    esConceptoMultiple = false;
                }
                List<String> arrayConcepto = new ArrayList<>();
                if (arrayConceptoObj instanceof List) {
                    for (Object conceptoObj : (List<?>) arrayConceptoObj) {
                        if (conceptoObj instanceof String) {
                            arrayConcepto.add((String) conceptoObj);
                        }
                    }
                }

                // Convierte el campo esConceptoMultiple a "Si" o "No"
                String esCM = esConceptoMultiple ? "Si" : "No";

                // Crea una cadena separada por comas de los elementos en arrayConcepto
                StringBuilder sb = new StringBuilder();
                for (String concepto : arrayConcepto) {
                    sb.append(concepto).append(", ");
                }
                if (sb.length() >= 2) {
                    sb.setLength(sb.length() - 2); // Elimina la última coma y espacio
                }

                // Agrega una fila al modelo de datos
                model.addRow(new Object[]{
                    document.getId(),
                    nombreCS,
                    sb.toString(),
                    esCM
                });
            }

            tabla.setModel(model);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
        });
    }

    /** actualizar concepto
     *
     * @param idDoc id
     * @param nombreCM nombre 
     * @param esConceptoMultiple tipo 
     * @param arrayConcepto lista
     */
    public void actualizarConcepto(String idDoc,
            String nombreCM,
            boolean esConceptoMultiple,
            ArrayList arrayConcepto) {
        Concepto c = new Concepto();
        c.setNombre(nombreCM);

        ConceptoSimple cs = new ConceptoSimple();
        cs.setNombre(nombreCM);
        cs.setEsConceptoMultiple(esConceptoMultiple);

        ConceptoMultiple cm = new ConceptoMultiple();
        cm.setNombre(nombreCM);
        cm.setConceptoMultiple(arrayConcepto);

        if (esConceptoMultiple) {
            try {
                conceptoMultipleProvider.actualizarModeloConceptoMultiple(idDoc, cm);
                JOptionPane.showMessageDialog(null, "Concepto Multiple actualizado exitosamente");
                //System.out.println("Gasto guardado exitosamente");
            } catch (InterruptedException | ExecutionException ex) {
                //System.out.println("Error al guardar el gasto: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "Error al actualizar el concepto simple: " + ex.getMessage());
            }
        } else {
            try {
                conceptoSimpleProvider.actualizarModeloConceptoSimple(idDoc, cs);
                JOptionPane.showMessageDialog(null, "Concepto Simple actualizado exitosamente");
                //System.out.println("Gasto guardado exitosamente");
            } catch (InterruptedException | ExecutionException ex) {
                //System.out.println("Error al guardar el gasto: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "Error al actualizar el concepto simple: " + ex.getMessage());
            }
        }

    }

    /** eliminar concepto
     *
     * @param idDoc id
     * @param esConceptoMultiple tipo
     */
    public void eliminarConcepto(String idDoc, boolean esConceptoMultiple) {

        if (esConceptoMultiple) {
            try {
                conceptoMultipleProvider.eliminarModeloConceptoMultiple(idDoc);
                JOptionPane.showMessageDialog(null, "Concepto Multiple eliminado exitosamente");
                //System.out.println("Gasto guardado exitosamente");
            } catch (InterruptedException | ExecutionException ex) {
                //System.out.println("Error al guardar el gasto: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "Error al eliminar el concepto Multiple: " + ex.getMessage());
            }
        } else {
            try {
                conceptoSimpleProvider.eliminarModeloConceptoSimple(idDoc);
                JOptionPane.showMessageDialog(null, "Concepto Simple eliminado exitosamente");
                //System.out.println("Gasto guardado exitosamente");
            } catch (InterruptedException | ExecutionException ex) {
                //System.out.println("Error al guardar el gasto: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "Error al eliminar el concepto simple: " + ex.getMessage());
            }
        }
    }

    /** Limpia los campos de la vista
     * 
     * @param txtId id
     * @param txtNombreCS simple
     * @param txtNombreCM multiple
     * @param txtAreaConceptos descripcion
     * @param checkEsConceptoMultiple tipo
     */
    public void limpiarCampos(
            JTextField txtId,
            JTextField txtNombreCS,
            JTextField txtNombreCM,
            JTextArea txtAreaConceptos,
            JCheckBox checkEsConceptoMultiple) {

        txtId.setText("");
        txtNombreCS.setText("");
        txtNombreCM.setText("");
        txtAreaConceptos.setText("");
        checkEsConceptoMultiple.setSelected(false);

        txtAreaConceptos.setEnabled(false);
        txtNombreCM.setEnabled(false);
        txtNombreCS.setEnabled(true);
    }
}
