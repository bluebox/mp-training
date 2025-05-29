package day6.barkingDog;
import java.util.*;

public class BarkingDog {
public static void main(String args[]) {
	Scanner sc=new Scanner(System.in);
	WakeOrNot wk=new WakeOrNot();
	int t=0;
	String s;
	while(true) {
		if(t==-1)
			break;
		System.out.println("Enter Y if dog is barking ,N if not barking");
		s=sc.next();
		if(!s.matches("[YNyn]")) {
			System.out.println("Enter either Y or N");
			continue;
		}
		System.out.println("Enter the time in 24 hour clock format");
		t=sc.nextInt();
		if(t<0 || t>24) {
			System.out.println("Enter the valid time");
			continue;
		}
		boolean bark= s.equals("Y")|| s.equals("y") ? true:false;
		if(wk.shouldWakeUp(bark,t))
		System.out.println("Wake up dog is barking");
		else 
			System.out.println("keep sleeping");
		
		System.out.println("----------------------");
		
	}
	
}

}
