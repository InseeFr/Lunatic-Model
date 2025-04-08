package fr.insee.lunatic.model.flat.cleaning;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CleanedVariableEntry {

    private final String variableName;
    private final List<CleaningExpression> cleaningExpressions;

    public CleanedVariableEntry(String variableName) {
        this.variableName = variableName;
        this.cleaningExpressions = new ArrayList<>();
    }

    public CleanedVariableEntry(String variableName, List<CleaningExpression> cleaningExpressions) {
        this.variableName = variableName;
        this.cleaningExpressions = cleaningExpressions;
    }
}
