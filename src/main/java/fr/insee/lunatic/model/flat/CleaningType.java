package fr.insee.lunatic.model.flat;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Slf4j
public class CleaningType extends LinkedHashMap<String, Map<String, String>> {

    public CleaningType() {
        super();
    }

    public void insertCleaningEntry(String cleaningVariableName, CleaningEntry cleaningEntry) {
        if (! this.containsKey(cleaningVariableName))
            this.put(cleaningVariableName, new LinkedHashMap<>());
        if (this.get(cleaningVariableName).containsKey(cleaningVariableName))
            log.warn("Overwriting cleaning entry '{}' in cleaning variable '{}'",
                    cleaningEntry.variableName(), cleaningVariableName);
        this.get(cleaningVariableName).put(cleaningEntry.variableName(), cleaningEntry.filterExpression());
    }

    public void removeCleaningEntries(String cleaningVariableName) {
        this.remove(cleaningVariableName);
    }

    public void removeCleaningEntry(String cleaningVariableName, String variableName) {
        if (this.containsKey(cleaningVariableName))
            this.get(cleaningVariableName).remove(variableName);
    }

}
