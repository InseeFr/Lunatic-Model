package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Suggester component.
 */
@Getter
@Setter
public class Suggester extends ComponentType implements ComponentSimpleResponseType {

    public Suggester() {
        this.componentType = ComponentTypeEnum.SUGGESTER;
    }

    public record OptionResponse(String name, String attribute) {}

    /** Collected response of the suggester component. */
    @JsonProperty(required = true)
    protected ResponseType response;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<OptionResponse> optionResponses = new ArrayList<>();

    /** Maximal length of the text that can be inputted in the arbitrary response. */
    protected BigInteger maxLength; // Note: BigInteger so that it's the same as in Input component

    /** Configuration of the arbitrary response feature.
     * Can be null (feature is disabled in that case).  */
    protected ArbitraryType arbitrary;

}
