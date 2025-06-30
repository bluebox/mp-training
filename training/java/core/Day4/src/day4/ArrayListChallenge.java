package day4;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListChallenge {
	public static void main(String[] args) {
		ArrayList<String> groceries=new ArrayList<>();
		Scanner sc=new Scanner(System.in);
		boolean flag=true;
		while(flag) {
			System.out.println("enter the choice : ");
			System.out.println("""
					0 - To ShutDown
					1 - To add items to list
					2 - To remove items from the list
					3 - To print the list
					""");
			int n=sc.nextInt();
			sc.nextLine();
			switch(n) {
			case 1:
				System.out.println("enter the elements to add into list : ");
				//sc.next();
				String str1=sc.nextLine();
				addItems(groceries,str1);
				break;
			case 2:
				System.out.println("Enter the elements to remove from the list :");
				sc.next();
				String str2=sc.nextLine();
				removeItems(groceries,str2);
				break;
			case 3:
				if(groceries.isEmpty()) {
					System.out.println("Empty list");
					break;
				}
				System.out.println(groceries);
				break;
			case 0:
				flag=false;
				break;
			default:
				System.out.println("Invalid input ");
			}
		}
	}
	
	public static void addItems(ArrayList<String> groceries , String str1) {
		String[] s=str1.split(",");
		for(String str:s) {
			if(groceries.contains(str)) {
				System.out.println(str+" is already present");
			}
			else {
				System.out.println(str+" is added into the list");
				groceries.add(str);
			}
		}
		System.out.println("-".repeat(30));
	}
	
	public static void removeItems(ArrayList<String> groceries , String str1) {
		String[] s=str1.split(",");
		for(String str:s) {
			if(!groceries.contains(str)) {
				System.out.println(str+" is not present");
			}
			else {
				System.out.println(str+" is removed from the list ");
				groceries.remove(str);
			}
		}
		System.out.println("-".repeat(30));
		}
}
