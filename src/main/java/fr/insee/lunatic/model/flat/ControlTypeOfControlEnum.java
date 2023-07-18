package fr.insee.lunatic.model.flat;

public enum ControlTypeOfControlEnum {
    FORMAT,
    CONSISTENCY;

    public String value() {
        return name();
    }

    public static ControlTypeOfControlEnum fromValue(String v) {
        return valueOf(v);
    }

}
