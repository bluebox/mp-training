import java.util.regex.*;
public class RegexMailsChallenge{
    public static void main(String[] args){
        String mails="""
            john.boy@valid.com
            jane.doe-smith@valid.co.uk
            jane_Doe1976@valid.co.uk
            john.boy@invalid
            bob!@invalid.com
            elaineinvalid1983@.com
            david@invalid..com
            bob-1964@valid.net
            elaine@valid-test.com.au
            david@valid.io""";
    Pattern pattern=Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}$", Pattern.MULTILINE);
    Matcher matcher=pattern.matcher(mails);
    while(matcher.find()){
        System.out.println(matcher.group());
    }
    }
}