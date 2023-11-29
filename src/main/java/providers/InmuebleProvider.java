package providers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.mycompany.viuproyecto.Conexion;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import modelo.Inmueble;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */

// Clase que realiza las busquedas en la base de datos para inmueble 
public class InmuebleProvider {

    private Firestore db;

    /**
     * conculta el inmueble
     */
    public InmuebleProvider() {
        this.db = Conexion.getConnection();
    }

    /** Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     * 
     * @param inmueble inmueble
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void guardarModeloInmueble(Inmueble inmueble) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Inmueble").document();
        Map<String, Object> data = new HashMap<>();
        data.put("domicilio", inmueble.getDomicilio());
        data.put("numero", inmueble.getNumero());
        data.put("vecino", inmueble.getVecino());
        data.put("idVecino", inmueble.getIdVecino());
        data.put("metros", inmueble.getMetros());
        ApiFuture<WriteResult> result = docRef.set(data);
        result.get();
    }


    /**Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     * Además se recibe el id del documento el cual va a ser actualizado.
     *  
     * @param idDocumento inmueble
     * @param inmueble inmueble
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void actualizarModeloInmueble(String idDocumento, Inmueble inmueble) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Inmueble").document(idDocumento);
        Map<String, Object> data = new HashMap<>();
        data.put("domicilio", inmueble.getDomicilio());
        data.put("numero", inmueble.getNumero());
        data.put("vecino", inmueble.getVecino());
        data.put("idVecino", inmueble.getIdVecino());
        data.put("metros", inmueble.getMetros());
        ApiFuture<WriteResult> result = docRef.update(data);
        result.get();
    }

    /** Se recibe por parametro el id del documento el cual se va a eliminar
     *  
     * @param idDocumento inmueble
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void eliminarModeloInmueble(String idDocumento) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Inmueble").document(idDocumento);
        ApiFuture<WriteResult> result = docRef.delete();
        result.get();
    }
}
