import java.util.Scanner;
public class LeapYear {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Year:");
		int year=sc.nextInt();
		if(year<0) System.out.println("Not a Valid Year");
		else {
			boolean is_Leap=isLeap(year);
		    if(is_Leap==true) {
		    	System.out.println("Leap Year");
		    }
		    else{
		    	System.out.println("Not a Leap Year");
		    }
		    System.out.println("Enter the month:");
		    int month=sc.nextInt();
		    int no_Of_Days=days(year,month);
		    System.out.println("The total number of days are:"+ no_Of_Days);
		}
		
		

	}
	public static boolean isLeap(int year) {
		if(year%4==0) {
			if(year%100==0 && year%400!=0) {
				return false;
			}
			else {
				return true;
			}
			
		}
		else {
			return false;
		}
	}
	public static int days(int year,int month) {
		if(month==1) return 31;
		else if(month==2) {
			if(isLeap(year)==true) return 29;
			return 28;
		}
		else if(month==3) return 31;
		else if(month==4) return 30;
		else if(month==5) return 31;
		else if(month==6) return 30;
		else if(month==7) return 31;
		else if(month==8) return 31 ;
		else if(month==9) return 30;
		else if(month==10) return 31;
		else if(month==11) return 30;
		else if(month==12)return  31;
		else return -1;
	}

}
