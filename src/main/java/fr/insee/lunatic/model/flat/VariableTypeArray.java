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
public class VariableTypeArray extends IVariableType {

    /** Value field for external variables */
    protected ValueTypeArray value;

    /** Values field for collected variables */
    protected ValuesTypeArray values;

}
