package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CheckboxOne
    extends ComponentType
    implements ComponentSimpleResponseType
{

    protected List<Options> options;

    @JsonProperty(required = true)
    protected ResponseType response;

    public CheckboxOne() {
        super();
        this.options = new ArrayList<>();
    }
}
