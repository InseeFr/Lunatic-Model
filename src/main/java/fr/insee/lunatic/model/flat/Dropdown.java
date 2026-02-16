package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Response component for a unique choice question as a dropdown list.
 */
@Getter
@Setter
public class Dropdown extends ComponentType implements ComponentSimpleResponseType, ComponentMandatory {

    /** Indicates whether the response is mandatory for this component. */
    @JsonProperty("isMandatory")
    private Boolean mandatory;

    /** List of the modalities of the dropdown component (static mode). */
    protected List<Option> options;

    /** Dynamic source for options (dynamic mode). */
    protected String optionSource;

    /** Optional filter for dynamic options. */
    protected OptionFilter optionFilter;

    @JsonProperty(required = true)
    protected ResponseType response;

    public Dropdown() {
        super(ComponentTypeEnum.DROPDOWN);
        this.options = new ArrayList<>();
    }

}
