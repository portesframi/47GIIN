package modelo;

import java.util.List;

/** Proyecto asignatura Proyectos de programación 
 *
 * @author frami
 */
public class Concepto {
    
    private String id;
    private String nombre;

    /**
     * crea el concepto
     */
    public Concepto() {
    }

    /** Atributos del concepto
     * 
     * @param id id
     * @param nombre nombre
     */
    public Concepto(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    /** Obtiene el valor de id concepto
     *
     * @return devuelve id
     */
    public String getId() {
        return id;
    }

    /** set
     * 
     * @param id Establece del valor de id concepto
     */
    public void setId(String id) {
        this.id = id;
    }

    /**get
     * 
     * @return Obtiene el valor de nombre
     */
    public String getNombre() {
        return nombre;
    }

    /** set
     * 
     * @param nombre Establece del valor de nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
