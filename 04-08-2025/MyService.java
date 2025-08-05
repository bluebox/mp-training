package mymaven;


public class MyService {

    private String message;

    public MyService(String message) {
        this.message = message;
    }

    public void printMessage() {
        System.out.println("My Service Says: " + message);
    }
}