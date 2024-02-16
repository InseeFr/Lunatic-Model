package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;

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

    public CleaningVariableEntry removeCleaningEntry(String cleaningVariableName) {
        return cleaningVariables.remove(cleaningVariableName);
    }

}
