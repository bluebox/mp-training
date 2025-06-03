package javaPrograms;
import java.util.*;
public class LeapYear {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the year : ");
		int year=sc.nextInt();
		if(year<0) {
			System.out.println("The year "+year+" is invalid");
		}
		else {
			if((year%4==0 && year%100!=0) || (year%100==0 && year%400==0)) {
				System.out.println("The year "+year+" is Leap year");
			}
			else {
				System.out.println("The year "+year+" is not Leap year");
			}
		}
	}
}
