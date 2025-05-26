package Main;

import java.util.*;
public class PasswordChecker {
	public static boolean checkPassword(String password) {
		int lengthOfPassword=password.length();
		if(lengthOfPassword < 8) {
			return false;
		}
		boolean hasLower=false;
		boolean hasUpper=false;
		boolean hasDigit=false;
		boolean hasSpecialChar=false;
		
		for(int i=0;i<lengthOfPassword;i++) {
			char currentCharacter=password.charAt(i);
			int ascii = (int) currentCharacter;
			
			if (ascii >= 65 && ascii <= 90) {
                hasUpper = true;
            } else if (ascii >= 97 && ascii <= 122) {
                hasLower = true;
            } else if (ascii >= 48 && ascii <= 57) {
                hasDigit = true;
            } else if ((ascii >= 33 && ascii <= 47) || (ascii >= 58 && ascii <= 64) || (ascii >= 91 && ascii <= 96) || (ascii >= 123 && ascii <= 126)){
                hasSpecialChar = true;
            }
		}
		
		if (hasUpper && hasLower && hasDigit && hasSpecialChar) {
            return true;
		}
		else
			return false;
		
		
		
	}
	public static void main(String args[]) {
		
		Scanner sc=new Scanner (System.in);
		System.out.println("Enter password such it follows the following parameters:");
		System.out.println("1.Should be atleast 8 characters.");
		System.out.println("2.Atleast one numerical");
		System.out.println("3.Atleast one Uppercase");
		System.out.println("4.Atleast one special character");

		String password=sc.next();
		boolean passwordStatus=checkPassword(password);
		if(passwordStatus) {
			System.out.println("Your password is Accepted.");
		}
		else
		{
			System.out.println("Your password is Not Accepted!.Create new one");
		}
		
		sc.close();
		
	}

}
