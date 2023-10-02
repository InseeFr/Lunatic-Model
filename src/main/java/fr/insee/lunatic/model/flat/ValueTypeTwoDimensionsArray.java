package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "value"
})
@Getter
@Setter
public class ValueTypeTwoDimensionsArray {

    protected List<List<String>> value;

    public ValueTypeTwoDimensionsArray() {
        this.value = new ArrayList<>(new ArrayList<>());
    }
}
