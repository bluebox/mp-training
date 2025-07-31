package library.exception;

public class LibraryDataAccessException extends LibraryException {

    public LibraryDataAccessException(String message) {
        super(message);
    }

    public LibraryDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}