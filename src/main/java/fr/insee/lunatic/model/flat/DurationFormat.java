package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Duration format.
 * When serialized, it is written in the XSD Duration Data Type style.
 * For now, only specific cases are allowed in Lunatic, these are defined by this enum.
 */
public enum DurationFormat {

    /*
    Note on the XSD Duration Data Type style:
    Must start with a 'P' (that stands for period). Then can be followed by 'nY' (years), 'nM' (months), 'nD' (days).
    'T' indicates the start of a time section that can be followed by 'nH' (hours), 'nM' (minutes), 'nS' (seconds).
    */

    YEARS_MONTHS("PnYnM"),
    HOURS_MINUTES("PTnHnM");

    private final String value;

    DurationFormat(String v) {
        value = v;
    }

    @JsonValue
    public String value() {
        return value;
    }

    @JsonCreator
    public static DurationFormat fromValue(String v) {
        for (DurationFormat c: DurationFormat.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
