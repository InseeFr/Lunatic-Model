package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({
        "name",
        "componentRef"
})
@Getter
@Setter
public abstract class IVariableType {

    @JsonProperty(required = true)
    protected String name;
    protected String componentRef;
    protected VariableTypeEnum variableType;

}
