package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "forced"
})
@Getter
@Setter
public class FORCEDArray {

    @JsonProperty(value = "FORCED")
    protected List<Object> forced;

    public FORCEDArray() {
        this.forced = new ArrayList<>();
    }
}
