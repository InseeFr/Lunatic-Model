package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Suggester component.
 */
@Getter
@Setter
public class Suggester extends ComponentType implements ComponentSimpleResponseType {

    /* Name of the code list used for auto-completion. */
    //private String storeName;

    /** Collected response of the suggester component. */
    @JsonProperty(required = true)
    protected ResponseType response;

}
