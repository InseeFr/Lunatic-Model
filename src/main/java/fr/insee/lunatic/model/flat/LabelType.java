package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({
        "value",
        "type"
})
@Getter
@Setter
public class LabelType {

    protected String value;

    protected LabelTypeEnum type;

}
