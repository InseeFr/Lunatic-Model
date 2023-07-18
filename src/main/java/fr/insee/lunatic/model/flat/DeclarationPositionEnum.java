package fr.insee.lunatic.model.flat;

public enum DeclarationPositionEnum {

    AFTER_QUESTION_TEXT,
    AFTER_RESPONSE,
    BEFORE_QUESTION_TEXT,
    DETACHABLE;

    public String value() {
        return name();
    }

    public static DeclarationPositionEnum fromValue(String v) {
        return valueOf(v);
    }

}
