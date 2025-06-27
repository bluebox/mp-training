package problems;
import java.util.*;
public class Day2_4 {
	public static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		
		// Managing Floor pack requirements
		System.out.println("Eneter the Big Count for floor:");
      int  bigCount=Integer.parseInt(sc.nextLine());
  	System.out.println("Eneter the small Count for floor:");
      int  smallCount=Integer.parseInt(sc.nextLine());
  	System.out.println("Eneter the Goal for floor:");
      int  goal=Integer.parseInt(sc.nextLine());
      System.out.println(canPack(bigCount,smallCount,goal));
		
      //Largest prime factor
  	System.out.println("Eneter the number for largest prime Factor:");
      int num=Integer.parseInt(sc.nextLine());
      System.out.println(getLargestPrime(num));
      
      //Reading userInput using Scanner class
      for(int i=1;i<=5;i++) {
    	  System.out.println("Eneter the number #"+i);
    	  int num_1=Integer.parseInt(sc.nextLine());
    	  int sum=0;
    	  if(num_1<0) {
    		  System.out.println("Eneter the valid number #"); 
    		  i=i-1;
    	  }
    	  sum+=num_1;
    	  System.out.println(sum);
      }
      
      //min and max challenge
      boolean isint=false;
      int min=0;
      int max=0;
      do {
    	  try {
    	 System.out.println("Eneter the Number or characetr to Quit");
    	 int num_1=sc.nextInt();
    	 isint=true;
    	 if(num_1<min) {
    		 min=num_1;
    	 }else if(num_1>max) {
    		 max=num_1;
    	 }
    	  }catch(NumberFormatException e) {
    		  System.out.println("you entered character or Double");
    		  break;
    		  
    	  }
      }while(isint);
      
      
      // sum and average 
      findSumAndAvg();
      
      //get Bucket count
      System.out.println("Eneter the width of Wall");
      Double width=Double.parseDouble(sc.nextLine());
      System.out.println("Eneter the Height of Wall");
      Double Height=Double.parseDouble(sc.nextLine());
      System.out.println("Eneter the Area of wall covered per bucket of Paint");
      Double AreaperBucket=Double.parseDouble(sc.nextLine());
      System.out.println("Eneter the number of buckets of Paint");
      Double buckets=Double.parseDouble(sc.nextLine());
      
      System.out.println(getBucketCount(width,Height,AreaperBucket,buckets));
            
      //construct a Diagonal star pattern with the loops
      System.out.println("Eneter the number to print Square where it should be greater tahn or Equal to 5:");
      int side=Integer.parseInt(sc.nextLine());
      generateSquare(side);
      
	}
	
	
		
		
		
	public static void generateSquare(int length) {
		if(length<5) {
			System.out.println("Invalid Input");
			return;
		}
		
		for(int i=0;i<length;i++) {
			System.out.print("*");
		}
		System.out.println();
		for(int i=1;i<length-1;i++) {
			for(int j=0;j<length;j++) {
				if((j==0 || j==(length-1))) {
					System.out.print("*");
				}
				else if(i==j || (((length-1)-i))==j) {
					System.out.print("*");
				}
				else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		
		for(int i=0;i<length;i++) {
			System.out.print("*");
		}
		
	}

	
	public static int getBucketCount(double a,double b ,double c,double d) {
		if(a<0 || b<0 || c<0 || d<0)return -1;
		double area=a*b;
		double required=area/c;
		return (int)Math.ceil(required-d);
	}
	
	public static void findSumAndAvg() {
		boolean isint=false;
		int sum=0;
		int avg=0;
		 do {
	    	  try {
	    	 System.out.println("Eneter the Number or characetr to Quit");
	    	 int num_1=sc.nextInt();
	    	 isint=true;
	    	 sum+=num_1;
	    	 avg++;
	    	  }catch(NumberFormatException e) {
	    		  System.out.println("you entered character or Double");
	    		  System.out.println("sum "+sum+"avg "+sum/avg);
	    		  break;
	    	  }
	      }while(isint);
	      
	}
	
	public static int getLargestPrime(int n) {
		int maxi=-1;
		for(int i=2;i<=n/2;i++) {
			if(n%i==0) {
				if(i%2==0) {
					maxi=i;
				}
			}
		}
		return maxi;
	}
	
	
	public static boolean canPack(int b,int s,int g) {
		if((b*5)<=g) {
			return true;
		}
		
		return false;
	}

}
