package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Response component for a duration.
 */
@Getter
@Setter
public class Duration extends ComponentType implements ComponentSimpleResponseType {

    public Duration() {
        super();
        this.componentType = ComponentTypeEnum.DURATION;
    }

    /** Indicates whether the response is mandatory for this component. */
    @JsonProperty("isMandatory")
    private Boolean mandatory;

    @JsonProperty(required = true)
    private DurationFormat format;

    @JsonProperty(required = true)
    protected ResponseType response;

    protected String min;
    protected String max;
}
