package day_2_7_25;

import java.util.*;
public class DailyProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number to get ones in binary");
        int temp=Integer.parseInt(sc.nextLine());
        int sum=0;
        for(int i=1;i<=temp;i++) {
        	int count=0;
        	int input=i;
        	while(input>0) {
        		int bit=0;
        		bit=input&1;
        		if(bit==1) {
        			count++;
        		}
        		input=input>>1;
        	}
        	sum+=count;
        	System.out.println("in "+i+" the count of setbits is "+count);
        	
        }
        System.out.println(" the sum of setbits is "+sum);
	}

}
