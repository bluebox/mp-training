package training.java.core.RegularExpressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneCountry {
    public static void main(String[] args) {
        String number ="+914348356,+101347939,+9143633480,+2343869374";
        String text="\\+91[^,]*"; 
        Pattern pattern= Pattern.compile(text);
        Matcher matcher= pattern.matcher(number);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
