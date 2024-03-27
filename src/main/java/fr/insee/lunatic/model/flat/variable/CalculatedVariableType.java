package fr.insee.lunatic.model.flat.variable;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.insee.lunatic.model.flat.LabelType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * A calculated variable contains an expression to be evaluated at runtime.
 */
@Getter
@Setter
public class CalculatedVariableType extends VariableType {

    public CalculatedVariableType() {
        variableType = VariableTypeEnum.CALCULATED;
    }

    /** Expression of the calculated variable. */
    protected LabelType expression;

    /** Name of collected and/or external variables that are required to evaluate the expression. */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<String> bindingDependencies = new ArrayList<>();

    /** Name of the collected variable that define the 'shape' of the variable. */
    protected String shapeFrom;

}
