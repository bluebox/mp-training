package problems;
import java.util.*;
public class day2_2 {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		
		// the word to number challenge
		 System.out.println("Enter the number in words to find the numeric form:");
		String number=sc.nextLine().toLowerCase();
        System.out.println(printNumberFromWord(number));
        
        
        //Switch challenge
        System.out.println("Enter the letters between A and E");
        String ch=sc.nextLine();
        System.out.println(NatoCode(ch));
        
        // A playing Cat
        System.out.println("Enter the Temperature");
        int temperature=Integer.parseInt(sc.nextLine());
        System.out.println("Enter the true for summer and false for other seasons");
        boolean issummer=Boolean.parseBoolean(sc.nextLine());
        System.out.println(isCatPlaying(temperature,issummer)+"is telling whether the cat iss playing or not");
        
        //equality printer
        int num=Integer.parseInt(sc.nextLine());
        int num_1=Integer.parseInt(sc.nextLine());
        int num_2=Integer.parseInt(sc.nextLine());
        System.out.println(findEquality(num,num_1,num_2));
        
        //area calculator
        System.out.println("Area of circle requires radius");
        double radius=Double.parseDouble(sc.nextLine());
        System.out.println(area(radius));
        System.out.println("Area of rectangle requires length and breadth");
        double length=Double.parseDouble(sc.nextLine());
        double breadth=Double.parseDouble(sc.nextLine());
        System.out.println(area(length,breadth));
        
        //Minutes to Years and Days calculator
        System.out.println("Enter the number of minutes to find the year and days passed:");
        long minutes=Long.parseLong(sc.nextLine());
        System.out.println(findyearandmonth(minutes));
        
        
        //get the time in HH:MM:SS
        System.out.println("Eneter the mode want to continue only seconds(true) or minutes and seconds(false) ");
        boolean isvalid=sc.nextBoolean();
        if(isvalid) {
        	System.out.println("Eneter the seconds as seleced");
        int seconds=Integer.parseInt(sc.nextLine());
        System.out.println(getDuration(seconds));
        }else {
        	System.out.println("Eneter the minutes for calculation as seleced");
        int minutes_1=Integer.parseInt(sc.nextLine());
        System.out.println("Eneter the seconds as seleced");
        int seconds_1=Integer.parseInt(sc.nextLine());
        System.out.println(getDuration(seconds_1,minutes_1));
        }
	}
	
	public static String getDuration(int seconds) {
		  if(seconds<0)return "Invalid Input";
		  int minutes=seconds/60;
		return  getDuration(seconds,minutes);
	}
	
	
	public static String getDuration(int sec,int min) {
		if(sec<0 || min>59 || min<0 ) {
			return "Invalid Input";
		}
		int hrs=min/60;
		int remaining_min=min%60;
		int remaining_sec=sec%60;
		
		return "HH:"+hrs+"MM:"+remaining_min+"SS:"+remaining_sec;	
	}
	
	public static String findyearandmonth(long minutes) {
		if(minutes <0 ) {
			return "Invalid Value";
		}else {
			long hours=minutes/60;
			long days=hours/24;
			long years=days/365;
			long day_s=days%365;
			return minutes+"  min = "+years +" y and days "+ day_s ;
			}
	}
	
	public static double area(double r) {
		if(r<0.0) {
			return -1.0;
		}
		return 3.14*r*r;
	}
	
	public static double area(double w,double l) {
		if(w<0.0 || l<0.0) {
			return -1.0;
		}
		return w*l;
	}
	
	
	public static String findEquality(int num,int num_1,int num_2) {
		   if(num<0 || num_1<0 || num_2<0) {
			   return "Invalid Value";
		   }else if(num==num_1 && num==num_2 && num_2==num_1) {
			   return "All the Numbers are Equal";
		   }else if(num!=num_1 || num!=num_2 || num_2!=num_1){
			   return "All the numbers are Different";
		   }
		   return "Neither all are equal or Different";
	}
	
	public static boolean isCatPlaying(int temp,boolean bool) {
		if(bool && (temp>25 && temp<45)) {
			return true;
		}
		else if(!bool && (temp>25 && temp<35)) {
			return true;
		}
		
		return false;
	}
	
	
	public static String NatoCode(String ch) {
		switch(ch) {
		case "A":case "a":
			return "Able";
		case "B":case "b":
			return "Baker";
		case "C":case "c":
			return "Charlie";
		case "D":case "d":
			return "Dog";	
		case "E":case "e":
			return "Easy";	
	    default :
	    	return "not found";
}
	}
	
	
	public static int printNumberFromWord(String num) {
		return switch(num) {
		case "one"->1;
		case "two"->2;
		case "three"->3;
		case "four"->4;
		case "five"->5;
		case "six"->6;
		case "seven"->7;
		case "eight"->8;
		case "nine"->9;
		default->-1;
		};
	}

}
