package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "componentType",
        visible = true
)
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
