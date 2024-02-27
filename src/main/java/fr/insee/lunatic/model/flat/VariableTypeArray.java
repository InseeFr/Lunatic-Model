package fr.insee.lunatic.model.flat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VariableTypeArray extends IVariableType {

    /** Value field for external variables */
    protected ValueTypeArray value;

    /** Values field for collected variables */
    protected ValuesTypeArray values;

    public VariableTypeArray() {
        this.values = new ValuesTypeArray();
        this.variableDimension = VariableDimension.ARRAY;
    }

}
