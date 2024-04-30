package fr.insee.lunatic.model.flat.variable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Describes the format of the data associated with the variable.
 */
public enum VariableDimension {

    /** Questionnaire-level variables are scalar. */
    SCALAR(0),

    /** Loop or RosterForLoop (dynamic array) variables are arrays. */
    ARRAY(1),

    /** PairwiseLinks variable is a two-dimensions array. (No other variables are since nested loops are forbidden.) */
    DOUBLE_ARRAY(2);

    private final Integer value;

    VariableDimension(Integer v) {
        value = v;
    }

    @JsonValue
    public Integer value() {
        return value;
    }

    @JsonCreator
    public static VariableDimension fromValue(Integer v) {
        for (VariableDimension c: VariableDimension.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
