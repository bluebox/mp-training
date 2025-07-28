package exceptions;

public class DatabaseException extends Exception {
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}