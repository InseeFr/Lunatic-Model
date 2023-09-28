package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "previous",
        "collected",
        "forced",
        "edited",
        "inputed"
})
@Getter
@Setter
public class ValuesTypeArray {

    /* Note: for now, seems that nested loops (or loop of loop) will be forbidden.
    * This modeling only works for "simple" loops. */

    @JsonProperty(value = "PREVIOUS")
    protected List<String> previous = new ArrayList<>();
    @JsonProperty(value = "COLLECTED")
    protected List<String> collected = new ArrayList<>();
    @JsonProperty(value = "FORCED")
    protected List<String> forced = new ArrayList<>();
    @JsonProperty(value = "EDITED")
    protected List<String> edited = new ArrayList<>();
    @JsonProperty(value = "INPUTED")
    protected List<String> inputed = new ArrayList<>();

}
