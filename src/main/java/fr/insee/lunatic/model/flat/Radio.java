package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Unique choice response component with radio buttons.
 */
@Getter
@Setter
public class Radio extends ComponentType implements ComponentSimpleResponseType {

    /** Orientation of the radio modalities. */
    protected Orientation orientation;

    /** List of the modalities of the radio component. */
    protected List<Options> options;

    /** {@link ResponseType} */
    @JsonProperty(required = true)
    protected ResponseType response;

    public Radio() {
        super();
        this.componentType = ComponentTypeEnum.RADIO;
        this.orientation = Orientation.VERTICAL;
        this.options = new ArrayList<>();
    }

}
