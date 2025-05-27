package ArrayChallenges;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListStore {
	public static void addItems(ArrayList<String> items) {
		Scanner s = new Scanner(System.in);

		int numberOfItems;
		System.out.println("Enter how many items you want to add.");
		numberOfItems = s.nextInt();
		System.out.println("Enter item names.");
		for (int i = 0; i <= numberOfItems; i++) {
			String name = s.nextLine();
			items.add(name);
		}
		System.out.println("Items added successfully.\nAnd its the updated items list");
		for(String item:items) {
			System.out.println(item);
		}
	}

	public static void removeItems(ArrayList<String> items) {
		Scanner r = new Scanner(System.in);
		int numberOfItems;
		System.out.println("Enter how many items you want to remove.");
		numberOfItems = r.nextInt();
		System.out.println("Enter item names to remove.");
		for (int i = 0; i <=numberOfItems; i++) {
			String name = r.nextLine();
			if(items.contains(name)) {
				items.remove(name);
			}
			else
				System.out.println("Item not present");
		}
		System.out.println("Items removed successfully.\nAnd its the updated items list");
		for(String item:items) {
			System.out.println(item);
		}

	}
	
//	public static void print(ArrayList<String> items) {
//		for(String item:items) {
//			System.out.println(item);
//		}
//	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		ArrayList<String> items = new ArrayList<>();
		
		while(true) {
			System.out.println("Enter you choice.\n1.Add items\n2.remove items\n3.Exit");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				addItems(items);
				break;
			case 2:
				removeItems(items);
				break;
			default:
				System.out.println("Exiting");
				return;
			}
			
		}
		

	}

}
