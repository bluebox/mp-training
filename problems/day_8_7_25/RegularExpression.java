package day_8_7_25;

public class RegularExpression {

	public static void main(String[] args) {
		String str1="Hello World";
		if(str1.matches(".*[A-Za-z]")){
			System.out.println("Strings matched");
		}else {
			System.out.println("Strings not matched");
		}
		
		
		String str2="How are you .";
		if(str2.matches("[A-Z][a-z].*\\.")) {
			System.out.println("Strings is matched");
		}else {
			System.out.println("Strings is not matched");
		}
		
		
		String str3="This is Bad ! ?";
		if(str3.matches("[A-Z].+[.?!]")) {
			System.out.println("Strings is matched");
		}else {
			System.out.println("Strings is not matched");
		}
		
       
	}

}
