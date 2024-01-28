package providers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.mycompany.viuproyecto.Conexion;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import modelo.Concepto;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */

//Clase que realiza las busquedas en la base de datos para el concepto
public class ConceptoProvider {

    private Firestore db;

    /**
     * Conexion para buscar el concepto
     */
    public ConceptoProvider() {
        this.db = Conexion.getConnection();
    }

  
    /** Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     * 
     * @param concepto concepto
     * @throws InterruptedException error
     * @throws ExecutionException error
     */ 

    public void guardarModeloConcepto(Concepto concepto) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Concepto").document();
        Map<String, Object> data = new HashMap<>();
        data.put("nombre", concepto.getNombre());
        ApiFuture<WriteResult> result = docRef.set(data);
        result.get();
    }


    /** Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     * Además se recibe el id del documento el cual va a ser actualizado
     * 
     * @param idDocumento concepto
     * @param concepto concepto
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void actualizarModeloConcepto(String idDocumento, Concepto concepto) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Concepto").document(idDocumento);
        Map<String, Object> data = new HashMap<>();
        data.put("nombre", concepto.getNombre());
        ApiFuture<WriteResult> result = docRef.update(data);
        result.get();
    }


    /** Se recibe por parametro el id del documento el cual se va a eliminar
     * 
     * @param idDocumento concepto
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void eliminarModeloConcepto(String idDocumento) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Concepto").document(idDocumento);
        ApiFuture<WriteResult> result = docRef.delete();
        result.get();
    }
}
