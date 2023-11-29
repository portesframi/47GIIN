package modelo;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */
public class Administrador extends Usuario {

    private String idAdministrador;
    private String alta;
    private String baja;
    private String modificado;

    /**
     * Creación del administrador
     */
    public Administrador() {
    }

    /** Atributos del administrador
     * 
     * @param idAdministrador identificador del administrador
     * @param alta alta del administrador
     * @param baja baja del administrador 
     * @param modificado indica si el administrador se ha modificado
     */
    public Administrador(String idAdministrador, String alta, String baja, String modificado) {
        this.idAdministrador = idAdministrador;
        this.alta = alta;
        this.baja = baja;
        this.modificado = modificado;
    }

    /** Obtiene la id del administrador
     * 
     * @return Obtiene la id del administrador
     */
    public String getIdAdministrador() {
        return idAdministrador;
    }

    /** Establece el valor de id administrador
     * 
     * @param idAdministrador Establece el valor de id administrador
     */
    public void setIdAdministrador(String idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    /** Obtiene el valor de alta
     * 
     * @return Obtiene el valor de alta
     */
    public String getAlta() {
        return alta;
    }

    /**Establece el valor de alta
     * 
     * @param alta Establece el valor de alta
     */
    public void setAlta(String alta) {
        this.alta = alta;
    }

    /** Obtiene el valor de baja
     *
     * @return Obtiene el valor de baja
     */
    public String getBaja() {
        return baja;
    }

    /** Establece el valor de baja
     *  
     * @param baja Establece el valor de baja
     */
    public void setBaja(String baja) {
        this.baja = baja;
    }

    /** Obtiene el valor de modificado
     * 
     * @return Obtiene el valor de modificado
     */
    public String getModificado() {
        return modificado;
    }

    /** Establece del valor de modificado
     * 
     * @param modificado Establece del valor de modificado
     */
    public void setModificado(String modificado) {
        this.modificado = modificado;
    }
}
