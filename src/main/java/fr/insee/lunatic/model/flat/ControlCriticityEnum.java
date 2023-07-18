package fr.insee.lunatic.model.flat;

public enum ControlCriticityEnum {

    INFO,
    WARN,
    ERROR;

    public String value() {
        return name();
    }

    public static ControlCriticityEnum fromValue(String v) {
        return valueOf(v);
    }

}
