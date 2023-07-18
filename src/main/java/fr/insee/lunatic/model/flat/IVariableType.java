package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class IVariableType {

    @JsonProperty(required = true)
    protected String name;
    protected String componentRef;
    protected VariableTypeEnum variableType;

}
