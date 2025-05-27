import java.util.Scanner;

public class LeapYear {
	public static boolean isLeapYear(int year) {
		if(year<0) return false;
		if(year%100==0) {
			return year%400 ==0;
		}
		return year%4==0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner (System.in);
		int year=sc.nextInt();
		boolean result=isLeapYear(year);
		System.out.print(result);

	}

}
