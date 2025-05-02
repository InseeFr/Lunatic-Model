package fr.insee.lunatic.model.flat;

import lombok.Getter;
import lombok.Setter;

/**
 * Object that holds min/max string properties for a BodyCell "Datepicker" or "Duration" component.
 */
@Getter
@Setter
class StringBoundaries implements Boundaries {
    private String min;
    private String max;
}
