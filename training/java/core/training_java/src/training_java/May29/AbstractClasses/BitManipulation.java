package AbstractClasses;

import java.util.ArrayList;
import java.util.Scanner;

public class BitManipulation {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int xor=0;
		int x=0;
		int y=0;
		ArrayList<Integer> lis=new ArrayList<Integer>();
		System.out.println("Enter size:");
		int len=sc.nextInt();
		System.out.println("Enter elements: ");
		for(int i=0;i<len;i++) {
			int ele=sc.nextInt();
			lis.add(ele);
		}
		for(var ele:lis) {
			xor=xor^ele;
		}
		System.out.println("Xor result is : "+xor);
		int temp=xor;
		int setBit=1;
		while((temp & 1) == 0) {
			temp=temp>>1;
		    setBit+=1;
			
		}
		
		for(int ele: lis) {
			
			if(((ele>>(setBit-1)) & 1)==1) {
				
				x=x^ele;	
			}
			else {
				y=y^ele;
			}
		}
		
		System.out.println("X is :  "+ x+" Y is :  "+y);
		sc.close();
	}

}
