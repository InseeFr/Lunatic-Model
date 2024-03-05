package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "id",
        "modele",
        "enoCoreVersion",
        "lunaticModelVersion",
        "generatingDate",
        "missing",
        "pagination",
        "maxPage",
        "label",
        "components",
        "suggesters",
        "variables",
        "cleaning",
        "missingBlock",
        "resizing"
})
@Getter
@Setter
public class Questionnaire extends ComponentType
{

    @JsonProperty("final")
    protected Boolean _final;
    protected String modele;
    protected String enoCoreVersion;
    protected String lunaticModelVersion;
    protected String generatingDate;
    protected Boolean missing;
    protected String pagination;
    protected String maxPage;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<ComponentType> components;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<SuggesterType> suggesters;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<IVariableType> variables;
    protected CleaningType cleaning;
    protected MissingType missingBlock;
    protected ResizingType resizing;

    public Questionnaire () {
        super();
        this.componentType = ComponentTypeEnum.QUESTIONNAIRE;
        this.components = new ArrayList<>();
        this.suggesters = new ArrayList<>();
        this.variables = new ArrayList<>();
    }
}
