package problems;
import java.util.*;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Length of the Array");
		int length=sc.nextInt();
		int arr[]=new int[length];
		for(int i=0;i<length;i++){
			System.out.println("Enter the element of the Array");
			arr[i]=sc.nextInt();
		}
		
		System.out.println("check");
     Set<Integer> set=new HashSet<>();
 	int min=Integer.MAX_VALUE;
	int max=Integer.MIN_VALUE;
		for(int i=0;i<length;i++) {
			set.add(arr[i]);
			System.out.println("check1");
			if(arr[i]>max) {
				max=arr[i];
			}
		    if(arr[i]<min) {
				min=arr[i];
			}
		    System.out.println("check2");
		}
		
		int res=max-min+1;		
		System.out.println(min+" "+max+" "+set.size());
		if(max-min+1==(set.size())) {
			System.out.println("All the numbers are present");
		}
		
		for(int start=min;start<max;start++) {
			if(!set.contains(start)){
				System.out.println(start+" is the missing value in the array");
				break;
			}	
		}
		
		
		
		//day_2_5_AccountClass account=new Account();
		//account.setName("XYZ");
		
	
//		Day_2_Calculator s=new Day_2_Calculator();
//		System.out.println(s);
		

	}	
	}
	

