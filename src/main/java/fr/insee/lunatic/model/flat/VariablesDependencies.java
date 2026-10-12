package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({"COLLECTED", "EXTERNAL", "CALCULATED"})
public class VariablesDependencies {

    @JsonProperty("COLLECTED")
    private List<String> collected = new ArrayList<>();

    @JsonProperty("EXTERNAL")
    private List<String> external = new ArrayList<>();

    @JsonProperty("CALCULATED")
    private List<String> calculated = new ArrayList<>();
}