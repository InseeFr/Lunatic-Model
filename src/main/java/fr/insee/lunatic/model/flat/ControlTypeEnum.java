package fr.insee.lunatic.model.flat;

/**
 * Enum to describe the origin of a control.
 */
public enum ControlTypeEnum {

    /** Control automatically generated from questionnaire's metadata in Eno generation.
     * Example: control that the user input is a number between the min and the max for a number component. */
    FORMAT,

    /** Business-level control defined by the questionnaire's designer. */
    CONSISTENCY,

    /** Control automatically generated from questionnaire's metadata in Eno generation,
     * to make a question mandatory.
     * A "mandatory" control has a specific behavior in Lunatic. */
    MANDATORY

}
