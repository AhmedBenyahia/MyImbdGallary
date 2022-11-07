package test.arelion.myimbdgallary.exception;

/**
 * The {@link ExceptionInfo } class.
 * This abstract class hold generale information about exceptions
 * All custom exception in the application extend it.
 *
 * @author AhmedBenyahia
 * @since 1.0.0
 */
public abstract class ExceptionInfo extends RuntimeException{

    private String message;
    private int status;

    public ExceptionInfo(int statusCode, String message) {
        this.message = message;
        this.status = statusCode;
//        Logger logger = LoggerConfig.getLogger(ExceptionInfo.class);
//        logger.fine("Exception thrown with status code: " + statusCode + " and message: "+message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
