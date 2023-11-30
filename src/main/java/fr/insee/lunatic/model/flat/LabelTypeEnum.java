package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonValue;

public enum LabelTypeEnum {

    /** Label that is a VTL expression and contains Markdown formatting. */
    VTL_MD("VTL|MD"),
    /** Label that is a VTL expression. */
    VTL("VTL");

    private final String value;

    LabelTypeEnum(String v) {
        value = v;
    }

    @JsonValue
    public String value() {
        return value;
    }

    public static LabelTypeEnum fromValue(String v) {
        for (LabelTypeEnum c : LabelTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
