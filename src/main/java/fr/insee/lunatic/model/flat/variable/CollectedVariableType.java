package fr.insee.lunatic.model.flat.variable;

import lombok.Getter;
import lombok.Setter;

/**
 * A collected variable is a variable whose value is collected in a response component.
 */
@Getter
@Setter
public class CollectedVariableType extends VariableType {

    public CollectedVariableType() {
        variableType = VariableTypeEnum.COLLECTED;
    }

    /**
     * Identifier of the component in which the variable is collected.
     * @deprecated Not used by Lunatic anymore.
     */
    @Deprecated(since = "3.3.5", forRemoval = true)
    protected String componentRef;

    /** Value field of the collected variable. */
    protected CollectedVariableValues values;

}
