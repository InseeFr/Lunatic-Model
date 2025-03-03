package fr.insee.lunatic.model.flat.cleaning;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class CleaningVariableEntry2 {

    @Getter
    private String cleaningVariableName;

    @JsonValue
    private final Map<String, List<String>> cleanedVariables;


    public CleaningVariableEntry2(final String cleaningVariableName) {
        this.cleaningVariableName = cleaningVariableName;
        this.cleanedVariables = new LinkedHashMap<>();
    }

    /**
     * Private constructor only meant to be used by jackson (through reflection) for deserialization.
     * @param cleanedVariables Key value map mapped by jackson.
     */
    @JsonCreator @SuppressWarnings("unused")
    private CleaningVariableEntry2(final Map<String, List<String>> cleanedVariables) {
        this.cleanedVariables = cleanedVariables;
    }

    public void addCleanedVariable(CleanedVariableEntry2 cleanedVariableEntry) {
        if (cleanedVariables.containsKey(cleanedVariableEntry.getVariableName()))
            log.debug("Overwriting cleaned variable entry '{}' in cleaning variable entry '{}'",
                    cleanedVariableEntry.getVariableName(), cleaningVariableName);
        cleanedVariables.put(cleanedVariableEntry.getVariableName(), cleanedVariableEntry.getFilterExpressions());
    }

    public CleanedVariableEntry2 getCleanedVariable(String cleanedVariableName) {
        CleanedVariableEntry2 cleanedVariableEntry = new CleanedVariableEntry2(cleanedVariableName);
        cleanedVariableEntry.getFilterExpressions().addAll(cleanedVariables.get(cleanedVariableName));
        return cleanedVariableEntry;
    }

    public Set<String> getCleanedVariableNames() {
        return cleanedVariables.keySet();
    }

    public int countCleanedVariables() {
        return cleanedVariables.size();
    }

    public CleanedVariableEntry2 removeCleanedVariable(String cleanedVariableName) {
        List<String> removed = cleanedVariables.remove(cleanedVariableName);
        CleanedVariableEntry2 cleanedVariableEntry = new CleanedVariableEntry2(cleanedVariableName);
        cleanedVariableEntry.getFilterExpressions().addAll(removed);
        return cleanedVariableEntry;
    }

}
