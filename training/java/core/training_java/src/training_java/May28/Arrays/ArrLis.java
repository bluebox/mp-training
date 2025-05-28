package Arrays;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrLis {
	public  static void displayMenu() {
		System.out.println(" 0--->to shutdown");
		System.out.println("1--->to add");
		System.out.println(" 2-->to remove");	
	
	}
	public static void add(Scanner sc,ArrayList<String> groceryList) {
		System.out.println(" Enter the items to be added");
		String list=sc.next();
		String[] items=list.split(",");
		for(var item: items) {
			if(!groceryList.contains(item)) {
				groceryList.add(item);
			}
		}
			groceryList.sort(null);;
			System.out.println(groceryList);	
		
	}
	public static void remove(Scanner sc,ArrayList<String> groceryList) {
		System.out.println(" Enter the items to be removed");
		String list=sc.next();
		String[] items=list.split(",");
		for(var item: items) {
			if(groceryList.contains(item)) {
				groceryList.remove(item);
			}
			else {
			System.out.println(item+ " is Not there to remove");
			}
		}
			groceryList.sort(null);;
			System.out.println(groceryList);	
		
	}

	public static void main(String[] args) {
		ArrayList<String> grocery=new ArrayList<String>();
		grocery.add("Sugar");
		grocery.add("Milk");
		Scanner sc=new Scanner(System.in);
		displayMenu();
		int choice=sc.nextInt();
		while(choice!=0) {
			
			switch(choice) {
			case 1 : add(sc,grocery);
			break;
			case 2: remove(sc,grocery);
			break;
			default : System.out.println("Enter valid choice");
			break;
			}
			displayMenu();
			choice=sc.nextInt();
			
		}
		System.out.println("System Shutdown");
	}

}
