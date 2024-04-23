package fr.insee.lunatic.model.flat;

public enum ControlTypeEnum {

    /** Control automatically generated from questionnaire's metadata.
     * Example: control that the user input is a number between the min and the max for a number component. */
    FORMAT,

    /** Business-level control defined bt the questionnaire's designer. */
    CONSISTENCY,

    /** Control to be applied at the line level of a roster for loop (i.e. dynamic table) component. */
    ROW

}
