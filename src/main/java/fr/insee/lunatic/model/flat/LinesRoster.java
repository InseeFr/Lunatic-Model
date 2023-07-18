package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({
        "min",
        "max"
})
@Getter
@Setter
public class LinesRoster {

    @JsonProperty(required = true)
    protected LabelType min;
    @JsonProperty(required = true)
    protected LabelType max;
}
