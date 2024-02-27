package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class VariableType extends IVariableType {

    /** Value field for external variables */
    protected String value;

    /** Value field for external variables */
    protected ValuesType values;

    /** Values field for collected variables */
    protected LabelType expression;

    /** Only for calculated variables.
     * Name of collected variables that are required to evaluate the expression. */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<String> bindingDependencies;

    /** Only for calculated and external variables.
     * Name of the collected variable that define the 'shape' of the variable. */
    protected String shapeFrom;

    /** @deprecated This property is not used in Lunatic. */
    @Deprecated(since = "3.3.5")
    protected String inFilter;

    public VariableType() {
        this.bindingDependencies = new ArrayList<>();
        this.values = new ValuesType();
        this.variableDimension = VariableDimension.SCALAR;
    }

}
