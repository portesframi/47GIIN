package modelo;

/** Asignatura Proyecto de ingenieria de software 
 *
 * @author frami
 */

// Clase que define el usuario de la aplicacion
public class Usuario {

    private String id;
    private String usuario;
    private String contraseña;
    private String dni;
    private String nombre;
    private String tipo;
    private String telefono;
    private String email;
    private long fechaAlta;

    /**
     * Crea el usuario
     */
    public Usuario() {
    }

    /** Atributos especificos de esta clase
     * 
     * @param id Identificador de usuario
     * @param usuario Usuario del sistema
     * @param contraseña contraseña del usuario
     * @param dni dni del usuario
     * @param nombre nombre del usuario
     * @param tipo tipo de usurio si vecino o administrador
     * @param telefono telefono de usuario
     * @param email email del usuario
     * @param fechaAlta fecha que se da de alta el usuario
     */
    public Usuario(String id, String usuario, String contraseña, String dni, String nombre, String tipo, String telefono, String email, long fechaAlta) {
        this.id = id;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.dni = dni;
        this.nombre = nombre;
        this.tipo = tipo;
        this.telefono = telefono;
        this.email = email;
        this.fechaAlta = fechaAlta;
    }

    /** get id usuario
     *
     * @return devuelve id usuario
     */
    public String getId() {
        return id;
    }

    /** set id
     *
     * @param id establece id usuario
     */
    public void setId(String id) {
        this.id = id;
    }

    /** get
     *
     * @return devuelve usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /** set
     *
     * @param usuario establece usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /** get
     *
     * @return devuelve contraseña
     */
    public String getContraseña() {
        return contraseña;
    }

    /** set
     *
     * @param contraseña establece contraseña
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /** get
     *
     * @return devuelve dni usuario
     */
    public String getDni() {
        return dni;
    }

    /** set
     *
     * @param dni establece dni usuario
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /** get
     *
     * @return devuelve nombre usuario
     */
    public String getNombre() {
        return nombre;
    }

    /** set
     *
     * @param nombre establece nombre usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** get
     *
     * @return devuelve el tipo de usuario
     */
    public String getTipo() {
        return tipo;
    }

    /** set
     *
     * @param tipo establece el tipo de usuario
     */
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /** get
     *
     * @return devuelve el telefono del usuario
     */
    public String getTelefono() {
        return telefono;
    }

    /** set
     *
     * @param telefono establece el telefono del usuario
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /** get
     *
     * @return devuelve el email del usuario
     */
    public String getEmail() {
        return email;
    }

    /** set
     *
     * @param email establece el email del usuario
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /** get
     *
     * @return devuelve la fecha de alta del usuario
     */
    public long getFechaAlta() {
        return fechaAlta;
    }

    /** set
     *
     * @param fechaAlta establece la fecha de alta del usuario
     */
    public void setFechaAlta(long fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /** Define el usuario como administrador
     * 
     * @return crea el usuario como administrador
     */
    public Administrador convertirAAdministrador() {
        Administrador administrador = new Administrador();
        administrador.setUsuario(this.getUsuario());
        administrador.setContraseña(this.getContraseña());
        administrador.setDni(this.getDni());
        administrador.setNombre(this.getNombre());
        administrador.setTipo(this.getTipo());
        administrador.setTelefono(this.getTelefono());
        administrador.setEmail(this.getEmail());
        // Otras propiedades específicas de Administrador si las hay
        return administrador;
    }

    /** Define el usuario como vecino
     * 
     * @return crea el usuaro como vecino
     */
    public Vecino convertirAVecino() {
        Vecino vecino = new Vecino();
        vecino.setUsuario(this.getUsuario());
        vecino.setContraseña(this.getContraseña());
        vecino.setDni(this.getDni());
        vecino.setNombre(this.getNombre());
        vecino.setTipo(this.getTipo());
        vecino.setTelefono(this.getTelefono());
        vecino.setEmail(this.getEmail());
        // Otras propiedades específicas de Vecino si las hay
        return vecino;
    }
}
