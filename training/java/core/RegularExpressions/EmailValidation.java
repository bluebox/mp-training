package training.java.core.RegularExpressions;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String text="[a-zA-z].[a-z]";
        String email=sc.nextLine();
        Pattern pattern= Pattern.compile(text);
        Matcher matcher=pattern.matcher(email);
        while(matcher.find()){
            System.out.println(matcher.group());
        }

    }
}
