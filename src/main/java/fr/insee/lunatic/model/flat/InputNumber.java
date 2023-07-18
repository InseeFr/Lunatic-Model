package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class InputNumber
    extends ComponentType
    implements ComponentSimpleResponseType
{

    protected String unit;
    @JsonProperty(required = true)
    protected ResponseType response;
    protected Double min;
    protected Double max;
    protected BigInteger decimals;
}
