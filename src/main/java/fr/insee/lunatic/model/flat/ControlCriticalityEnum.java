package fr.insee.lunatic.model.flat;

/**
 * Enum to qualify the criticality level of a control.
 * Determines how the control is displayed, and if it is blocking or not.
 */
public enum ControlCriticalityEnum {

    /** Informative control. */
    INFO,

    /** Warning control. */
    WARN,

    /** Error control, blocking. */
    ERROR

}
