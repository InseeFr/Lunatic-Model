package fr.insee.lunatic.model.flat.variable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import fr.insee.lunatic.model.flat.LabelType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * A calculated variable contains an expression to be evaluated at runtime.
 */
@Getter @Setter
public class CalculatedVariableType extends VariableType {

    public CalculatedVariableType() {
        variableType = VariableTypeEnum.CALCULATED;
    }

    /** Expression of the calculated variable. */
    protected LabelType expression;

    /** Name of collected and/or external variables that are required to evaluate the expression. */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<String> bindingDependencies = new ArrayList<>();

    /** Name of the collected variables that determine the 'shape' of the variable. */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<String> shapeFrom = new ArrayList<>();

    /** Whether the Lunatic engine should ignore this variable
     * (e.g. it is an exploitation variable that will be calculated later). */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected Boolean isIgnoredByLunatic;

    /**
     * Returns the list of the 'shape from' variable names.
     * @return A list of variable names.
     * @deprecated Use <code>getShapeFrom()</code> method.
     */
    @JsonIgnore
    @Deprecated(since = "6.0.0")
    public List<String> getShapeFromList() {
        return shapeFrom;
    }

}
