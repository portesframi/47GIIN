package modelo;

import java.util.Date;

/** Proyecto asignatura Proyectos de programación 
 *
 * @author frami
 */

// Clase liquidacion
public class Liquidacion {
    
    private String id;
    private Date fechaIncio;
    private Date fechaFin;
    private String detalle;
    private String periodoTrimestral;

    /**
     * permite crear la liquidación
     */
    public Liquidacion() {
    }

    /**Atributos especificos de esta clase
     * 
     * @param id liquidación
     * @param fechaIncio liquidación
     * @param fechaFin liquidación
     * @param detalle liquidación
     * @param periodoTrimestral liquidación
     */
    public Liquidacion(String id, Date fechaIncio, Date fechaFin, String detalle, String periodoTrimestral) {
        this.id = id;
        this.fechaIncio = fechaIncio;
        this.fechaFin = fechaFin;
        this.detalle = detalle;
        this.periodoTrimestral = periodoTrimestral;
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

    /**Obtiene el valor
     *
     * @return get
     */
    public Date getFechaIncio() {
        return fechaIncio;
    }
 
    /** Establece el valor
     *
     * @param fechaIncio set
     */
    public void setFechaIncio(Date fechaIncio) {
        this.fechaIncio = fechaIncio;
    }

    /** Obtiene el valor
     *
     * @return get
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /** Establece el valor
     *
     * @param fechaFin set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /** Obtiene el valor
     *
     * @return get
     */
    public String getDetalle() {
        return detalle;
    }

    /**Establece el valor
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
    public String getPeriodoTrimestral() {
        return periodoTrimestral;
    }

    /**Establece el valor
     *
     * @param periodoTrimestral set
     */
    public void setPeriodoTrimestral(String periodoTrimestral) {
        this.periodoTrimestral = periodoTrimestral;
    }
}
