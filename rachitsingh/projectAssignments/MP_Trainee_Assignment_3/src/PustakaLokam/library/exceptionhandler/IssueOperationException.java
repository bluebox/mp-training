package PustakaLokam.library.exceptionhandler;

public class IssueOperationException extends RuntimeException {

    public IssueOperationException(String message) {
        super(message);
    }

    public IssueOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}