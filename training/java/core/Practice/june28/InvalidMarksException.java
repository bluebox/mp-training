package Practice.june28;

public class InvalidMarksException extends Exception {
	  public InvalidMarksException() {
	        super("Marks must be between 0 and 100.");
	    }

	    public InvalidMarksException(String message) {
	        super(message);
	    }
}
