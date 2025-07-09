package com.prana;

import java.util.Scanner;

public class Mini1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String st = "Hello World";
        boolean matc = st.matches("Hello World");
        System.out.println(matc);
        
        Scanner sc=new Scanner(System.in);
		System.out.println("Enter the string");
		String s=sc.nextLine();
		System.out.println("Enter the string");
		String s1=sc.nextLine();
		boolean match=s.matches(s1);
		System.out.println(match);

	}

}
