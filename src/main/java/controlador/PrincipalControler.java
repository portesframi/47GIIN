package controlador;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.mycompany.viuproyecto.Conexion;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import vista.VistaLogin;
import vista.VistaPrincipal;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */
public class PrincipalControler {

    private Firestore db;

    /**
     * Define el tipo de usuario conectado
     */
    public static String tipoUser;

    /**
     * Define el usuario conectado
     */
    public static String User;

    /**
     * Clase que controla la vista principal del sistema
     */
    public PrincipalControler() {
        // Obtener la instancia de Firestore
        db = Conexion.getConnection();
    }

    /** Permite saber el Tipo de usuario
     *
     * @param idDocumento id en bd
     * @return valor
     * @throws InterruptedException error
     * @throws ExecutionException error
     */
    public String obtenerTipoUsuario(String idDocumento) throws InterruptedException, ExecutionException {
        // Crear una referencia al documento del usuario
        DocumentReference docRef = db.collection("Usuario").document(idDocumento);

        // Obtener el documento del usuario
        ApiFuture<DocumentSnapshot> docFuture = docRef.get();
        DocumentSnapshot doc = docFuture.get();

        // Verificar si el documento existe
        if (!doc.exists()) {
            return null;
        }

        // Obtener el valor del campo "tipo"
        String tipo = doc.getString("tipo");

        return tipo;
    }

    /** Obtiene el usuario de la Bd
     *
     * @param idDocumento id
     * @return valor
     * @throws InterruptedException error
     * @throws ExecutionException error
     */
    public String obtenerUsuario(String idDocumento) throws InterruptedException, ExecutionException {
        // Crear una referencia al documento del usuario
        DocumentReference docRef = db.collection("Usuario").document(idDocumento);

        // Obtener el documento del usuario
        ApiFuture<DocumentSnapshot> docFuture = docRef.get();
        DocumentSnapshot doc = docFuture.get();

        // Verificar si el documento existe
        if (!doc.exists()) {
            return null;
        }

        // Obtener el valor del campo "usuario"
        String usuario = doc.getString("usuario");

        return usuario;
    }

    /**
     * Permite gestionar la visibilidad de los menus segun el tipo de usuario
     */
    public void componetesSegunUsuario() {
        try {
            if (VistaLogin.idUser == null) {
                System.out.println("El id esta vacio: " + VistaLogin.idUser);
            } else {
                tipoUser = obtenerTipoUsuario(VistaLogin.idUser);
                User = obtenerUsuario(VistaLogin.idUser);
                if (tipoUser.equals("Administrador")) {
                    /*
                    Es Administrador
                     */
                    verTodosComponentes();
                    cagarLblUsuario();
                    VistaPrincipal.lblTipoUser.setText(tipoUser);
                    VistaPrincipal.lblUsuario.setText(User);
                    //System.out.println("Soy Administrador");
                    
                } else if (tipoUser.equals("Vecino")) {
                    /*
                    Es Vecino
                     */
                    
                    verComponetesVecino();
                    cagarLblUsuario();
                    VistaPrincipal.lblTipoUser.setText(tipoUser);
                    VistaPrincipal.lblUsuario.setText(User);
                    //System.out.println("Soy Vecino");
                }
            }

        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(VistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Oculta los menus 
     */
    public void ocultarTodosComponentes() {

        VistaPrincipal.lblTipoUser.setVisible(false);
        VistaPrincipal.lblUsuario.setVisible(false);
        VistaPrincipal.btnInmueble.setVisible(false);
        VistaPrincipal.btnAdministrador.setVisible(false);
        VistaPrincipal.btnVecino.setVisible(false);
        VistaPrincipal.btnUsuarios.setVisible(false);
        VistaPrincipal.panelVecino.setVisible(false);
    }

    /**
     * Permite ver los menus 
     */
    public void verTodosComponentes() {

        VistaPrincipal.lblTipoUser.setVisible(true);
        VistaPrincipal.lblUsuario.setVisible(true);
        VistaPrincipal.btnInmueble.setVisible(true);
        VistaPrincipal.btnAdministrador.setVisible(true);
        VistaPrincipal.btnVecino.setVisible(true);
        VistaPrincipal.btnUsuarios.setVisible(true);
        VistaPrincipal.panelVecino.setVisible(true);
    }

    /**
     * Oculta los menus a los vecinos
     */
    public void ocultarComponetesVecino() {

        VistaPrincipal.panelVecino.setVisible(false);

    }
    
    /**
     * Activa los menus de los vecinos
     */
    public void verComponetesVecino() {

        VistaPrincipal.panelVecino.setVisible(true);

    }

    /**
     * Carga el nombre del usuario
     */
    public void cagarLblUsuario() {
        VistaPrincipal.lblUsuario.setVisible(true);
        VistaPrincipal.lblTipoUser.setVisible(true);
    }
}
