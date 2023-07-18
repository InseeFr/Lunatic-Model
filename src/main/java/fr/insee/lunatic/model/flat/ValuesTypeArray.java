package fr.insee.lunatic.model.flat;

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
public class ValuesTypeArray {

    @JsonProperty(value = "PREVIOUS")
    protected PREVIOUSArray previous;
    @JsonProperty(value = "COLLECTED")
    protected COLLECTEDArray collected;
    @JsonProperty(value = "FORCED")
    protected FORCEDArray forced;
    @JsonProperty(value = "EDITED")
    protected EDITEDArray edited;
    @JsonProperty(value = "INPUTED")
    protected INPUTEDArray inputed;
}
