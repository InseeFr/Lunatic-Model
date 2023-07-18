package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({
        "value",
        "values"
})
@Getter
@Setter
public class VariableTypeArray
    extends IVariableType
{

    protected ValueTypeArray value;
    protected ValuesTypeArray values;
}
