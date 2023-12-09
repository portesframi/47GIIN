package modelo;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */

//define la clase de servicio de la comunidad
public class Servicio {
 
    private String id;
    private String nombre;
    private String detalle;
    private boolean isObligatorio;

    /**
     * crea el servicio
     */
    public Servicio() {
    }
    
    /** Atributos especificos de esta clase
     * 
     * @param id servicio
     * @param nombre servicio
     * @param detalle servicio
     * @param isObligatorio servicio
     */
    public Servicio(String id, String nombre, String detalle, boolean isObligatorio) {
        this.id = id;
        this.nombre = nombre;
        this.detalle = detalle;
        this.isObligatorio = isObligatorio;
    }

    /** Obtiene el valor 
     * 
     * @return get
     */
    public String getId() {
        return id;
    }

    /** Establece el valor
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
    public String getNombre() {
        return nombre;
    }

    /** Establece el valor
     *
     * @param nombre set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** Obtiene el valor 
     * 
     * @return get
     */
    public String getDetalle() {
        return detalle;
    }

    /** Establece el valor
     *
     * @param detalle set
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /** Obtiene el valor 
     * 
     * @return get
     */
    public boolean getIsObligatorio() {
        return isObligatorio;
    }

    /** Establece el valor
     *
     * @param isObligatorio set
     */
    public void setIsObligatorio(boolean isObligatorio) {
        this.isObligatorio = isObligatorio;
    }
}
