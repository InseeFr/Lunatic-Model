package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Duration extends ComponentType implements ComponentSimpleResponseType {

    public Duration() {
        this.componentType = ComponentTypeEnum.DURATION;
    }

    @JsonProperty(required = true)
    private DurationFormat format;

    @JsonProperty(required = true)
    protected ResponseType response;

    protected DurationFormat min;
    protected DurationFormat max;
}
