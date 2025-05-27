import java.util.*;
public class LeapYear {
    public static boolean isLeapYear(int year) {
    	if(year<0)return false;
    	if((year%100==0 && year%400==0) || (year%4==0 && year%100!=0))return true;
    	return false;
    }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int year=sc.nextInt();
		if(isLeapYear(year)) {
			System.out.print(year+" this is a Leap year");
		}
		else {
			System.out.print(year +" this is not a Leap Year");
		}
	}

}
