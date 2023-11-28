package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({
        "value",
        "type"
})
public class LabelType {

    @Getter @Setter
    protected String value;

    protected String type;
    @JsonIgnore
    private LabelTypeEnum typeEnum;

    /**
     * Get label type.
     * @return String value of label type.
     * @deprecated The string property is being replaced with an enum. Use the <code>getTypeEnum</code> method.
     */
    @Deprecated(since = "3.2.6")
    public String getType() {
        if (typeEnum != null)
            return typeEnum.toString();
        return type;
    }

    /**
     * Set string label type.
     * @param type String value of label type, can be either "VTL_MD" or "VTL".
     * @deprecated The string property is being replaced with an enum.
     */
    @Deprecated(since = "3.2.6")
    public void setType(String type) {
        if (! ("VTL_MD".equals(type) || "VTL".equals(type)))
            throw new IllegalArgumentException("Label type can be either \"VTL_MD\" or \"VTL\".");
        this.type = type;
        this.typeEnum = null;
    }

    /**
     * Temporary getter for the type property being changed to an enum. In a future version, the <code>getType</code>
     * will return the proper label type.
     * @return Label type.
     */
    public LabelTypeEnum getTypeEnum() {
        if (type != null)
            return LabelTypeEnum.valueOf(type);
        return typeEnum;
    }

    /**
     * Set label type.
     * @param labelTypeEnum Label type.
     */
    public void setType(LabelTypeEnum labelTypeEnum) {
        this.type = null;
        this.typeEnum = labelTypeEnum;
    }

}
