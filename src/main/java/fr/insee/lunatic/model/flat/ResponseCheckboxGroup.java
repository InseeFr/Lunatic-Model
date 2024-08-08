package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({
        "id",
        "label",
        "response",
        "detail"
})
@Getter
@Setter
public class ResponseCheckboxGroup {

    /** Identifier of the response object. */
    @JsonProperty(required = true)
    protected String id;

    /** Displayed label of the modality. */
    @JsonProperty(required = true)
    protected LabelType label;

    /** Filter that determines if the option is displayed or not.
     * If null, the option is displayed. */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected ConditionFilterType conditionFilter;

    /** Response name of the collected variable that corresponds to the modality. */
    @JsonProperty(required = true)
    protected ResponseType response;

    /** {@link DetailResponse} */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected DetailResponse detail;

}
