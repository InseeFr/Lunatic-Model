package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Table
    extends ComponentType
    implements ComponentMultipleResponseType
{

    protected LinesRoster lines;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<HeaderType> header;
    @JsonProperty("body")
    protected List<BodyLine> bodyLines;
    protected String positioning;

    public Table() {
        super();
        this.header = new ArrayList<>();
        this.bodyLines = new ArrayList<>();
    }
}
