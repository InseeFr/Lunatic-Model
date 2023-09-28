package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({
        "previous",
        "collected",
        "forced",
        "edited",
        "inputed"
})
@Getter
@Setter
public class ValuesType {

    @JsonInclude @JsonProperty(value = "PREVIOUS")
    protected String previous;
    @JsonInclude @JsonProperty(value = "COLLECTED")
    protected String collected;
    @JsonInclude @JsonProperty(value = "FORCED")
    protected String forced;
    @JsonInclude @JsonProperty(value = "EDITED")
    protected String edited;
    @JsonInclude @JsonProperty(value = "INPUTED")
    protected String inputed;
}
