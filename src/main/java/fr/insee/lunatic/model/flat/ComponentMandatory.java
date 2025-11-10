package fr.insee.lunatic.model.flat;

/**
 * Interface for components that support a "mandatory" property.
 * Allows external processors (e.g. Eno) to apply generic logic
 * without having to check specific component types.
 */
public interface ComponentMandatory {
    Boolean getMandatory();
    void setMandatory(Boolean value);
}
