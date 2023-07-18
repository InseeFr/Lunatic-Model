package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "edited"
})
@Getter
@Setter
public class EDITEDArray {

    @JsonProperty(value = "EDITED")
    protected List<Object> edited;

    public EDITEDArray() {
        this.edited = new ArrayList<>();
    }
}
