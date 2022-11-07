package test.arelion.myimbdgallary.exception;


/**
 * The {@link MovieNotFoundException } class.
 *
 * @author AhmedBenyahia
 * @since 1.0.0
 */
public class MovieNotFoundException extends ExceptionInfo {

    public MovieNotFoundException(int statusCode, String message) {
        super(statusCode, message);
    }


}
