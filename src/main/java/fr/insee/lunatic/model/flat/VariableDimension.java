package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum VariableDimension {

    /** Variable is a scalar point. */
    SCALAR("0"),

    /** Variable is a vector. */
    ARRAY("1"),

    /** Variable is a two dimensions vector. */
    DOUBLE_ARRAY("2");

    private final String label;

    VariableDimension(String label) {
        this.label = label;
    }

    @JsonValue
    public String label() {
        return this.label;
    }

    @JsonCreator
    public VariableDimension fromLabel(String label) {
        return switch (label) {
            case "0" -> SCALAR;
            case "1" -> ARRAY;
            case "2" -> DOUBLE_ARRAY;
            default -> throw new IllegalArgumentException("Variable dimension '" + label + "' is not allowed");
        };
    }

}
