package providers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.mycompany.viuproyecto.Conexion;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import modelo.InmuebleApartamento;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */

// Clase que realiza las busquedas en la base de datos para inmueble apartamento
public class InmuebleApartamentoProvider {

    private Firestore db;

    /**
     * conculta el inmueble apartamento
     */
    public InmuebleApartamentoProvider() {
        this.db = Conexion.getConnection();
    }


    /** Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     * 
     * @param inmuebleApartamento apartamento
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void guardarModeloInmuebleApartamento(InmuebleApartamento inmuebleApartamento) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Inmueble").document();
        Map<String, Object> data = new HashMap<>();
        data.put("domicilio", inmuebleApartamento.getDomicilio());
        data.put("numero", inmuebleApartamento.getNumero());
        data.put("vecino", inmuebleApartamento.getVecino());
        data.put("idVecino", inmuebleApartamento.getIdVecino());
        data.put("metros", inmuebleApartamento.getMetros());

        //APARTAMENTO
        data.put("piso", inmuebleApartamento.getPiso());
        data.put("puerta", inmuebleApartamento.getPuerta());

        //CASA
        data.put("parcela", "");
        ApiFuture<WriteResult> result = docRef.set(data);
        result.get();
    }

    /** Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     * Además se recibe el id del documento el cual va a ser actualizado. 
     * 
     * @param idDocumento apartamento
     * @param inmuebleApartamento apartamento
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void actualizarModeloInmuebleApartamento(String idDocumento, InmuebleApartamento inmuebleApartamento) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Inmueble").document(idDocumento);
        Map<String, Object> data = new HashMap<>();
        data.put("domicilio", inmuebleApartamento.getDomicilio());
        data.put("numero", inmuebleApartamento.getNumero());
        data.put("vecino", inmuebleApartamento.getVecino());
        data.put("idVecino", inmuebleApartamento.getIdVecino());
        data.put("metros", inmuebleApartamento.getMetros());

        //APARTAMENTO
        data.put("piso", inmuebleApartamento.getPiso());
        data.put("puerta", inmuebleApartamento.getPuerta());

        //CASA
        data.put("parcela", "");
        ApiFuture<WriteResult> result = docRef.update(data);
        result.get();
    }

    /** Se recibe por parametro el id del documento el cual se va a eliminar
     * 
     * @param idDocumento apartamento
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void eliminarModeloInmuebleApartamento(String idDocumento) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Inmueble").document(idDocumento);
        ApiFuture<WriteResult> result = docRef.delete();
        result.get();
    }
}
