package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "previous"
})
@Getter
@Setter
public class PREVIOUSArray {

    @JsonProperty(value = "PREVIOUS")
    protected List<Object> previous;

    public PREVIOUSArray() {
        this.previous = new ArrayList<>();
    }
}
