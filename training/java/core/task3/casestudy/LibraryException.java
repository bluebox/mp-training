package casestudy;

public class LibraryException extends Exception { 
	public LibraryException(String message) { super(message); }
	public LibraryException(String message, Throwable cause) {
    super(message, cause);
}}