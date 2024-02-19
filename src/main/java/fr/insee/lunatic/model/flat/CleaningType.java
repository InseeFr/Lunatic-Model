package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Set;

@Slf4j
public class CleaningType {

    @JsonValue
    private final LinkedHashMap<String, CleaningVariableEntry> cleaningVariables;

    public CleaningType() {
        cleaningVariables = new LinkedHashMap<>();
    }

    public void addCleaningEntry(CleaningVariableEntry cleaningVariableEntry) {
        String cleaningVariableName = cleaningVariableEntry.getCleaningVariableName();
        if (cleaningVariables.containsKey(cleaningVariableName))
            log.warn("Overwriting cleaning variable entry '{}'", cleaningVariableName);
        cleaningVariables.put(cleaningVariableName, cleaningVariableEntry);
    }

    public CleaningVariableEntry getCleaningEntry(String cleaningVariableName) {
        return cleaningVariables.get(cleaningVariableName);
    }

    public Set<String> getCleaningVariableNames() {
        return cleaningVariables.keySet();
    }

    public int countCleaningVariables() {
        return cleaningVariables.size();
    }

    public CleaningVariableEntry removeCleaningEntry(String cleaningVariableName) {
        return cleaningVariables.remove(cleaningVariableName);
    }

}
