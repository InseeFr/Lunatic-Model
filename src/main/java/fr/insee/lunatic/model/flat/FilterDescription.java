package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

// Note: the component inherits from too many properties of the ComponentType class.
// Yet not inheriting this class would require quite a big refactor.
// See comment in the ComponentType class.

/**
 * Component to be used for Lunatic "management mode".
 * Its purpose is to show if a filter would be applied on a component in the management mode where filters are
 * by-passed.
 */
@JsonPropertyOrder({
        "id",
        "page",
        "componentType",
        "label",
        "startId",
        "endId"
})
@Getter
@Setter
public class FilterDescription extends ComponentType {

    public FilterDescription() {
        super();
        this.componentType = ComponentTypeEnum.FILTER_DESCRIPTION;
    }

    /** Identifier of the component on which the filter starts. */
    private String startId;

    /** Identifier of the component on which the filter ends. */
    private String endId;

}
