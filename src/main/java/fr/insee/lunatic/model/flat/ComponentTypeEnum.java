package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ComponentTypeEnum {

    QUESTIONNAIRE("Questionnaire"),
    SEQUENCE("Sequence"),
    SUBSEQUENCE("Subsequence"),
    ROSTER_FOR_LOOP("RosterForLoop"),
    LOOP("Loop"),
    TABLE("Table"),
    INPUT("Input"),
    PAIRWISE_LINKS("PairwiseLinks"),
    INPUT_NUMBER("InputNumber"),
    DATEPICKER("Datepicker"),
    CHECKBOX_GROUP("CheckboxGroup"),
    CHECKBOX_ONE("CheckboxOne"),
    CHECKBOX_BOOLEAN("CheckboxBoolean"),
    RADIO("Radio"),
    DROPDOWN("Dropdown"),
    TEXTAREA("Textarea"),
    FILTER_DESCRIPTION("FilterDescription"),
    SUGGESTER("Suggester");
    private final String value;

    ComponentTypeEnum(String v) {
        value = v;
    }

    @JsonValue
    public String value() {
        return value;
    }

    @JsonCreator
    public static ComponentTypeEnum fromValue(String v) {
        for (ComponentTypeEnum c: ComponentTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
