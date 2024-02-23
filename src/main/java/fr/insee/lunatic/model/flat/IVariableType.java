package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({
        "name",
        "variableType",
        "componentRef",
        "variableDimension",
        "value",
        "values",
        "expression",
        "bindingDependencies",
        "shapeFrom",
        "inFilter"
})
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "variableDimension",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = VariableType.class, name = "0"),
        @JsonSubTypes.Type(value = VariableTypeArray.class, name = "1"),
        @JsonSubTypes.Type(value = VariableTypeTwoDimensionsArray.class, name = "2")
})
@Getter
@Setter
public abstract class IVariableType {

    // TODO: make a proper modeling for collected/external/calculated variables

    @JsonProperty(required = true)
    protected String name;

    /**
     * Response name associated with the variable.
     * @deprecated Not used by Lunatic anymore.
     */
    @Deprecated(since = "3.3.5")
    protected String componentRef;

    @JsonProperty(required = true)
    protected VariableTypeEnum variableType;

    /** Property required to deserialize variable objects. */
    @JsonProperty(required = true)
    protected VariableDimension variableDimension;

}
