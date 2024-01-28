package providers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.mycompany.viuproyecto.Conexion;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import modelo.SuscripcionServicio;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */

// Clase que realiza las busquedas en la base de datos para la suscripcion de servicio
public class SuscripcionServicioProvider {

    private Firestore db;

    /**
     * consulta la suscripcion
     */
    public SuscripcionServicioProvider() {
        this.db = Conexion.getConnection();
    }


    /** Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     * 
     * @param suscripcionServicio suscripcion de servicio
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void guardarModeloSuscripcionServicio(SuscripcionServicio suscripcionServicio) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("SuscripcionServicio").document();
        Map<String, Object> data = new HashMap<>();
        data.put("usuario", suscripcionServicio.getUsuario());
        data.put("servicio", suscripcionServicio.getServicio());
        data.put("inmueble", suscripcionServicio.getInmueble());
        data.put("fechaAlta", suscripcionServicio.getFechaAlta());
        data.put("fechaBaja", suscripcionServicio.getFechaBaja());
        data.put("isActiva", suscripcionServicio.getIsActiva());
        data.put("idUsuario", suscripcionServicio.getIdUsuario());
        data.put("idServicio", suscripcionServicio.getIdServicio());
        data.put("idInmueble", suscripcionServicio.getIdInmueble());
        ApiFuture<WriteResult> result = docRef.set(data);
        result.get();
    }

    /*
 
     */

    /** Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     * Además se recibe el id del documento el cual va a ser actualizado.
     * 
     * @param idDocumento suscripcion de servicio
     * @param suscripcionServicio suscripcion de servicio
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void actualizarModeloSuscripcionServicio(String idDocumento, SuscripcionServicio suscripcionServicio) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("SuscripcionServicio").document(idDocumento);
        Map<String, Object> data = new HashMap<>();
        data.put("usuario", suscripcionServicio.getUsuario());
        data.put("servicio", suscripcionServicio.getServicio());
        data.put("inmueble", suscripcionServicio.getInmueble());
        data.put("fechaAlta", suscripcionServicio.getFechaAlta());
        data.put("fechaBaja", suscripcionServicio.getFechaBaja());
        data.put("isActiva", suscripcionServicio.getIsActiva());
        data.put("idUsuario", suscripcionServicio.getIdUsuario());
        data.put("idServicio", suscripcionServicio.getIdServicio());
        data.put("idInmueble", suscripcionServicio.getIdInmueble());
        ApiFuture<WriteResult> result = docRef.update(data);
        result.get();
    }

    /** Se recibe por parametro el id del documento el cual se va a eliminar
     * 
     * @param idDocumento suscripcion de servicio
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void eliminarModeloSuscripcionServicio(String idDocumento) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("SuscripcionServicio").document(idDocumento);
        ApiFuture<WriteResult> result = docRef.delete();
        result.get();
    }
}
