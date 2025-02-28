package fr.insee.lunatic.model.flat.cleaning;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CleanedVariableEntry2 {

    private final String variableName;
    private final List<String> filterExpressions;

    public CleanedVariableEntry2(String variableName) {
        this.variableName = variableName;
        this.filterExpressions = new ArrayList<>();
    }

    public CleanedVariableEntry2(String variableName, List<String> filterExpressions) {
        this.variableName = variableName;
        this.filterExpressions = filterExpressions;
    }

}
