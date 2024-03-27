package fr.insee.lunatic.model.flat.variable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * Lunatic-Model variable.
 */
@JsonPropertyOrder({
        "name",
        "variableType",
        "componentRef",
        "value",
        "values",
        "expression",
        "bindingDependencies",
        "shapeFrom"
})
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "variableType",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CollectedVariableType.class, name = "COLLECTED"),
        @JsonSubTypes.Type(value = ExternalVariableType.class, name = "EXTERNAL"),
        @JsonSubTypes.Type(value = CalculatedVariableType.class, name = "CALCULATED")
})
@Getter
@Setter
public abstract class VariableType {

    /** Business name of the variable, that correspond to the response name used in components. */
    @JsonProperty(required = true)
    protected String name;

    /** {@link VariableTypeEnum} */
    @JsonProperty(required = true)
    protected VariableTypeEnum variableType;

}
