package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ConditionFilterType extends LabelType {

    @JsonIgnore
    protected List<String> bindingDependencies;

    public ConditionFilterType() {
        this.bindingDependencies = new ArrayList<>();
    }
}
