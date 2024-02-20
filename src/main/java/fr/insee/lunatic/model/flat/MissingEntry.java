package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class MissingEntry {

    @Getter
    private String variableName;

    @JsonValue
    @Getter
    private final List<String> correspondingVariables;

    public MissingEntry(String variableName) {
        correspondingVariables = new ArrayList<>();
        this.variableName = variableName;
    }

    @JsonCreator @SuppressWarnings("unused") // constructor only meant for jackson doing deserialization
    private MissingEntry(List<String> correspondingVariables) {
        this.correspondingVariables = correspondingVariables;
    }

}
