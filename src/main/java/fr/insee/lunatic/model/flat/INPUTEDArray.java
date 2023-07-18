package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "inputed"
})
@Getter
@Setter
public class INPUTEDArray {

    @JsonProperty(value = "INPUTED")
    protected List<Object> inputed;

    public INPUTEDArray() {
        this.inputed = new ArrayList<>();
    }
}
