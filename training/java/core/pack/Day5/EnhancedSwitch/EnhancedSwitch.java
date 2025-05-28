package Day5.EnhancedSwitch;

import java.util.Scanner;

public class EnhancedSwitch {
public static void main(String args[]) {
	
	Scanner sc=new Scanner(System.in);
	
	System.out.println("Enter the Cricketer name [kohli,Rohith,Gill,Pandya,Shami,Bumrah] to get the role and Q to quit");
	
	while(true) {
	String s=sc.next();
	if(s.equals("Q") || s.equals("q"))
		break;
	
	String result=performSwitchOperation(s);
		System.out.println(s+ " is "+result);
	
	
}
}
public static String performSwitchOperation(String s) {
	String res= switch(s){
	case "Kohli" -> "one down";
	case  "Rohith" -> "Opener";
	case   "Gill" -> "Opener";
	case  "Pandya" -> "Middle order";
	case "Shami" ->"Tailender";
	case "Bumrah" -> "Tailender";
	 default -> "Enter form the given list only";
			
	};
	return res;
}
}
