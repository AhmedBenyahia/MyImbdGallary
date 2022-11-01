package test.arelion.myimbdgallary.exception;

public class ActorNotFoundException extends ExceptionInfo {
    public ActorNotFoundException(int statusCode, String message) {
        super(statusCode, message);
    }
}
