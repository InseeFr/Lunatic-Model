package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "collected"
})
@Getter
@Setter
public class COLLECTEDArray {

    @JsonProperty(value = "COLLECTED")
    protected List<Object> collected;

    public COLLECTEDArray() {
        this.collected = new ArrayList<>();
    }
}
