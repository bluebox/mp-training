package june26_Loops;
import java.util.Scanner;
public class MonthLengthWithLeapYear {
	public static boolean isLeapYear(int year) {
		if(year<1 || year>9999)
			return false;
		return (year%4==0 && year%100!=0)||(year%400==0);
	}
	
	public static int getDaysInMonth(int month,int year) {
		if((month<1 || month>12)||(year<1 || year>9999))
			return -1;
		return switch(month) {
		case 1,3,5,7,8,10,12 ->  31;
		case 4,6,9,11 -> 30;
		case 2 -> (isLeapYear(year)?29:28);
		default -> -1;
		};
	}
	
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter year and month");
		int year=sc.nextInt();
		int month=sc.nextInt();
		int days=getDaysInMonth(month,year);
		System.out.println("Number of days in "+month+" month "+year+" year "+": "+days );
		sc.close();
	}
}
