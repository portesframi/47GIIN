package providers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.mycompany.viuproyecto.Conexion;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import modelo.ConceptoMultiple;

/** Proyecto asignatura Proyectos de programación 
 *
 * @author frami
 */

// Clase que realiza las busquedas en la base de datos para concepto multiple
public class ConceptoMultipleProvider {

    private Firestore db;

    /**
     *  Conexion a concepto multiple
     */
    public ConceptoMultipleProvider() {
        this.db = Conexion.getConnection();
    }

    /** Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     *
     * @param conceptoMultiple concepto
     * @throws java.lang.InterruptedException error
     * @throws java.util.concurrent.ExecutionException error
     */
    public void guardarModeloConceptoMultiple(ConceptoMultiple conceptoMultiple) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Concepto").document();
        Map<String, Object> data = new HashMap<>();
        data.put("nombreConcepto", conceptoMultiple.getNombre());
        data.put("arrayConcepto", conceptoMultiple.getConceptoMultiple());
        data.put("esConceptoMultiple", true);
        ApiFuture<WriteResult> result = docRef.set(data);
        result.get();
    }

    /**Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     * Además se recibe el id del documento el cual va a ser actualizado.
     * 
     * @param idDocumento concepto
     * @param conceptoMultiple concepto
     * @throws InterruptedException  error
     * @throws ExecutionException error
     */

    public void actualizarModeloConceptoMultiple(String idDocumento, ConceptoMultiple conceptoMultiple) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Concepto").document(idDocumento);
        Map<String, Object> data = new HashMap<>();
        data.put("nombreConcepto", conceptoMultiple.getNombre());
        data.put("arrayConcepto", conceptoMultiple.getConceptoMultiple());
        data.put("esConceptoMultiple", true);
        ApiFuture<WriteResult> result = docRef.update(data);
        result.get();
    }


    /** Se recibe por parametro el id del documento el cual se va a eliminar
     * 
     * @param idDocumento concepto
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void eliminarModeloConceptoMultiple(String idDocumento) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Concepto").document(idDocumento);
        ApiFuture<WriteResult> result = docRef.delete();
        result.get();
    }
}
