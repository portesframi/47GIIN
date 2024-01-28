package modelo;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */
// Clase que permite llevar la cuenta de los inmuebles  segun su suscripcion de servicio
public class Cuenta {
    
    private String id;
    private Inmueble inmueble;
    private Servicio servicio;
    private Concepto concepto;
    private double saldo;

    /** Atributos de la cuenta
     * 
     * @param id cuenta
     * @param inmueble cuenta
     * @param servicio cuenta
     * @param concepto cuenta
     * @param saldo cuenta
     */
    public Cuenta(String id, Inmueble inmueble, Servicio servicio, Concepto concepto, double saldo) {
        this.id = id;
        this.inmueble = inmueble;
        this.servicio = servicio;
        this.concepto = concepto;
        this.saldo = saldo;
    }

    /** Obtiene el valor de id cuenta
     *
     * @return valor
     */
    public String getId() {
        return id;
    }

    /** Establece del valor de id cuenta
     * 
     * @param id set
     */
    public void setId(String id) {
        this.id = id;
    }

    /** Obtiene el valor de inmueble
     * 
     * @return get
     */
    public Inmueble getInmueble() {
        return inmueble;
    }

    /** Establece del valor de  inmueble
     * 
     * @param inmueble set
     */
    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    /** Obtiene el valor de servicio
     * 
     * @return get
     */
    public Servicio getServicio() {
        return servicio;
    }

    /** Establece el valor de  servicio
     * 
     * @param servicio set
     */
    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    /** Obtiene el valor de concepto
     * 
     * @return get
     */
    public Concepto getConcepto() {
        return concepto;
    }

    /**Establece del valor de concepto
     * 
     * @param concepto set
     */
    public void setConcepto(Concepto concepto) {
        this.concepto = concepto;
    }

    /** Obtiene el valor del saldo
     *
     * @return get
     */
    public double getSaldo() {
        return saldo;
    }

    /** Establece del valor del saldo
     * 
     * @param saldo set
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }    
}
