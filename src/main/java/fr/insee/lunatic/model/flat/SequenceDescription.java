package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({
        "id",
        "page",
        "label"
})
@Getter
@Setter
public class SequenceDescription {

    @JsonProperty(required = true)
    protected LabelType label;
    protected String id;
    protected String page;
}
