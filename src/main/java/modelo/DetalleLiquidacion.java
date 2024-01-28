package modelo;

/** Asignatura Proyecto de ingenieria de software 
 *
 * @author frami
 */

// Clase para definir el detalle de la liquidacion en funcion del vecino, inmueble y servicio suscrito
public class DetalleLiquidacion {

    /*
     RELACION UNICA
     */
    private String idInmueble;
    private String nombreInmueble;
    private String idUsuario;
    private String nombreUsuario;//Viene a ser el Vecino
    private String idServicio;
    private String nombreSuscripcionServicio;
    /*
     RELACION UNICA
     */

 /*
     FUSIÓN DE LA FUSIÓN 
     */
    private String nombre;
    /*
     FUSIÓN DE LA FUSIÓN 
     */

 /*
     VALORES DE LA RELACIÓN
     */
    private double importeFinal;
    private String concepto;
    private String periodoLiquidacion;

    /*
     VALORES DE LA RELACIÓN
     */

    /**
     * Define el detalle de la liquidacion a calcular
     */

    public DetalleLiquidacion() {
    }

    /** Atributos del detalle liquidacion
     * 
     * @param idInmueble  detalle liquidacion
     * @param nombreInmueble detalle liquidacion
     * @param idUsuario detalle liquidacion
     * @param nombreUsuario detalle liquidacion
     * @param idServicio detalle liquidacion
     * @param nombreSuscripcionServicio detalle liquidacion
     * @param nombre detalle liquidacion
     * @param importeFinal detalle liquidacion
     * @param concepto detalle liquidacion
     * @param periodoLiquidacion detalle liquidacion
     */
    public DetalleLiquidacion(String idInmueble, String nombreInmueble, String idUsuario, String nombreUsuario, String idServicio, String nombreSuscripcionServicio, String nombre, double importeFinal, String concepto, String periodoLiquidacion) {
        this.idInmueble = idInmueble;
        this.nombreInmueble = nombreInmueble;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.idServicio = idServicio;
        this.nombreSuscripcionServicio = nombreSuscripcionServicio;
        this.nombre = nombre;
        this.importeFinal = importeFinal;
        this.concepto = concepto;
        this.periodoLiquidacion = periodoLiquidacion;
    }

    /** Obtiene el valor
     *
     * @return get
     */
    public String getIdInmueble() {
        return idInmueble;
    }

    /**Establece del valor
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
    public String getNombreInmueble() {
        return nombreInmueble;
    }

    /** Establece del valor
     *
     * @param nombreInmueble set
     */
    public void setNombreInmueble(String nombreInmueble) {
        this.nombreInmueble = nombreInmueble;
    }

    /** Obtiene el valor
     *
     * @return get
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /** Establece del valor
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
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /** Establece del valor
     *
     * @param nombreUsuario set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /** Obtiene el valor
     *
     * @return get
     */
    public String getIdServicio() {
        return idServicio;
    }

    /** Establece del valor
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
    public String getNombreSuscripcionServicio() {
        return nombreSuscripcionServicio;
    }

    /** Establece del valor
     *
     * @param nombreSuscripcionServicio set
     */
    public void setNombreSuscripcionServicio(String nombreSuscripcionServicio) {
        this.nombreSuscripcionServicio = nombreSuscripcionServicio;
    }

    /** Obtiene el valor
     *
     * @return get
     */
    public String getNombre() {
        return nombre;
    }

    /** Establece del valor
     *
     * @param nombre sert
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** Obtiene el valor
     *
     * @return get
     */
    public double getImporteFinal() {
        return importeFinal;
    }

    /** Establece del valor
     *
     * @param importeFinal set
     */
    public void setImporteFinal(double importeFinal) {
        this.importeFinal = importeFinal;
    }

    /** Obtiene el valor
     * 
     * @return get
     */
    public String getConcepto() {
        return concepto;
    }

    /** Establece del valor
     *
     * @param concepto set
     */
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    /** Obtiene el valor
     *
     * @return get
     */
    public String getPeriodoLiquidacion() {
        return periodoLiquidacion;
    }

    /** Establece del valor
     *
     * @param periodoLiquidacion set
     */
    public void setPeriodoLiquidacion(String periodoLiquidacion) {
        this.periodoLiquidacion = periodoLiquidacion;
    }
}
