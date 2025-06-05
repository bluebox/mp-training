package training.java.core.RegularExpressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidation {
    public static void main(String[] args) {
        // String text="[0-9]";
        String number="6304993898";        
        
        if(number.matches("^[6-9]\\d{9}")){
            System.out.println("it is valid number");
        }else{
            System.out.println("not valiud" );
        }
    }   
}
