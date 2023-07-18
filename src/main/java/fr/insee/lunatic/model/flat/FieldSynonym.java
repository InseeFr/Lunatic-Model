package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "source",
        "target"
})
@Getter
@Setter
public class FieldSynonym {

    @JsonProperty(required = true)
    protected String source;
    @JsonProperty(required = true)
    protected List<String> target;

    public FieldSynonym() {
        this.target = new ArrayList<>();
    }
}
