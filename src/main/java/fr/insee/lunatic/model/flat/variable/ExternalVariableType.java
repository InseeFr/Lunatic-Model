package fr.insee.lunatic.model.flat.variable;

import lombok.Getter;
import lombok.Setter;

/**
 * An external variable is a variable with pre-defined value.
 */
@Getter
@Setter
public class ExternalVariableType extends VariableType {

    public ExternalVariableType() {
        variableType = VariableTypeEnum.EXTERNAL;
    }

    /** Value field of the external variable. */
    ExternalVariableValue value;

}
