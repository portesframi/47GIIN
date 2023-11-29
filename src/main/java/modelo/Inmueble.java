package modelo;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */
// Clase para almacenar los inmuebles
public class Inmueble {
    
    private String id;
    private String domicilio;
    private long numero;
    private String vecino;
    private String idVecino;
    private float metros;

    /**
     * Crea inmuebles
     */
    public Inmueble() {
    }

    /** Atributos de los inmuebles
     *
     * @param id inmuebles
     * @param domicilio inmuebles
     * @param numero inmuebles
     * @param vecino inmuebles
     * @param idVecino inmuebles
     * @param metros inmuebles
     */
    public Inmueble(String id, String domicilio, long numero, String vecino, String idVecino, float metros) {
        this.id = id;
        this.domicilio = domicilio;
        this.numero = numero;
        this.vecino = vecino;
        this.idVecino = idVecino;
        this.metros = metros;
    }

    /** Obtiene el valor
     *
     * @return get
     */ 
    public String getId() {
        return id;
    }

    /**Establece del valor
     *
     * @param id set
     */
    public void setId(String id) {
        this.id = id;
    }

    /** Obtiene el valor
     *
     * @return get
     */
    public String getDomicilio() {
        return domicilio;
    }

    /** Establece del valor
     *
     * @param domicilio setr
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    /** Obtiene el valor
     *
     * @return get
     */
    public long getNumero() {
        return numero;
    }

    /**Establece del valor
     *
     * @param numero sert
     */
    public void setNumero(long numero) {
        this.numero = numero;
    }

    /** Obtiene el valor
     *
     * @return get
     */
    public String getVecino() {
        return vecino;
    }

    /**  Establece del valor
     *
     * @param vecino set
     */
    public void setVecino(String vecino) {
        this.vecino = vecino;
    }

    /** Obtiene el valor
     *
     * @return get
     */
    public String getIdVecino() {
        return idVecino;
    }

    /** Establece del valor
     *
     * @param idVecino set
     */
    public void setIdVecino(String idVecino) {
        this.idVecino = idVecino;
    }

    /**Obtiene el valor
     *
     * @return get
     */
    public float getMetros() {
        return metros;
    }

    /** Establece del valor
     *
     * @param metros set
     */
    public void setMetros(float metros) {
        this.metros = metros;
    }

   
}
