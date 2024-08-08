package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Dropdown
    extends ComponentType
    implements ComponentSimpleResponseType
{

    protected List<Option> options;

    @JsonProperty(required = true)
    protected ResponseType response;

    public Dropdown() {
        super();
        this.options = new ArrayList<>();
    }
}
