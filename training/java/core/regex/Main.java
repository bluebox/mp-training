import java.util.regex.Pattern;

public class Main {
		private static final String regex =
	            "^[a-zA-Z0-9_+&*-]+(?:\\." +
	            "[a-zA-Z0-9_+&*-]+)*@" +
	            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

	    private static final Pattern pattern = Pattern.compile(regex);

	    private static boolean isValidEmail(String email) {
	        if (email == null) {
	            return false;
	        }
	        return pattern.matcher(email).matches();
	    }

	    public static void main(String[] args) {
	        String exampleEmail= "Hello._World123@medplus.com";
	        String exampleEmail2 = "JhonDoe!@gmail.";
	        
	        System.out.println("( "+exampleEmail+" )"+ ((isValidEmail(exampleEmail))? " is a Valid Email.":" is NOT a Valid Email."));
	        System.out.println("( "+exampleEmail2+" )"+ ((isValidEmail(exampleEmail2))? " is a Valid Email.":" is NOT a Valid Email."));
	        
	    }
}

