package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Pagination mode of a questionnaire.
 * Defines how the components should be paginated (e.g. one per page, or grouped by sequence).
 */
public enum Pagination {

    /** Each question is on a single page. */
    QUESTION("question"),

    /** Questions of a same sequence are on the same page. */
    SEQUENCE("sequence"),

    /** No page number on components. */
    NONE("none");

    @JsonValue
    private final String value;

    @JsonCreator
    Pagination(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Pagination fromValue(String value) {
        for (Pagination pagination : Pagination.values()) {
            if (pagination.value.equals(value))
                return pagination;
        }
        throw new IllegalArgumentException(value);
    }

}
