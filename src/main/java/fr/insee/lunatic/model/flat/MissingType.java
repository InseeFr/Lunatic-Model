package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
public class MissingType {

    @JsonValue
    private final Map<String, MissingEntry> missingEntries;

    public MissingType() {
         missingEntries = new HashMap<>();
    }

    @JsonCreator
    private MissingType(final Map<String, MissingEntry> missingEntries) {
        this.missingEntries = missingEntries;
    }

    public void addMissingEntry(MissingEntry missingEntry) {
        if (missingEntries.containsKey(missingEntry.getVariableName()))
            log.warn("Overwriting missing entry for variable '{}'", missingEntry.getVariableName());
        missingEntries.put(missingEntry.getVariableName(), missingEntry);
    }

    public MissingEntry getMissingEntry(String variableName) {
        return missingEntries.get(variableName);
    }

    public Set<String> getMissingKeys() {
        return missingEntries.keySet();
    }

    public int countMissingEntries() {
        return missingEntries.size();
    }

    public MissingEntry removeMissingEntry(String variableName) {
        return missingEntries.remove(variableName);
    }

}
