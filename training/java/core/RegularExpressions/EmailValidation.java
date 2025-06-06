package training.java.core.RegularExpressions;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String text="^([a-zA-Z0-9._-]+)@([a-zA-Z0-9.-]+)\\.[a-z]{2,}";
        String email="john.boy@valid.com, jane.doe-smith@valid.co.uk, jane_Doe1976@valid.co.uk,bob-1964@valid.net, elaine@valid-test.com.au, david@valid.io,john.boy@invalid,bob1@invalid.com,elaineinvalid1983@.com,david@invalid..com";
        String mails[]=email.split(",");
        Pattern pattern= Pattern.compile(text);
        for(String mail:mails){
            Matcher matcher=pattern.matcher(mail.trim());
            if(matcher.matches()){
                System.out.println(mail);
            }
        }
        
        

    }
}
