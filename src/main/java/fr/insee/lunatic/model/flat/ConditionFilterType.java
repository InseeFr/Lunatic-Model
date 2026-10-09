package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "value",
        "type",
        "bindingDependencies",
        "variablesDependencies"
})
@Getter
@Setter
public class ConditionFilterType extends LabelType {

    // Internal, not serialized. Still used by Eno processing steps; to be removed after their refactoring.
    @JsonIgnore
    protected List<String> bindingDependencies;

    /** Direct variable dependencies of the filter expression, by variable type. */
    protected VariablesDependencies variablesDependencies;

    public ConditionFilterType() {
        this.bindingDependencies = new ArrayList<>();
    }
}