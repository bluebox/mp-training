package service.exceptions;

public class LogicalError extends Exception {

    public LogicalError(String message) {
        super(message);
    }

    public LogicalError(String message, Throwable cause) {
        super(message, cause);
    }
}