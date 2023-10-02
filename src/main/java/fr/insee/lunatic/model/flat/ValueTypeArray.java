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
public class ValueTypeArray {

    protected List<String> value;

    public ValueTypeArray() {
        this.value = new ArrayList<>();
    }
}
