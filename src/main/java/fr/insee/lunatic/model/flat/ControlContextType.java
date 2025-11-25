package fr.insee.lunatic.model.flat;

/**
 * Enum to quality the context in which the control is interpreted.
 * Determines the behavior of the control in its context.
 */
public enum ControlContextType {

    /** Default type.
     * Note: a roundabout control is a 'SIMPLE' control in a roundabout component. */
    SIMPLE,

    /** Control at row level of a roster for loop (i.e. dynamic table). */
    ROW

}
