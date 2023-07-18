package fr.insee.lunatic.model.flat;

public enum VariableTypeEnum {

    EXTERNAL,
    COLLECTED,
    CALCULATED;

    public String value() {
        return name();
    }

    public static VariableTypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
