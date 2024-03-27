package fr.insee.lunatic.model.flat.variable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.insee.lunatic.conversion.variable.CollectedVariableValuesDeserializer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "previous",
        "collected",
        "forced",
        "edited",
        "inputted"
})
@JsonDeserialize(using = CollectedVariableValuesDeserializer.class)
public abstract class CollectedVariableValues {

    private CollectedVariableValues() {}

    @Getter
    @Setter
    public static class Scalar extends CollectedVariableValues {
        @JsonInclude @JsonProperty(value = "PREVIOUS")
        protected ValueType previous;
        @JsonInclude @JsonProperty(value = "COLLECTED")
        protected ValueType collected;
        @JsonInclude @JsonProperty(value = "FORCED")
        protected ValueType forced;
        @JsonInclude @JsonProperty(value = "EDITED")
        protected ValueType edited;
        @JsonInclude @JsonProperty(value = "INPUTTED")
        protected ValueType inputted;
    }

    @Getter
    @Setter
    public static class Array extends CollectedVariableValues {
        @JsonProperty(value = "PREVIOUS")
        protected List<ValueType> previous = new ArrayList<>();
        @JsonProperty(value = "COLLECTED")
        protected List<ValueType> collected = new ArrayList<>();
        @JsonProperty(value = "FORCED")
        protected List<ValueType> forced = new ArrayList<>();
        @JsonProperty(value = "EDITED")
        protected List<ValueType> edited = new ArrayList<>();
        @JsonProperty(value = "INPUTTED")
        protected List<ValueType> inputted = new ArrayList<>();
    }

    @Getter
    @Setter
    public static class DoubleArray extends CollectedVariableValues {
        @JsonProperty(value = "PREVIOUS")
        protected List<List<ValueType>> previous;
        @JsonProperty(value = "COLLECTED")
        protected List<List<ValueType>> collected;
        @JsonProperty(value = "FORCED")
        protected List<List<ValueType>> forced;
        @JsonProperty(value = "EDITED")
        protected List<List<ValueType>> edited;
        @JsonProperty(value = "INPUTTED")
        protected List<List<ValueType>> inputted;

        public DoubleArray() {
            previous = newBidimentionalList();
            collected = newBidimentionalList();
            forced = newBidimentionalList();
            edited = newBidimentionalList();
            inputted = newBidimentionalList();
        }

        private static List<List<ValueType>> newBidimentionalList() {
            List<List<ValueType>> list = new ArrayList<>();
            list.add(new ArrayList<>());
            return list;
        }
    }

}
