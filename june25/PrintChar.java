package june25;

public class PrintChar {
	
    public static void main(String[] args) {
        //use symbl.cc for unicode notation
        char myChar = 'S';
        System.out.println(myChar);
        char DecimalChar = 83;
        System.out.println(DecimalChar);
        char UnicodeChar = '\u0053';
        System.out.println(UnicodeChar);

        String s = "I wish I had \u00241,000,000.00"; // $ unicode is \u0024 
        System.out.println(s);
    }
}

