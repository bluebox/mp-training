import java.util.regex.*;
public class emailvalidator {
    public static void main(String[] args) {
        String emailText = """
                john.boy@valid.com
			jane.doe-smith@vaalid.co.uk
			jane_Doe1976@valid.co.uk
			bob-1964@valid.net
			elaine@valid-test.com.au
			david@valid.io
			john.boy@invalid
			bob!@invalid.com
			elaineinvalid1983@.com
			david@invalid..com
                """;
            Pattern pp = Pattern.compile("([\\w.-]+)@((\\w+\\.)+\\w{2,})");
		Matcher em = pp.matcher(emailText);
        em.results().forEach(mr->{
			System.out.printf("[username=%s,domain=%s]%n", mr.group(1), mr.group(2));
        });
    }
}