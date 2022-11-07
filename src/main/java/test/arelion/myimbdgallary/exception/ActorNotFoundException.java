package test.arelion.myimbdgallary.exception;

/**
 * The {@link ActorNotFoundException } class.
 * This exception is thrown when the requested actor do not exist in DB
 *
 * @author AhmedBenyahia
 * @since 1.0.0
 */
public class ActorNotFoundException extends ExceptionInfo {
    public ActorNotFoundException(int statusCode, String message) {
        super(statusCode, message);
    }
}
