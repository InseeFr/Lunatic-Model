package fr.insee.lunatic.model.flat.cleaning;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CleanedVariableEntry {

    private final String variableName;
    private final List<String> filterExpressions;

    public CleanedVariableEntry(String variableName) {
        this.variableName = variableName;
        this.filterExpressions = new ArrayList<>();
    }

    public CleanedVariableEntry(String variableName, List<String> filterExpressions) {
        this.variableName = variableName;
        this.filterExpressions = filterExpressions;
    }

}
