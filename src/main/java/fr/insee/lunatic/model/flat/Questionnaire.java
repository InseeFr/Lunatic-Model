package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.insee.lunatic.model.flat.articulation.Articulation;
import fr.insee.lunatic.model.flat.cleaning.CleaningType;
import fr.insee.lunatic.model.flat.multimode.Multimode;
import fr.insee.lunatic.model.flat.variable.VariableType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Questionnaire extends ComponentType {

    /**
     * @deprecated Unused in Lunatic.
     */
    @JsonProperty("final")
    @Deprecated(since = "6.0.0")
    protected Boolean _final;

    protected String modele;
    protected String enoCoreVersion;
    protected String lunaticModelVersion;
    protected String generatingDate;
    protected Boolean missing;
    protected Pagination pagination;
    protected String maxPage;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<ComponentType> components;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<SuggesterType> suggesters;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<VariableType> variables;
    protected CleaningType cleaning;
    protected MissingType missingBlock;
    protected ResizingType resizing;

    protected Multimode multimode;
    protected Articulation articulation;

    public Questionnaire () {
        super(ComponentTypeName.QUESTIONNAIRE);
        this.components = new ArrayList<>();
        this.suggesters = new ArrayList<>();
        this.variables = new ArrayList<>();
    }

    /**
     * @deprecated Use <code>getPagination()</code> method.
     */
    @JsonIgnore
    @Deprecated(since = "6.0.0")
    public Pagination getPaginationEnum() {
        return pagination;
    }

}
