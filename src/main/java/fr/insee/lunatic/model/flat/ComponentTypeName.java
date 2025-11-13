package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ComponentTypeName {

    QUESTIONNAIRE("Questionnaire"),
    SEQUENCE("Sequence"),
    SUBSEQUENCE("Subsequence"),
    QUESTION("Question"),
    ROSTER_FOR_LOOP("RosterForLoop"),
    LOOP("Loop"),
    ROUNDABOUT("Roundabout"),
    TABLE("Table"),
    INPUT("Input"),
    PAIRWISE_LINKS("PairwiseLinks"),
    INPUT_NUMBER("InputNumber"),
    DATEPICKER("Datepicker"),
    DURATION("Duration"),
    CHECKBOX_GROUP("CheckboxGroup"),
    CHECKBOX_ONE("CheckboxOne"),
    CHECKBOX_BOOLEAN("CheckboxBoolean"),
    RADIO("Radio"),
    DROPDOWN("Dropdown"),
    TEXTAREA("Textarea"),
    SUGGESTER("Suggester"),
    TEXT("Text"),
    FILTER_DESCRIPTION("FilterDescription"),
    ACCORDION("Accordion");

    private final String value;

    ComponentTypeName(String v) {
        value = v;
    }

    @JsonValue
    public String value() {
        return value;
    }

    @JsonCreator
    public static ComponentTypeName fromValue(String v) {
        for (ComponentTypeName c: ComponentTypeName.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
