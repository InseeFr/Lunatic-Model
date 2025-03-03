package fr.insee.lunatic.model.flat.cleaning;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
public class CleaningVariableEntry {

    @Getter
    private String cleaningVariableName;

    @JsonValue
    private final Map<String, String> cleanedVariables;


    public CleaningVariableEntry(final String cleaningVariableName) {
        this.cleaningVariableName = cleaningVariableName;
        this.cleanedVariables = new LinkedHashMap<>();
    }

    /**
     * Private constructor only meant to be used by jackson (through reflection) for deserialization.
     * @param cleanedVariables Key value map mapped by jackson.
     */
    @JsonCreator @SuppressWarnings("unused")
    private CleaningVariableEntry(final Map<String, String> cleanedVariables) {
        this.cleanedVariables = cleanedVariables;
    }

    public void addCleanedVariable(CleanedVariableEntry cleanedVariableEntry) {
        if (cleanedVariables.containsKey(cleanedVariableEntry.variableName()))
            log.debug("Overwriting cleaned variable entry '{}' in cleaning variable entry '{}'",
                    cleanedVariableEntry.variableName(), cleaningVariableName);
        cleanedVariables.put(cleanedVariableEntry.variableName(), cleanedVariableEntry.filterExpression());
    }

    public CleanedVariableEntry getCleanedVariable(String cleanedVariableName) {
        return new CleanedVariableEntry(cleanedVariableName, cleanedVariables.get(cleanedVariableName));
    }

    public Set<String> getCleanedVariableNames() {
        return cleanedVariables.keySet();
    }

    public int countCleanedVariables() {
        return cleanedVariables.size();
    }

    public CleanedVariableEntry removeCleanedVariable(String cleanedVariableName) {
        return new CleanedVariableEntry(cleanedVariableName, cleanedVariables.remove(cleanedVariableName));
    }

}
