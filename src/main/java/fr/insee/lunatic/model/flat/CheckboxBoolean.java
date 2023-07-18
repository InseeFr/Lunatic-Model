package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckboxBoolean
    extends ComponentType
    implements ComponentSimpleResponseType
{
    @JsonProperty(required = true)
    protected ResponseType response;
}
