package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "any"
})
@Getter
@Setter
public class MissingType {

    protected List<Object> any;

    public MissingType() {
        this.any = new ArrayList<>();
    }
}
