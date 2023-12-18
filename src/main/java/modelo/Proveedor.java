package modelo;

/** Asignatura Proyecto de ingenieria de software 
 *
 * @author frami
 */

//define la clase del proveedor de facturas
public class Proveedor {
    
    private String id;
    private String nombre;
    private String telefono;
    private String email;
    private String direccion;

    /**
     * Crea el proveedor
     */
    public Proveedor() {
    }

    /** Atributos especificos de esta clase
     * 
     * @param id proveedor
     * @param nombre proveedor
     * @param telefono proveedor
     * @param email proveedor
     * @param direccion proveedor
     */
    public Proveedor(String id, String nombre, String telefono, String email, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    /** Obtiene el valor 
     * 
     * @return get
     */
    public String getId() {
        return id;
    }

    /**Establece el valor
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
    public String getTelefono() {
        return telefono;
    }

    /** Establece el valor
     *
     * @param telefono set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /** Obtiene el valor 
     * 
     * @return get
     */
    public String getEmail() {
        return email;
    }

    /** Establece el valor
     *
     * @param email set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /** Obtiene el valor  
     * 
     * @return get
     */
    public String getDireccion() {
        return direccion;
    }

    /**Establece el valor
     *
     * @param direccion set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }   
}
