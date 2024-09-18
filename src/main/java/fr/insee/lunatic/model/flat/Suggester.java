package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

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

    protected List<OptionResponse> optionResponses = new ArrayList<>();

}
