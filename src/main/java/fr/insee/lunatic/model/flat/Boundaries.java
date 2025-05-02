package fr.insee.lunatic.model.flat;

/**
 * Workaround interface to manage min/max properties in the BodyCell object.
 * @see BodyCell
 */
interface Boundaries {
    Object getMin();
    Object getMax();
}
