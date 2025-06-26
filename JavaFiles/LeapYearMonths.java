import java.util.Scanner;

public class LeapYearMonths {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the Month and Year: ");
		
		int month=sc.nextInt(),year=sc.nextInt();
		
		System.out.println("The no.of days in the given month and year is: "+ getDaysInMonth(month,year));

	}
	
	public static boolean isLeap(int year) {
		if(year%4==0) {
			if(year%100==0) {
				if(year%400==0) {
					return true;
				}
				return false;
			}
			return true;
		}
		return false;
	}
	
	public static int getDaysInMonth(int month,int year) {
		
		int[] arr= {31,28,31,30,31,30,31,31,30,31,30,31};
		
		if(month<1 || month >12 || year<1 || year>9999) {
			return -1;
		}
		
		if(isLeap(year)) {
			if(month==2) {
				return arr[month-1]+1;
			}
			else {
				return arr[month-1];
			}
		}
		else {
			return arr[month-1];
		}
		
	}

}
