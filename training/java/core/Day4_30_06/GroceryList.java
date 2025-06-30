package Day4_30_06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GroceryList {
	static Scanner sc=new Scanner(System.in);
	static ArrayList<String> lis=new ArrayList<>();
	public static void main(String args[]) {
		
		boolean exit=false;
		while(!exit) {
			System.out.println("""
					Choose an option
					0.exit
					1.add elements
					2.remove elements
					""");
			String inp=sc.nextLine();
			switch(inp) {
			case "0":
				exit=true;
				break;
			case "1":
				addElements();
				break;
			case "2":
				removeElements();
				break;
			default:
				System.out.println(lis.toString());
			}
			
		}
	}
	public static void addElements() {
		System.out.println("Enter the items seperated by ,");
		String[] inp=sc.nextLine().split(",");
		for(String s:inp) {
			if(!lis.contains(s)) {
				lis.add(s);
			}
		}
		Collections.sort(lis);
		System.out.println(lis.toString());
	}
	public static void removeElements() {
		System.out.println("Enter the items seperated by ,");
		String[] inp=sc.nextLine().split(",");
		for(String s:inp) {
			if(lis.contains(s)) {
				lis.remove(s);
			}
		}
		Collections.sort(lis);
		System.out.println(lis.toString());
	}
	
}
