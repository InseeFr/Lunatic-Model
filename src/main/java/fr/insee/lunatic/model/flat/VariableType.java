package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "value",
        "values",
        "expression",
        "bindingDependencies",
        "shapeFrom",
        "inFilter"
})
@Getter
@Setter
public class VariableType
    extends IVariableType
{
    protected String value;
    protected ValuesType values;
    protected LabelType expression;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<String> bindingDependencies;
    protected String shapeFrom;
    protected String inFilter;

    public VariableType() {
        this.bindingDependencies = new ArrayList<>();
    }
}
