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
import modelo.Administrador;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */

// Clase que realiza las busquedas en la base de datos para el Administrador
public class AdministradorProvider {

    private Firestore db;

    /**
     * método para buscar el admninistrador en la base de datos por id
     */
    public static String idAdministrador;

    /**
     * Conecta con la base de datos
     */
    public AdministradorProvider() {
        this.db = Conexion.getConnection();
    }

    /** Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     * 
     * @param administrador admin
     * @throws java.lang.InterruptedException error
     * @throws java.util.concurrent.ExecutionException error
     */
    public void guardarModeloAdministrador(Administrador administrador) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Administrador").document();
        Map<String, Object> data = new HashMap<>();
        idAdministrador = docRef.getId();
        data.put("id", idAdministrador);
        data.put("idUsuario", UsuarioProvider.idUsuario);
        data.put("usuario", administrador.getUsuario());
        data.put("clave", administrador.getContraseña());
        data.put("dni", administrador.getDni());
        data.put("nombre", administrador.getNombre());
        data.put("tipo", administrador.getTipo());
        data.put("telefono", administrador.getTelefono());
        data.put("email", administrador.getEmail());
        data.put("fechaAlta", new Date().getTime());
//
        data.put("alta", administrador.getAlta());
        data.put("baja", administrador.getBaja());
        data.put("modificado", administrador.getModificado());

        ApiFuture<WriteResult> result = docRef.set(data);
        result.get();
    }

    /** Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     * Además se recibe el id del documento el cual va a ser actualizado.
     * 
     * @param idDocumento id de la Bd
     * @param administrador admin
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void actualizarModeloAdministrador(String idDocumento, Administrador administrador) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Administrador").document(idDocumento);
        Map<String, Object> data = new HashMap<>();

        data.put("usuario", administrador.getUsuario());
        data.put("clave", administrador.getContraseña());
        data.put("dni", administrador.getDni());
        data.put("nombre", administrador.getNombre());
        data.put("tipo", administrador.getTipo());
        data.put("telefono", administrador.getTelefono());
        data.put("email", administrador.getEmail());
        //data.put("fechaAlta", new Date().getTime()); //No debe ser actualizada la fecha en la cual se creo...        
//
        data.put("alta", administrador.getAlta());
        data.put("baja", administrador.getBaja());
        data.put("modificado", administrador.getModificado());

        ApiFuture<WriteResult> result = docRef.update(data);
        result.get();
    }

    /** Realizar consulta para obtener documento con idUsuario deseado
     *
     * @param idUsuario id usuario
     * @param administrador admin
     * @throws InterruptedException error
     * @throws ExecutionException error
     */
    public void actualizarAdministradorPorIdUsuario(String idUsuario, Administrador administrador) throws InterruptedException, ExecutionException {
        
        ApiFuture<QuerySnapshot> query = db.collection("Administrador").whereEqualTo("idUsuario", idUsuario).get();
        QuerySnapshot querySnapshot = query.get();

        // Verificar si se encontró un documento
        if (querySnapshot.isEmpty()) {
            System.out.println("No se encontró ningún documento con idUsuario " + idUsuario);
            return;
        }

        // Obtener ID del documento encontrado
        String docId = querySnapshot.getDocuments().get(0).getId();

        // Actualizar documento con ID obtenido
        DocumentReference docRef = db.collection("Administrador").document(docId);
        Map<String, Object> data = new HashMap<>();
        data.put("usuario", administrador.getUsuario());
        data.put("clave", administrador.getContraseña());
        data.put("dni", administrador.getDni());
        data.put("nombre", administrador.getNombre());
        data.put("tipo", administrador.getTipo());
        data.put("telefono", administrador.getTelefono());
        data.put("email", administrador.getEmail());
        data.put("alta", administrador.getAlta());
        data.put("baja", administrador.getBaja());
        data.put("modificado", administrador.getModificado());

        ApiFuture<WriteResult> result = docRef.update(data);
        result.get();
    }


    /** Se recibe por parametro el id del documento el cual se va a eliminar
     * 
     * @param idDocumento id en la Bd
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void eliminarModeloAdministrador(String idDocumento) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Administrador").document(idDocumento);
        ApiFuture<WriteResult> result = docRef.delete();
        result.get();
    }

    /** Elimina el parametros de la base de datos
     * 
     * @param idUsuario id usuarios
     * @throws InterruptedException error
     * @throws ExecutionException error
     */
    public void eliminarAdministradorPorIdUsuario(String idUsuario) throws InterruptedException, ExecutionException {
        Firestore db = Conexion.getConnection();
        CollectionReference administradoresRef = db.collection("Administrador");
        Query query = administradoresRef.whereEqualTo("idUsuario", idUsuario);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            document.getReference().delete();
        }
    }

    /** obtiene el administrador por id
     * 
     * @param idUsuario id
     * @return devuelve
     * @throws InterruptedException obtiene el administrador por id
     * @throws ExecutionException obtiene el administrador por id
     */
    public Administrador obtenerAdministradorPorIdUsuario(String idUsuario) throws InterruptedException, ExecutionException {
        Firestore db = Conexion.getConnection();
        CollectionReference administradoresRef = db.collection("Administrador");
        Query query = administradoresRef.whereEqualTo("idUsuario", idUsuario);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        QuerySnapshot snapshot = querySnapshot.get();

        if (!snapshot.isEmpty()) {
            DocumentSnapshot document = snapshot.getDocuments().get(0);
            Administrador administrador = document.toObject(Administrador.class);
            administrador.setId(document.getId());
            return administrador;
        } else {
            return null;
        }
    }
}
