package PustakaLokam.library.exceptionhandler;

public class MemberNotFoundException extends Exception {

    public MemberNotFoundException(String message) {
        super(message);
    }

    public MemberNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
