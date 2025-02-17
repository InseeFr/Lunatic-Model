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

@JsonPropertyOrder({"collected"})

@JsonDeserialize(using = CollectedVariableValuesDeserializer.class)
public abstract class CollectedVariableValues {

    private CollectedVariableValues() {}

    @Getter
    @Setter
    public static class Scalar extends CollectedVariableValues {
        @JsonInclude @JsonProperty(value = "COLLECTED")
        protected ValueType collected;
    }

    @Getter
    @Setter
    public static class Array extends CollectedVariableValues {
        @JsonProperty(value = "COLLECTED")
        protected List<ValueType> collected = new ArrayList<>();
    }

    @Getter
    @Setter
    public static class DoubleArray extends CollectedVariableValues {
        @JsonProperty(value = "COLLECTED")
        protected List<List<ValueType>> collected;

        public DoubleArray() {
            collected = newBidimentionalList();
        }

        private static List<List<ValueType>> newBidimentionalList() {
            List<List<ValueType>> list = new ArrayList<>();
            list.add(new ArrayList<>());
            return list;
        }
    }

}
