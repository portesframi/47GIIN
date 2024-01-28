package modelo;

import java.util.ArrayList;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */
public class ConceptoMultiple extends Concepto {

    private String idConceptoMultiple;
    private ArrayList conceptoMultiple;

    /**
     * Crea un concepto multiple
     */
    public ConceptoMultiple() {
    }

    /** Atributos del concepto multiple
     * 
     * @param idConceptoMultiple tipo
     * @param concepto multiple
     */
    public ConceptoMultiple(String idConceptoMultiple, ArrayList concepto) {
        this.idConceptoMultiple = idConceptoMultiple;
        this.conceptoMultiple = concepto;
    }

    /** Obtiene el valor de id concepto multiple
     * 
     * @return get
     */
    public String getIdConceptoMultiple() {
        return idConceptoMultiple;
    }

    /** establece el valor de id concepto multiple
     * 
     * @param idConceptoMultiple set
     */
    public void setIdConceptoMultiple(String idConceptoMultiple) {
        this.idConceptoMultiple = idConceptoMultiple;
    }

    /** Obtiene el valor de concepto multiple
     *
     * @return get
     */
    public ArrayList getConceptoMultiple() {
        return conceptoMultiple;
    }

    /** Establece el valor de concepto multiple
     * 
     * @param conceptoMultiple set
     */
    public void setConceptoMultiple(ArrayList conceptoMultiple) {
        this.conceptoMultiple = conceptoMultiple;
    }
}
