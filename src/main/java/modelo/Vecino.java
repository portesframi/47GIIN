package modelo;

/** Asignatura Proyecto de ingenieria de software  
 *
 * @author frami
 */

// Clase que define el vecino que extiende a usuario 
public class Vecino extends Usuario{

    /**
     * Crea el vecino
     */
    public Vecino() {
    }

    /**Atributos especificos de esta clase
     * 
     * @param id vecino
     * @param usuario vecino
     * @param contraseña vecino
     * @param dni vecino
     * @param nombre vecino
     * @param tipo vecino
     * @param telefono vecino
     * @param email vecino
     * @param fechaAlta vecino
     */
    public Vecino(String id, String usuario, String contraseña, String dni, String nombre, String tipo, String telefono, String email, long fechaAlta) {
        super(id, usuario, contraseña, dni, nombre, tipo, telefono, email, fechaAlta);
    }
}
