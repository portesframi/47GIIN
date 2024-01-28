package modelo;

import java.util.Date;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */
// clase para registrar el gasto de la comunidad

public class Gasto {

    private int id;
    private double importe;
    private Date fechaRegistro;
    private Date fechaPago;
    private String numeroComprobante;
    private String proveedor;
    private String concepto;
    private String servicio;
    private String liquidacion;
    private String idServicio;

    /**
     * Crea el gasto para almacenarlo.
     */
    public Gasto() {
    }

    /** Atributos del gasto
     * 
     * @param id  gasto
     * @param importe gasto
     * @param fechaRegistro gasto
     * @param fechaPago gasto
     * @param numeroComprobante gasto
     * @param proveedor gasto
     * @param concepto gasto
     * @param servicio gasto
     * @param liquidacion gasto
     * @param idServicio gasto
     */
    public Gasto(int id, double importe, Date fechaRegistro, Date fechaPago, String numeroComprobante, String proveedor, String concepto, String servicio, String liquidacion, String idServicio) {
        this.id = id;
        this.importe = importe;
        this.fechaRegistro = fechaRegistro;
        this.fechaPago = fechaPago;
        this.numeroComprobante = numeroComprobante;
        this.proveedor = proveedor;
        this.concepto = concepto;
        this.servicio = servicio;
        this.liquidacion = liquidacion;
        this.idServicio = idServicio;
    }

    /** Obtiene el valor
     *
     * @return get
     */
    public int getId() {
        return id;
    }

    /** Establece del valor
     *
     * @param id set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**Obtiene el valor
     *
     * @return get
     */
    public double getImporte() {
        return importe;
    }

    /** Establece del valor
     *
     * @param importe set
     */
    public void setImporte(double importe) {
        this.importe = importe;
    }

    /**Obtiene el valor
     *
     * @return get
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /** Establece del valor
     *
     * @param fechaRegistro set
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**Obtiene el valor
     *
     * @return get
     */
    public Date getFechaPago() {
        return fechaPago;
    }

    /** Establece del valor
     *
     * @param fechaPago set
     */
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    /** Obtiene el valor
     *
     * @return get
     */
    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    /**Establece del valor
     *
     * @param numeroComprobante set
     */
    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    /**Obtiene el valor
     *
     * @return get
     */
    public String getProveedor() {
        return proveedor;
    }

    /** Establece del valor
     *
     * @param proveedor set
     */
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    /**Obtiene el valor
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
    public String getServicio() {
        return servicio;
    }

    /** Establece del valor
     *
     * @param servicio set
     */
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    /**Obtiene el valor
     *
     * @return gety
     */
    public String getLiquidacion() {
        return liquidacion;
    }

    /** Establece del valor
     *
     * @param liquidacion set
     */
    public void setLiquidacion(String liquidacion) {
        this.liquidacion = liquidacion;
    }

    /**Obtiene el valor
     *
     * @return get
     */
    public String getIdServicio() {
        return idServicio;
    }

    /**Establece del valor
     *  
     * @param idServicio set
     */
    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }
}
