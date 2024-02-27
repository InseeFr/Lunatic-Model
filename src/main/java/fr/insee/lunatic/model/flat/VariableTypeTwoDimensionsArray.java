package fr.insee.lunatic.model.flat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VariableTypeTwoDimensionsArray extends IVariableType {

    /** Value field for external variables */
    protected ValueTypeTwoDimensionsArray value;

    /** Values field for collected variables */
    protected ValuesTypeTwoDimensionsArray values;

    public VariableTypeTwoDimensionsArray() {
        this.values = new ValuesTypeTwoDimensionsArray();
        this.variableDimension = VariableDimension.DOUBLE_ARRAY;
    }

}
