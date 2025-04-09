package fr.insee.lunatic.model.flat;

public interface ComponentSimpleResponseType {
    void setResponse(ResponseType responseType);

    ResponseType getResponse();

    void setMandatory(Boolean mandatory);

    Boolean getMandatory();
}
