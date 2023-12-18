package providers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.mycompany.viuproyecto.Conexion;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import modelo.Gasto;

/** Proyecto asignatura Proyectos de programación 
 *
 * @author frami
 */

// Clase que realiza las busquedas en la base de datos para gastos
public class GastoProvider {

    private Firestore db;

    /**
     * busca el gasto
     */
    public GastoProvider() {
        this.db = Conexion.getConnection();
    }



    /** Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     * 
     * @param gasto gasto
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void guardarModeloGasto(Gasto gasto) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Gastos").document();
        Map<String, Object> data = new HashMap<>();
        data.put("importe", gasto.getImporte());
        data.put("fechaRegistro", gasto.getFechaRegistro());
        data.put("fechaPago", gasto.getFechaPago());
        data.put("numeroComprobante", gasto.getNumeroComprobante());
        data.put("proveedor", gasto.getProveedor());
        data.put("concepto", gasto.getConcepto());
        data.put("servicio", gasto.getServicio());
        data.put("liquidacion", gasto.getLiquidacion());
        data.put("idServicio", gasto.getIdServicio());
        ApiFuture<WriteResult> result = docRef.set(data);
        result.get();
    }



    /** Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     * Además se recibe el id del documento el cual va a ser actualizado. 
     * 
     * @param idDocumento gasto
     * @param gasto gasto
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void actualizarModeloGasto(String idDocumento, Gasto gasto) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Gastos").document(idDocumento);
        Map<String, Object> data = new HashMap<>();
        data.put("importe", gasto.getImporte());
        data.put("fechaRealizacionPago", gasto.getFechaRegistro());
        data.put("fechaPago", gasto.getFechaPago());
        data.put("numeroComprobante", gasto.getNumeroComprobante());
        data.put("proveedor", gasto.getProveedor());
        data.put("concepto", gasto.getConcepto());
        data.put("servicio", gasto.getServicio());
        data.put("liquidacion", gasto.getLiquidacion());
        data.put("idServicio", gasto.getIdServicio());
        ApiFuture<WriteResult> result = docRef.update(data);
        result.get();
    }



    /** Se recibe por parametro el id del documento el cual se va a eliminar
     * 
     * @param idDocumento gasto
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void eliminarModeloGasto(String idDocumento) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Gastos").document(idDocumento);
        ApiFuture<WriteResult> result = docRef.delete();
        result.get();
    }
}
