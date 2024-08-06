package fr.insee.lunatic.model.flat.variable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.insee.lunatic.model.flat.LabelType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * A calculated variable contains an expression to be evaluated at runtime.
 */
public class CalculatedVariableType extends VariableType {

    public CalculatedVariableType() {
        variableType = VariableTypeEnum.CALCULATED;
    }

    /** Expression of the calculated variable. */
    @Getter @Setter
    protected LabelType expression;

    /** Name of collected and/or external variables that are required to evaluate the expression. */
    @Getter @Setter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<String> bindingDependencies = new ArrayList<>();

    /** Name of the collected variables that determine the 'shape' of the variable. */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<String> shapeFrom = new ArrayList<>();

    /**
     * Returns the list of the 'shape from' variable names.
     * @return A list of variable names.
     */
    @JsonProperty("shapeFrom")
    public List<String> getShapeFromList() {
        return shapeFrom;
    }

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public void setShapeFrom(List<String> variableNames) {
        this.shapeFrom = variableNames;
    }

    /**
     * Get the first variable in the shape from variables list.
     * @return A variable name.
     * @deprecated The shape from property is a list now so this method will be removed at some point.
     */
    @Deprecated(since = "3.13.0")
    public String getShapeFrom() {
        if (shapeFrom.isEmpty())
            return null;
        return shapeFrom.getFirst();
    }

    /**
     * Set shape from variable.
     * @param variableName Variable name.
     * @deprecated The shape from property is now a list of variable names.
     */
    @Deprecated(since = "3.13.0")
    public void setShapeFrom(String variableName) {
        shapeFrom.clear();
        shapeFrom.add(variableName);
    }

}
