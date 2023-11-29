package modelo;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */

// Clase hija inmueble casa que extiende a la clase inmueble.
public class InmuebleCasa extends Inmueble{
    
    private String idInmuebleCasa;
    private String parcela;

    /** Obtiene el valor
     * 
     * @return get
     */
    public String getIdInmuebleCasa() {
        return idInmuebleCasa;
    }

    /** Establece el valor
     *
     * @param idInmuebleCasa set
     */
    public void setIdInmuebleCasa(String idInmuebleCasa) {
        this.idInmuebleCasa = idInmuebleCasa;
    }

    /** Obtiene el valor
     *
     * @return get
     */
    public String getParcela() {
        return parcela;
    }

    /** Establece el valor
     *
     * @param parcela set
     */
    public void setParcela(String parcela) {
        this.parcela = parcela;
    }
}
