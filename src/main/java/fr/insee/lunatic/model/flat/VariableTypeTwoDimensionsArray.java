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
public class VariableTypeTwoDimensionsArray extends IVariableType {

    /** Value field for external variables */
    protected ValueTypeTwoDimensionsArray value;

    /** Values field for collected variables */
    protected ValuesTypeTwoDimensionsArray values;

    public VariableTypeTwoDimensionsArray() {
        this.values = new ValuesTypeTwoDimensionsArray();
    }

}
