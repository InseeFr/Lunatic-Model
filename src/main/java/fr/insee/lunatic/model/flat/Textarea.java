package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@JsonPropertyOrder({
        "response"
})
@Getter
@Setter
public class Textarea
    extends ComponentType
    implements ComponentSimpleResponseType
{
    @JsonProperty(required = true)
    protected ResponseType response;
    protected BigInteger maxLength;
}
