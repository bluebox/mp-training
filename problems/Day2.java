package problems;
import java.util.*;
public class Day2 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		
		
	    // Digit Sum challenge
		System.out.println("Eneter the number:");
		int number=sc.nextInt();
		int returnedValue=sumDigits(number);
        System.out.println(returnedValue+"is the sum of Digits in the goiven number");
        
        
        // is Even number challenge - step1
        System.out.println("Eneter the number:");
        int number1=sc.nextInt();
        if(isEvenNumber(number1)) {
        	System.out.println(number1+" number is an Even number");
        }else {
        	System.out.println(number1+" number is an "
        			+ "Odd Number");
        }
        
        // is Even number challenge - step2
        System.out.println("Eneter the number start :");
        int number_2=sc.nextInt();
        System.out.println("Eneter the number end:");
        int number_3=sc.nextInt();
        System.out.println("Eneter the number for range of even nums to be printed:");
        int rangeorder=sc.nextInt();
        int evennums=0;
        while((number_2<=number_3 )&&(evennums<rangeorder)) {
        	if(isEvenNumber(number_2)) {
        		System.out.println(number_2);
        		evennums++;
        	}
        	number_2++;
        }
        System.out.println(evennums +"are the numbers to be printed in the" +rangeorder);
        
         // summing odd numbers in a range step 1
        System.out.println("Eneter the number to check if odd :");
        int checking=sc.nextInt();
         System.out.println("The number is an odd number is a "+isOdd(checking)+" statement");
        
         
         // summing odd numbers in a range step 2
         System.out.println("Eneter the number start :");
         int start=sc.nextInt();
         System.out.println("Eneter the number end:");
         int end=sc.nextInt();
        int sumOddNums=sumOdds(start,end);
        System.out.println(sumOddNums+"is the sum of odd numbers in the range "+start+":"+end);
        
        // summing 3 and 5 challenge
        int foundmatches=0;
        int matches_Sum=0;        
        System.out.println("Eneter the number  :");
        System.out.println("Eneter the number start for remainder check  challenge:");
        int start_1=sc.nextInt();
        System.out.println("Eneter the number end for remainder check  challenge:");
        int end_1=sc.nextInt();
        for(int j=start_1;j<=end_1;j++) {
        	if(foundmatches==5)break;
        	if((j%3==0)&&(j%5==0)) {
        		foundmatches++;
        		System.out.println("foundmatch "+j);
        		matches_Sum=matches_Sum+j;
        	}
        }
        
        System.out.println("sum of the matched numbers is "+matches_Sum);
        
        
        // prime number counter challenge
        System.out.println("Eneter the number for prime number start range :");
		int isPrimeStart=sc.nextInt();
		 System.out.println("Eneter the number for prime number end range :");
        int isPrimeEnd=sc.nextInt();
        int primecounter=0;
        for(int num=isPrimeStart;(num<=isPrimeEnd)&&(primecounter<3);num++) {
        	if(isPrime(num)) {
        		System.out.println(num+" is a prime numbner");
        		primecounter++;
        	}
        }
        
        // for statement using double iterator
        System.out.println("Enter the amount in $");
        double amount=sc.nextInt();
        System.out.println("Enter the number  for start of range");
        double s=sc.nextDouble();
        System.out.println("Enter the number  for end of range");
        double e=sc.nextDouble();
        System.out.println("Enter the number  for rate of interest in $");
        double rate=sc.nextDouble();
        for(double i=s;i<e;i=i+rate) {
        	amount=amount+amount*i;
        }
        
        System.out.println("the amount after the interest is "+amount);
        
        
        // checking Leap year
        do {
        	System.out.println("Eneter the valid year to check if Leap year:");
        	int year=sc.nextInt();
          if(year>1 && year<10000) {
        	  if(isLeapYear(year)) {
        		  System.out.println("this is a leap year");
        	  }else {
        		  System.out.println("this is not a leap year");
        	  }
        	  break;
          }else {
        	  System.out.println("Enter the valid year");
          }
        }while(true);
        
        
        //get days length in month\
        do {
        	System.out.println("Eneter the valid month to check if Leap year month days:");
        int month=sc.nextInt();
        System.out.println("Eneter the valid year to check if Leap year for days:");
        int year=sc.nextInt();
        if(month>=1 && month<10000 && year>1 && year<=9999) {
        getdaysofmonth(month,year);  	
        break;
        }
        else if(month<1 && month>12) {
        	System.out.println("Enter valid month between 1 and 12");
        }
        else if(year<1 && year>9999) {
        	System.out.println("Enter the valid year");}
        
        }while(true);
        
	}
	
	public static void getdaysofmonth(int month,int year) {
		if(isLeapYear(year)) {
			if(month==2) {
				System.out.println("the number of days in this month are 29");
			}else {
				System.out.println(findmonth(month));
			}
		}else {
			if(month==2) {
				System.out.println("the number of days in this month are 28");
			}else {
				System.out.println(findmonth(month));
			}
		}
	}
	
	public static String findmonth(int month) {
		return switch(month) {
		case 1,3,5,7,8,10,12->"the number of days in this month is 31";
		default->"the number of days in this month are 30";
		};
	}
	
	
	public static boolean isLeapYear(int year){
	   if(year%100!=0 && year%4==0) {
		   return true;
	   }
		else if((year%400==0)) {
			return true;
		}
		return false;
	}
	
	public static boolean isPrime(int num) {
		if(num<=2) {
			return (num==2);
		}
		
		for(int i=2;i<=(num/2);i++) {
			if(num%i==0) {
				return false;
			}
		}
		
		return true;
		
	}
	public static int sumOdds(int start,int end) {
		int sum=0;
		for(int i=start;i<=end;i++){
			if(isOdd(i)) {
				sum+=i;
			}
		}
		return sum;
	}
	
	public static boolean isOdd(int start) {
		if(start<0)return false;
		if(start%2 != 0){
			return true;
		}
		return false;
	}
	
	
	public static boolean isEvenNumber(int num){
		if(num%2==0) {
			return true;
		}
		
		return false;
	}
	
	public static int sumDigits(int number) {
		
		if(number<0) {
			return -1;
		}else if(number <10) {
			return number;
		}
		
		int sum=0;
		while(number>0) {
			sum+=number%10;
			number=number/10;
		}
		return sum;
	}

}
