package exceptions;

public class ManagementException extends Exception {
    public ManagementException(String message) {
        super(message);
    }

    public ManagementException(String message, Throwable cause) {
        super(message, cause);
    }
}
