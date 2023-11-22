package providers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.mycompany.viuproyecto.Conexion;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import modelo.Usuario;

/** Asignatura Proyecto de ingenieria de software  
 *
 * @author frami
 */

// Clase que realiza las busquedas en la base de datos para Usuario
public class UsuarioProvider {

    private Firestore db;

    /**
     * conculta el id del usuario
     */
    public static String idUsuario;

    /**
     * conculta el usuario en la base de datos
     */
    public UsuarioProvider() {
        this.db = Conexion.getConnection();
    }


    /** Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     * 
     * @param usuario usuario
     * @throws InterruptedException v
     * @throws ExecutionException error
     */

    public void guardarModeloUsuario(Usuario usuario) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Usuario").document();
        Map<String, Object> data = new HashMap<>();
        idUsuario = docRef.getId();
        data.put("id", idUsuario);
        data.put("usuario", usuario.getUsuario());
        data.put("contraseña", usuario.getContraseña());
        data.put("dni", usuario.getDni());
        data.put("nombre", usuario.getNombre());
        data.put("tipo", usuario.getTipo());
        data.put("telefono", usuario.getTelefono());
        data.put("email", usuario.getEmail());
        data.put("fechaAlta", new Date().getTime());
        ApiFuture<WriteResult> result = docRef.set(data);
        result.get();
    }


    /** Recibimos por parametro el modelo correspondiente para realizar la escritura del mismo en la DB Firestore
     * Además se recibe el id del documento el cual va a ser actualizado. 
     * 
     * @param idDocumento usuario
     * @param usuario usuario
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void actualizarModeloUsuario(String idDocumento, Usuario usuario) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Usuario").document(idDocumento);
        Map<String, Object> data = new HashMap<>();
        data.put("usuario", usuario.getUsuario());
        data.put("contraseña", usuario.getContraseña());
        data.put("dni", usuario.getDni());
        data.put("nombre", usuario.getNombre());
        data.put("tipo", usuario.getTipo());
        data.put("telefono", usuario.getTelefono());
        data.put("email", usuario.getEmail());
        data.put("fechaAlta", new Date().getTime());
        ApiFuture<WriteResult> result = docRef.update(data);
        result.get();
    }

 
    /** Se recibe por parametro el id del documento el cual se va a eliminar
     * 
     * @param idDocumento usuario
     * @throws InterruptedException error
     * @throws ExecutionException error
     */

    public void eliminarModeloUsuario(String idDocumento) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Usuario").document(idDocumento);
        ApiFuture<WriteResult> result = docRef.delete();
        result.get();
    }

    /** Obtiene el usuario de la base de datos
     * 
     * @param idDoc usuario
     * @return usuario
     * @throws InterruptedException error
     * @throws ExecutionException error
     */
    public Usuario obtenerModeloUsuario(String idDoc) throws InterruptedException, ExecutionException {
        ApiFuture<DocumentSnapshot> future = db.collection("Usuario").document(idDoc).get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            Usuario usuario = document.toObject(Usuario.class);
            usuario.setId(document.getId());
            return usuario;
        } else {
            return null;
        }
    }
    
    /** obtiene el id del usuario de la base de datos
     * 
     * @param idDoc id usuario
     * @return valor
     */
    public DocumentSnapshot obtenerModeloUsuarioDOC(String idDoc) {
    Firestore db = Conexion.getConnection();
    DocumentReference documentRef = db.collection("Usuario").document(idDoc);

    try {
        return documentRef.get().get();
    } catch (InterruptedException | ExecutionException ex) {
        // Manejo de excepciones en caso de error
        ex.printStackTrace();
        return null;
    }
}

    /** obtiene de la base de datos un usuario concreto
     * 
     * @param idDoc id
     * @return valor
     */
    public Usuario obtenerModeloUsuarioX(String idDoc) {
    Firestore db = Conexion.getConnection();
    DocumentReference documentRef = db.collection("Usuario").document(idDoc);

    try {
        DocumentSnapshot document = documentRef.get().get();
        if (document.exists()) {
            Map<String, Object> data = document.getData();
            Usuario usuario = new Usuario();
            usuario.setId(document.getId());
            usuario.setUsuario((String) data.get("usuario"));
            usuario.setContraseña((String) data.get("contraseña"));
            usuario.setDni((String) data.get("dni"));
            usuario.setNombre((String) data.get("nombre"));
            usuario.setTipo((String) data.get("tipo"));
            usuario.setTelefono((String) data.get("telefono"));
            usuario.setEmail((String) data.get("email"));
            return usuario;
        } else {
            return null;
        }
    } catch (InterruptedException | ExecutionException ex) {
        // Manejo de excepciones en caso de error
        ex.printStackTrace();
        return null;
    }
}


}
