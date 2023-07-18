package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({
        "sequence",
        "subSequence"
})
@Getter
@Setter
public class Hierarchy {

    protected SequenceDescription sequence;
    protected SequenceDescription subSequence;
}
