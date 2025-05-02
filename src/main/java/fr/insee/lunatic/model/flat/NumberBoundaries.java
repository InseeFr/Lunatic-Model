package fr.insee.lunatic.model.flat;

import lombok.Getter;
import lombok.Setter;

/**
 * Object that holds min/max number properties for a BodyCell "InputNumber" component.
 */
@Getter
@Setter
class NumberBoundaries implements Boundaries {
    private Double min;
    private Double max;
}
