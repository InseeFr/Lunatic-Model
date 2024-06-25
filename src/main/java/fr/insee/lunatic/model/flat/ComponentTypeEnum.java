package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ComponentTypeEnum {

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
    ACCORDION("Accordion");

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
