package fr.insee.lunatic.model.flat;

/**
 * Enum to quality the context in which the control is interpreted.
 * Determines the behavior of the control in its context.
 */
public enum ControlContextType {

    /** Default type. */
    SIMPLE,

    /** Control at row level of a roster for loop (i.e. dynamic table). */
    ROW,

    /** Special type of control for roundabouts.
     * @deprecated Should not be used, a roundabout control is a 'SIMPLE' control in a roundabout component. */
    @Deprecated(since = "3.15.3", forRemoval = true)
    ROUNDABOUT

}
