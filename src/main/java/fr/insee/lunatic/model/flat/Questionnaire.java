package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.insee.lunatic.model.flat.cleaning.CleaningType;
import fr.insee.lunatic.model.flat.cleaning.CleaningType2;
import fr.insee.lunatic.model.flat.variable.VariableType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Questionnaire extends ComponentType {

    @JsonProperty("final")
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
    protected CleaningType2 cleaning2;
    protected MissingType missingBlock;
    protected ResizingType resizing;

    public Questionnaire () {
        super();
        this.componentType = ComponentTypeEnum.QUESTIONNAIRE;
        this.components = new ArrayList<>();
        this.suggesters = new ArrayList<>();
        this.variables = new ArrayList<>();
    }

    @JsonProperty("pagination")
    public Pagination getPaginationEnum() {
        return pagination;
    }

    @JsonProperty("pagination")
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }


    /**
     * Method for the legacy string pagination attribute.
     * @return String value of the questionnaire pagination mode.
     * @deprecated Use the Pagination enum.
     */
    @JsonIgnore
    @Deprecated(since = "3.14.0")
    public String getPagination() {
        return pagination.value();
    }

    /**
     * Method for the legacy string pagination attribute.
     * @deprecated Use the Pagination enum.
     */
    @JsonIgnore
    @Deprecated(since = "3.14.0")
    public void setPagination(String pagination) {
        this.pagination = Pagination.fromValue(pagination);
    }

}
