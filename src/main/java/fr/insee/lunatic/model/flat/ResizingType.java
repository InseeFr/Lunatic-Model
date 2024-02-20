package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ResizingType {

    @JsonValue
    private final Map<String, ResizingEntry> resizingEntries;

    @JsonCreator
    private ResizingType(final Map<String, ResizingEntry> resizingEntries) {
        this.resizingEntries = resizingEntries;
    }

    public ResizingType() {
        resizingEntries = new LinkedHashMap<>();
    }

    public void putResizingEntry(String resizingVariableName, ResizingEntry resizingEntry) {
        resizingEntries.put(resizingVariableName, resizingEntry);
    }

    public ResizingEntry getResizingEntry(String resizingVariableName) {
        return resizingEntries.get(resizingVariableName);
    }

    public Set<String> getResizingVariableNames() {
        return resizingEntries.keySet();
    }

    public int countResizingEntries() {
        return resizingEntries.size();
    }

    public ResizingEntry removeResizingEntry(String resizingVariableName) {
        return resizingEntries.remove(resizingVariableName);
    }

}
