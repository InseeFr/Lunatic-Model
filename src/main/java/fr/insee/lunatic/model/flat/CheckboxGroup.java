package fr.insee.lunatic.model.flat;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CheckboxGroup
    extends ComponentType
    implements ComponentMultipleResponseType
{

    protected List<ResponsesCheckboxGroup> responses;

    public CheckboxGroup() {
        super();
        this.responses = new ArrayList<>();
    }
}
