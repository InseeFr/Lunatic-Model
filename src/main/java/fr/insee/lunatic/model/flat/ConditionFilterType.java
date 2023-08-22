package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "value",
        "type",
        "bindingDependencies"
})
@Getter
@Setter
public class ConditionFilterType {

    protected String value;
    protected String type;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<String> bindingDependencies;

    public ConditionFilterType() {
        this.bindingDependencies = new ArrayList<>();
    }
}
