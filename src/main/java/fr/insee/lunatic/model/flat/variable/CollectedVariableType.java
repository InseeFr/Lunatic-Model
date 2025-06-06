package fr.insee.lunatic.model.flat.variable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

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
    @Deprecated(since = "3.3.5")
    protected String componentRef;

    /** Value field of the collected variable. */
    protected CollectedVariableValues values;

}
