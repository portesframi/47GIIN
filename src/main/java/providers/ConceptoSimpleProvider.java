package providers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.mycompany.viuproyecto.Conexion;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import modelo.ConceptoSimple;

/** Proyecto asignatura Proyectos de programación 
 *
 * @author frami
 */

// Clase que realiza las busquedas en la base de datos para concepto simple
public class ConceptoSimpleProvider {

    private Firestore db;

    /**
     * crea la conexion para concepto simple
     */
    public ConceptoSimpleProvider() {
        this.db = Conexion.getConnection();
    }


    /** Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     * 
     * @param conceptoSimple concepto
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void guardarModeloConceptoSimple(ConceptoSimple conceptoSimple) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Concepto").document();
        Map<String, Object> data = new HashMap<>();
        data.put("nombreConcepto", conceptoSimple.getNombre() );
        data.put("arrayConcepto", "");
        data.put("esConceptoMultiple", conceptoSimple.getIsEsConceptoMultiple());
        ApiFuture<WriteResult> result = docRef.set(data);
        result.get();
    }


    /** Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     * Además se recibe el id del documento el cual va a ser actualizado.
     * 
     * @param idDocumento concepto
     * @param conceptoSimple concepto
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void actualizarModeloConceptoSimple(String idDocumento, ConceptoSimple conceptoSimple) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Concepto").document(idDocumento);
        Map<String, Object> data = new HashMap<>();
        data.put("nombreConcepto", conceptoSimple.getNombre());
        data.put("arrayConcepto", "");
        data.put("esConceptoMultiple", conceptoSimple.getIsEsConceptoMultiple());
        ApiFuture<WriteResult> result = docRef.update(data);
        result.get();
    }


    /** Se recibe por parametro el id del documento el cual se va a eliminar
     * 
     * @param idDocumento concepto
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void eliminarModeloConceptoSimple(String idDocumento) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Concepto").document(idDocumento);
        ApiFuture<WriteResult> result = docRef.delete();
        result.get();
    }
}
