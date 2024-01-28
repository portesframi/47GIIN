package modelo;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */
public class ConceptoSimple extends Concepto{
    
    private String idConceptoSimple;
    private boolean esConceptoMultiple;

    /**
     * Permite crear los conceptos simple
     */
    public ConceptoSimple() {
    }

    /** Atributos del concepto compuesto
     * 
     * @param idConceptoSimple id
     * @param esConceptoMultiple define tipo de concepto
     */
    public ConceptoSimple(String idConceptoSimple, boolean esConceptoMultiple) {
        this.idConceptoSimple = idConceptoSimple;
        this.esConceptoMultiple = esConceptoMultiple;
    }

    /** Obtiene el valor de id concepto simple
     *
     * @return get
     */
    public String getIdConceptoSimple() {
        return idConceptoSimple;
    }

    /** Establece el valor de id concepto simple
     * 
     * @param idConceptoSimple set
     */
    public void setIdConceptoSimple(String idConceptoSimple) {
        this.idConceptoSimple = idConceptoSimple;
    }

    /** Obtiene el valor que confirma que es concepto simple
     * 
     * @return set
     */
    public boolean getIsEsConceptoMultiple() {
        return esConceptoMultiple;
    }

    /** Establece el valor que confirma que es concepto simple
     * 
     * @param esConceptoMultiple set
     */
    public void setEsConceptoMultiple(boolean esConceptoMultiple) {
        this.esConceptoMultiple = esConceptoMultiple;
    }
}
