package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonSubTypes({
        @JsonSubTypes.Type(value = NumberBoundaries.class, name = "InputNumber"),
        @JsonSubTypes.Type(value = DateBoundaries.class, name = "Datepicker")
})
public interface Boundaries {
    Object getMin();
    Object getMax();

    @JsonIgnore
    ComponentTypeEnum getComponentType();
}
