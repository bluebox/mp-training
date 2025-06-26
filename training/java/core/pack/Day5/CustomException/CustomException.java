package Day5.CustomException;
import java.util.*;

public class CustomException {
	
public static void main(String args[]) throws InvalidInputException  {
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter the string that contain only alphabets");
	String s=sc.next();
	
	try {
	if(!s.matches("[a-zA-Z]")) 
		throw new InvalidInputException("invalid input");
	
	
	System.out.println("You have entered "+s);
	
}
	catch(InvalidInputException e) {
		System.out.println("Only alphabets are allowed");
	}
}
}


class InvalidInputException extends Exception{
	public InvalidInputException(String message){
		super(message);
	}
}
