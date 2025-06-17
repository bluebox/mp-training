package PustakaLokam.library.exceptionhandler;

public class IssueNotFoundException extends Exception {
    public IssueNotFoundException(String message) {
        super(message);
    }
}
