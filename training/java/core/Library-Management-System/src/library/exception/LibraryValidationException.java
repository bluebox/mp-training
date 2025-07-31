package library.exception;

public class LibraryValidationException extends LibraryException {

    public LibraryValidationException(String message) {
        super(message);
    }

    public LibraryValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}