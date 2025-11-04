package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Unique choice response component with checkboxes.
 */
@Getter
@Setter
public class CheckboxOne extends ComponentType implements ComponentSimpleResponseType, ComponentMandatory {

    /** Orientation of the checkbox modalities. */
    protected Orientation orientation;

    /** List of the modalities of the checkbox component. */
    protected List<Option> options;

    /** {@link ResponseType} */
    @JsonProperty(required = true)
    protected ResponseType response;

    /** Indicates whether the response is mandatory for this component. */
    @JsonProperty("isMandatory")
    private Boolean mandatory;

    public CheckboxOne() {
        super();
        this.componentType = ComponentTypeEnum.CHECKBOX_ONE;
        this.orientation = Orientation.VERTICAL;
        this.options = new ArrayList<>();
    }

}
