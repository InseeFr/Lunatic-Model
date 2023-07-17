package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@JsonPropertyOrder({
        "field",
        "type"
})
@Getter
public class SuggesterOrder {

    @JsonProperty(required = true)
    protected String field;
    @JsonProperty(required = true)
    protected String type;
}
