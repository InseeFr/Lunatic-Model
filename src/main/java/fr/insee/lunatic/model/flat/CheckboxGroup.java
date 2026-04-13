package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Multiple choice response component with checkboxes.
 */
@Getter
@Setter
public class CheckboxGroup extends ComponentType implements ComponentMultipleResponseType, ComponentMandatory {

    /** Orientation of the checkbox modalities. */
    protected Orientation orientation;

    /** List of the modalities of the checkbox component. */
    protected List<ResponseCheckboxGroup> responses;

    /** Indicates whether the response is mandatory for this component. */
    @JsonProperty("isMandatory")
    private Boolean mandatory;

    public CheckboxGroup() {
        super(ComponentTypeEnum.CHECKBOX_GROUP);
        this.orientation = Orientation.VERTICAL;
        this.responses = new ArrayList<>();
    }

}
