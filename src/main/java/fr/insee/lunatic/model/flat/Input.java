package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class Input
    extends ComponentType
    implements ComponentSimpleResponseType
{

    protected String format;
    @JsonProperty(required = true)
    protected ResponseType response;
    protected BigInteger maxLength;
}
