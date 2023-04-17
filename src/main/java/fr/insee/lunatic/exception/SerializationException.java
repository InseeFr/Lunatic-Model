package fr.insee.lunatic.exception;

/** Thrown when an error occurs during serialization of a questionnaire object. */
public class SerializationException extends Exception {

    public SerializationException(String message, Exception e) {
        super(message, e);
    }

}
