package fr.insee.lunatic.model.flat;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RosterForLoop
    extends ComponentType
    implements ComponentMultipleResponseType
{

    protected LinesRoster lines;
    protected List<HeaderType> header;
    protected List<BodyCell> components;

    /**
     * Metadata on how the table should be displayed.
     * Value should be one of "HORIZONTAL", "VERTICAL" or "DEFAULT".
     * @deprecated Unused in Lunatic, to be removed.
     */
    @Deprecated(forRemoval = true, since = "3.14.0")
    protected String positioning;

    public RosterForLoop() {
        super();
        this.header = new ArrayList<>();
        this.components = new ArrayList<>();
    }
}
