package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Orientation of the response modalities of a radio or checkbox component.
 */
public enum Orientation {

    /** Vertical orientation, which is the default. */
    VERTICAL("vertical"),

    /** Horizontal orientation, to be used for radio or checkbox within a table for instance. */
    HORIZONTAL("horizontal");

    final String value;

    Orientation(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }

    @JsonCreator
    public static Orientation fromValue(String value) {
        for (Orientation orientation: Orientation.values()) {
            if (orientation.value.equals(value)) {
                return orientation;
            }
        }
        throw new IllegalArgumentException(value);
    }

}
