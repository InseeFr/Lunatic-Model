package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "previous",
        "collected",
        "forced",
        "edited",
        "inputed"
})
@Getter
@Setter
public class ValuesTypeTwoDimensionsArray {

    /* Note: for now, seems that nested loops (or loop of loop) will be forbidden.
    * This modeling only works for "simple" loops. */

    @JsonProperty(value = "PREVIOUS")
    protected List<List<String>> previous;
    @JsonProperty(value = "COLLECTED")
    protected List<List<String>> collected;
    @JsonProperty(value = "FORCED")
    protected List<List<String>> forced;
    @JsonProperty(value = "EDITED")
    protected List<List<String>> edited;
    @JsonProperty(value = "INPUTED")
    protected List<List<String>> inputed;
    
    public ValuesTypeTwoDimensionsArray() {
        previous = newBidimentionalList();
        collected = newBidimentionalList();
        forced = newBidimentionalList();
        edited = newBidimentionalList();
        inputed = newBidimentionalList();
    }
    
    private List<List<String>> newBidimentionalList() {
        List<List<String>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        return list;
    }

}
