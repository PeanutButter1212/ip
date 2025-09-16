package peanut.tasks;


/**
 * Represents a custom exception used in the Peanut application.
 * <p>
 * This exception is thrown when an invalid user input or when other
 * errors occur while processing tasks.
 */

public class PeanutException extends Exception {
    /**
     * Creates a new PeanutException with the specified detail message.
     *
     * @param message Message describing the cause of the exception
     */
    public PeanutException(String message) {
        super(message);
    }
}
