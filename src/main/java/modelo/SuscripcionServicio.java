package modelo;

import java.util.Date;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */

//Clase que permite crear las suscripciones de los inmuebles a los servicios para cada usuario
public class SuscripcionServicio {
    
    private String idSuscripcion;
    private String usuario;
    private String servicio;
    private String Inmueble;
    private String idUsuario;
    private String idServicio;
    private String idInmueble;
    private Date fechaAlta;
    private Date fechaBaja;
    private boolean isActiva;

    /**
     * Crea la suscripcion
     */
    public SuscripcionServicio() {
    }

    /**Atributos especificos de esta clase
     * 
     * @param idSuscripcion suscripcion
     * @param usuario suscripcion
     * @param servicio suscripcion
     * @param Inmueble suscripcion
     * @param idUsuario suscripcion
     * @param idServicio suscripcion
     * @param idInmueble suscripcion
     * @param fechaAlta suscripcion
     * @param fechaBaja suscripcion
     * @param isActiva suscripcion
     */
    public SuscripcionServicio(String idSuscripcion, String usuario, String servicio, String Inmueble, String idUsuario, String idServicio, String idInmueble, Date fechaAlta, Date fechaBaja, boolean isActiva) {
        this.idSuscripcion = idSuscripcion;
        this.usuario = usuario;
        this.servicio = servicio;
        this.Inmueble = Inmueble;
        this.idUsuario = idUsuario;
        this.idServicio = idServicio;
        this.idInmueble = idInmueble;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.isActiva = isActiva;
    }

    /** Obtiene el valor 
     * 
     * @return get
     */
    public String getIdSuscripcion() {
        return idSuscripcion;
    }

    /**Establece el valor
     *
     * @param idSuscripcion set
     */
    public void setIdSuscripcion(String idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    /** Obtiene el valor 
     * 
     * @return get
     */
    public String getUsuario() {
        return usuario;
    }

    /** Establece el valor
     *
     * @param usuario set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /** Obtiene el valor 
     * 
     * @return get
     */
    public String getServicio() {
        return servicio;
    }

    /** Establece el valor
     *
     * @param servicio set
     */
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    /** Obtiene el valor 
     * 
     * @return get
     */
    public String getInmueble() {
        return Inmueble;
    }

    /** Establece el valor
     *
     * @param Inmueble set
     */
    public void setInmueble(String Inmueble) {
        this.Inmueble = Inmueble;
    }

    /** Obtiene el valor 
     * 
     * @return get
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /** Establece el valor
     *
     * @param idUsuario set
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /** Obtiene el valor 
     * 
     * @return get
     */
    public String getIdServicio() {
        return idServicio;
    }

    /** Establece el valor
     *
     * @param idServicio set
     */
    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }

    /** Obtiene el valor 
     * 
     * @return get
     */
    public String getIdInmueble() {
        return idInmueble;
    }

    /**Establece el valor
     *
     * @param idInmueble set
     */
    public void setIdInmueble(String idInmueble) {
        this.idInmueble = idInmueble;
    }

    /** Obtiene el valor 
     * 
     * @return get
     */
    public Date getFechaAlta() {
        return fechaAlta;
    }

    /**Establece el valor
     *
     * @param fechaAlta set
     */
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /** Obtiene el valor 
     * 
     * @return get
     */
    public Date getFechaBaja() {
        return fechaBaja;
    }

    /** Establece el valor
     *
     * @param fechaBaja set
     */
    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    /** Obtiene el valor 
     * 
     * @return get
     */
    public boolean getIsActiva() {
        return isActiva;
    }

    /** Establece el valor
     *
     * @param isActiva set
     */
    public void setIsActiva(boolean isActiva) {
        this.isActiva = isActiva;
    }

   
    
}
