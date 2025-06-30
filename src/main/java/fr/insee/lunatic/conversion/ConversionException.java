package fr.insee.lunatic.conversion;

/**
 * Exception indicating a failure during a data format conversion
 * (e.g., JSON ⇄ XML) in the Lunatic context.
 */
public class ConversionException extends Exception {

    /**
     * Constructs a new ConversionException with the specified detail message.
     *
     * @param message the detail message
     */
    public ConversionException(String message) {
        super(message);
    }

    /**
     * Constructs a new ConversionException with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public ConversionException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new ConversionException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the underlying cause of the exception
     */
    public ConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}