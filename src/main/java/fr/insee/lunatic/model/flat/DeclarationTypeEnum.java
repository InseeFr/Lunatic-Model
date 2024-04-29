package fr.insee.lunatic.model.flat;

public enum DeclarationTypeEnum {

    INSTRUCTION,
    COMMENT,
    HELP,
    CODECARD,
    WARNING,
    STATEMENT;

    public String value() {
        return name();
    }

    public static DeclarationTypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
