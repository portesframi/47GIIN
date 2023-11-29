package providers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.mycompany.viuproyecto.Conexion;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import modelo.Vecino;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */

// Clase que realiza las busquedas en la base de datos para el vecino
public class VecinoProvider {

    private Firestore db;

    /**
     * conculta el id vecino
     */
    public static String idVecino;

    /**
     * conculta el vecino
     */
    public VecinoProvider() {
        this.db = Conexion.getConnection();
    }


    /** Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     * 
     * @param vecino vecino
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void guardarModeloVecino(Vecino vecino) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Vecino").document();
        Map<String, Object> data = new HashMap<>();
        idVecino = docRef.getId();
        data.put("idVecino", idVecino);
        data.put("idUsuario", UsuarioProvider.idUsuario);
        data.put("usuario", vecino.getUsuario());
        data.put("contraseña", vecino.getContraseña());
        data.put("dni", vecino.getDni());
        data.put("nombre", vecino.getNombre());
        data.put("tipo", vecino.getTipo());
        data.put("telefono", vecino.getTelefono());
        data.put("email", vecino.getEmail());
        data.put("fechaAlta", new Date().getTime());
        ApiFuture<WriteResult> result = docRef.set(data);
        result.get();
    }

    /** Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     * Además se recibe el id del documento el cual va a ser actualizado.
     *  
     * @param idDocumento vecino
     * @param vecino vecino
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void actualizarModeloVecino(String idDocumento, Vecino vecino) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Vecino").document(idDocumento);
        Map<String, Object> data = new HashMap<>();
        data.put("usuario", vecino.getUsuario());
        data.put("contraseña", vecino.getContraseña());
        data.put("dni", vecino.getDni());
        data.put("nombre", vecino.getNombre());
        data.put("tipo", vecino.getTipo());
        data.put("telefono", vecino.getTelefono());
        data.put("email", vecino.getEmail());
        data.put("fechaAlta", new Date().getTime());
        ApiFuture<WriteResult> result = docRef.update(data);
        result.get();
    }

    /** Actualiza el vecino a través de la id
     * 
     * @param idUsuario vecino
     * @param vecino vecino
     * @throws InterruptedException v
     * @throws ExecutionException error
     */
    public void actualizarVecinorPorIdUsuario(String idUsuario, Vecino vecino) throws InterruptedException, ExecutionException {
        // Realizar consulta para obtener documento con idUsuario deseado
        ApiFuture<QuerySnapshot> query = db.collection("Vecino").whereEqualTo("idUsuario", idUsuario).get();
        QuerySnapshot querySnapshot = query.get();

        // Verificar si se encontró un documento
        if (querySnapshot.isEmpty()) {
            System.out.println("No se encontró ningún documento con idUsuario " + idUsuario);
            return;
        }

        // Obtener ID del documento encontrado
        String docId = querySnapshot.getDocuments().get(0).getId();

        // Actualizar documento con ID obtenido
        DocumentReference docRef = db.collection("Vecino").document(docId);
        Map<String, Object> data = new HashMap<>();
        data.put("usuario", vecino.getUsuario());
        data.put("clave", vecino.getContraseña());
        data.put("dni", vecino.getDni());
        data.put("nombre", vecino.getNombre());
        data.put("tipo", vecino.getTipo());
        data.put("telefono", vecino.getTelefono());
        data.put("email", vecino.getEmail());

        ApiFuture<WriteResult> result = docRef.update(data);
        result.get();
    }

    /** Se recibe por parametro el id del documento el cual se va a eliminar
     * 
     * @param idDocumento vecino
     * @throws InterruptedException error
     * @throws ExecutionException error
     */ 

    public void eliminarModeloVecino(String idDocumento) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Vecino").document(idDocumento);
        ApiFuture<WriteResult> result = docRef.delete();
        result.get();
    }

    /** elimina el vecino a gtravés de su id
     * 
     * @param idUsuario vecino
     * @throws InterruptedException v
     * @throws ExecutionException error
     */
    public void eliminarVecinoPorIdUsuario(String idUsuario) throws InterruptedException, ExecutionException {
        Firestore db = Conexion.getConnection();
        CollectionReference administradoresRef = db.collection("Vecino");
        Query query = administradoresRef.whereEqualTo("idUsuario", idUsuario);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            document.getReference().delete();
        }
    }

    /** obtiene el vecino a través de la id de usuario
     * 
     * @param idUsuario vecino
     * @return vecino
     * @throws InterruptedException error
     * @throws ExecutionException error
     */
    public Vecino obtenerVecinoPorIdUsuario(String idUsuario) throws InterruptedException, ExecutionException {
        Firestore db = Conexion.getConnection();
        CollectionReference vecinosRef = db.collection("Vecino");
        Query query = vecinosRef.whereEqualTo("idUsuario", idUsuario);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        QuerySnapshot snapshot = querySnapshot.get();

        if (!snapshot.isEmpty()) {
            DocumentSnapshot document = snapshot.getDocuments().get(0);
            Vecino vecino = document.toObject(Vecino.class);
            vecino.setId(document.getId());
            return vecino;
        } else {
            return null;
        }
    }

}
