package providers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.mycompany.viuproyecto.Conexion;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import modelo.DetalleLiquidacion;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */

// Clase que realiza las busquedas en la base de datos para detalleliquidacion
public class DetalleLiquidacionProvider {

    private Firestore db;

    /**
     * busca el detalle liquidacion
     */
    public DetalleLiquidacionProvider() {
        this.db = Conexion.getConnection();
    }

 
    /** Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     * 
     * @param detalleLiquidacion liquidacion
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void guardarModeloDetalleLiquidacion(DetalleLiquidacion detalleLiquidacion) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("DetalleLiquidacion").document();
        Map<String, Object> data = new HashMap<>();
        data.put("idInmueble", detalleLiquidacion.getIdInmueble());
        data.put("nombreInmueble", detalleLiquidacion.getNombreInmueble());
        data.put("idUsuario", detalleLiquidacion.getIdUsuario());
        data.put("nombreUsuario", detalleLiquidacion.getNombreUsuario());
        data.put("idServicio", detalleLiquidacion.getIdServicio());
        data.put("nombreSuscripcionServicio", detalleLiquidacion.getNombreSuscripcionServicio());
        data.put("nombre", detalleLiquidacion.getNombre());
        data.put("importeFinal", detalleLiquidacion.getImporteFinal());
        data.put("concepto", detalleLiquidacion.getConcepto());
        data.put("periodoLiquidacion", detalleLiquidacion.getPeriodoLiquidacion());
        ApiFuture<WriteResult> result = docRef.set(data);
        result.get();
    }



    /** Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     * Además se recibe el id del documento el cual va a ser actualizado.
     *  
     * @param idDocumento liquidacion
     * @param detalleLiquidacion liquidacion
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void actualizarModeloDetalleLiquidacion(String idDocumento, DetalleLiquidacion detalleLiquidacion) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("DetalleLiquidacion").document(idDocumento);
        Map<String, Object> data = new HashMap<>();
        data.put("idInmueble", detalleLiquidacion.getIdInmueble());
        data.put("nombreInmueble", detalleLiquidacion.getNombreInmueble());
        data.put("idUsuario", detalleLiquidacion.getIdUsuario());
        data.put("nombreUsuario", detalleLiquidacion.getNombreUsuario());
        data.put("idServicio", detalleLiquidacion.getIdServicio());
        data.put("nombreSuscripcionServicio", detalleLiquidacion.getNombreSuscripcionServicio());
        data.put("nombre", detalleLiquidacion.getNombre());
        data.put("importeFinal", detalleLiquidacion.getImporteFinal());
        data.put("concepto", detalleLiquidacion.getConcepto());
        data.put("periodoLiquidacion", detalleLiquidacion.getPeriodoLiquidacion());
        ApiFuture<WriteResult> result = docRef.update(data);
        result.get();
    }

    /** Se recibe por parametro el id del documento el cual se va a eliminar
     * 
     * @param idDocumento liquidacion
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void eliminarModeloDetalleLiquidacion(String idDocumento) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("DetalleLiquidacion").document(idDocumento);
        ApiFuture<WriteResult> result = docRef.delete();
        result.get();
    }
}
