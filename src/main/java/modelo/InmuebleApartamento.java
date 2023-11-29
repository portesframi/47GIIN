package modelo;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */

// Clase hija inmueble apartamente que extiende a la clase inmueble.
public class InmuebleApartamento extends Inmueble{
    
    private String idInmuebleApartamento;
    private String piso;
    private String puerta;

    /**
     * Permite crear el inmueble apartamento
     */
    public InmuebleApartamento() {
    }
    
    /**Atributos especificos de esta clase
     * 
     * @param idInmuebleApartamento inmueble apartamento
     * @param piso inmueble apartamento 
     * @param puerta inmueble apartamento
     */
    public InmuebleApartamento(String idInmuebleApartamento, String piso, String puerta) {
        this.idInmuebleApartamento = idInmuebleApartamento;
        this.piso = piso;
        this.puerta = puerta;
    }

    /**Obtiene el valor
     *
     * @return get
     */
    public String getIdInmuebleApartamento() {
        return idInmuebleApartamento;
    }

    /** Establece el valor
     *
     * @param idInmuebleApartamento set
     */
    public void setIdInmuebleApartamento(String idInmuebleApartamento) {
        this.idInmuebleApartamento = idInmuebleApartamento;
    }

    /** Obtiene el valor
     *
     * @return get
     */
    public String getPiso() {
        return piso;
    }

    /**Establece el valor
     *
     * @param piso set
     */
    public void setPiso(String piso) {
        this.piso = piso;
    }

    /** Obtiene el valor
     *
     * @return get
     */
    public String getPuerta() {
        return puerta;
    }

    /** Establece el valor
     *
     * @param puerta set
     */
    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }
}
