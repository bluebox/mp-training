package oops.Encapsulation;

import java.util.*;
public class PrinterClass {
	public static void main(String []args)
	{
		Scanner sc=new Scanner(System.in);
		Printer p=new Printer(1,true);
		System.out.println(p.printPages(10));
		
	}

}
