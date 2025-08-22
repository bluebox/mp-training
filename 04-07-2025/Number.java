package com.prana;

import java.util.Scanner;

public class Number {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a Non-Negative Integer:");
        int n = sc.nextInt();
        if(n<0) {
        	System.out.println("Inavlid Input");
        }
        else {
        	int p=num(n);
            System.out.println(p);
        	}
        }
	
	public static int num(int n) {
		
		//int s=0,r=0;
		  
		//r=n%10;
		//s+=r;
		//n=n/10;
		
		//return s;
		return (n % 9 == 0) ? 9 : (n % 9);
		}
}
