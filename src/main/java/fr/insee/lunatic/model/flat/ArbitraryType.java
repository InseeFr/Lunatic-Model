package fr.insee.lunatic.model.flat;

import lombok.Getter;
import lombok.Setter;

/**
 * Object for the suggester component for the arbitrary response feature.
 */
@Getter
@Setter
public class ArbitraryType {

    /** Name of the variable in which the arbitrary response is stored. */
    private ResponseType response;

    /** Label displayed that correspond to the 'arbitrary' choice in the suggestions. */
    private LabelType label;

    /** Label of the input filed when arbitrary response is chosen. */
    private LabelType inputLabel;
}
