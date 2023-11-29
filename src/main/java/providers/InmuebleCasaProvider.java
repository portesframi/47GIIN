package providers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.mycompany.viuproyecto.Conexion;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import modelo.InmuebleCasa;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */

// Clase que realiza las busquedas en la base de datos para inmueble casa
public class InmuebleCasaProvider {

    private Firestore db;

    /**
     * conculta la casa inmueble
     */
    public InmuebleCasaProvider() {
        this.db = Conexion.getConnection();
    }



    /** Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     *
     * @param inmuebleCasa casa inmueble
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void guardarModeloInmuebleCasa(InmuebleCasa inmuebleCasa) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Inmueble").document();
        Map<String, Object> data = new HashMap<>();
        data.put("domicilio", inmuebleCasa.getDomicilio());
        data.put("numero", inmuebleCasa.getNumero());
        data.put("vecino", inmuebleCasa.getVecino());
        data.put("idVecino", inmuebleCasa.getIdVecino());
        data.put("metros", inmuebleCasa.getMetros());
        
        //APARTAMENTO
        data.put("piso", "");        
        data.put("puerta", ""); 
        
        //CASA
        data.put("parcela", inmuebleCasa.getParcela());
        ApiFuture<WriteResult> result = docRef.set(data);
        result.get();
    }

    /**Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     * Además se recibe el id del documento el cual va a ser actualizado.
     *  
     * @param idDocumento casa inmueble
     * @param inmuebleCasa casa inmueble
     * @throws InterruptedException v
     * @throws ExecutionException error
     */

    public void actualizarModeloInmuebleCasa(String idDocumento, InmuebleCasa inmuebleCasa) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Inmueble").document(idDocumento);
        Map<String, Object> data = new HashMap<>();
        data.put("domicilio", inmuebleCasa.getDomicilio());
        data.put("numero", inmuebleCasa.getNumero());
        data.put("vecino", inmuebleCasa.getVecino());
        data.put("idVecino", inmuebleCasa.getIdVecino());
        data.put("metros", inmuebleCasa.getMetros());
        
        //APARTAMENTO
        data.put("piso", "");        
        data.put("puerta", "");   
        
        //CASA
        data.put("parcela", inmuebleCasa.getParcela());
        ApiFuture<WriteResult> result = docRef.update(data);
        result.get();
    }


    /** Se recibe por parametro el id del documento el cual se va a eliminar
     * 
     * @param idDocumento casa inmueble
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void eliminarModeloInmueble(String idDocumento) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Inmueble").document(idDocumento);
        ApiFuture<WriteResult> result = docRef.delete();
        result.get();
    }
}
