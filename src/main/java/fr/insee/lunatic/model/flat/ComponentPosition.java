package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum to define where a component should be displayed in a page.
 */
public enum ComponentPosition {

    /** Equivalent to a null value. This value is there to indicate explicitly that the position option is at default
     * (a null value is implicit). */
    DEFAULT("default"),

    /** The component should be displayed on the bottom of the page. */
    BOTTOM("bottom");

    @JsonValue
    private final String value;

    @JsonCreator
    ComponentPosition(String value) {
        this.value = value;
    }

    public static ComponentPosition fromValue(String value) {
        for (ComponentPosition position : ComponentPosition.values()) {
            if (value.equals(position.value))
                return position;
        }
        throw new IllegalArgumentException(value);
    }

}
