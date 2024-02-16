package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Getter
public class CleaningVariableEntry {

    private final String cleaningVariableName;

    @JsonValue
    private final Map<String, String> cleanedVariables = new LinkedHashMap<>();

    public CleaningVariableEntry(String cleaningVariableName) {
        this.cleaningVariableName = cleaningVariableName;
    }

    public void addCleanedVariable(CleanedVariableEntry cleanedVariableEntry) {
        if (cleanedVariables.containsKey(cleanedVariableEntry.variableName()))
            log.warn("Overwriting cleaned variable entry '{}' in cleaning variable entry '{}'",
                    cleanedVariableEntry.variableName(), cleaningVariableName);
        cleanedVariables.put(cleanedVariableEntry.variableName(), cleanedVariableEntry.filterExpression());
    }

    public CleanedVariableEntry getCleanedVariable(String cleanedVariableName) {
        return new CleanedVariableEntry(cleanedVariableName, cleanedVariables.get(cleanedVariableName));
    }

    public CleanedVariableEntry removeCleanedVariable(String cleanedVariableName) {
        return new CleanedVariableEntry(cleanedVariableName, cleanedVariables.remove(cleanedVariableName));
    }

}
