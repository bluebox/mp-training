package arrayListChallenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ArrayListChallenge {
	
	public static void main(String[] args) {
		
		ArrayList<String> groceryList = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;
		while(!exit) {
			 
			System.out.println("Available actions:");
			System.out.println("0 - to shutdown");
			System.out.println("1 - to add item(s) to list (comma delimited list)");
			System.out.println("2 - to remove any items (comma delimited list)");
			System.out.println("Enter a number for which action you want to do: ");
			
			int option = scanner.nextInt();
			
			switch(option) {
			case 0:
				System.out.println("Sucessfully Shutdown ");
				exit = true;
				break;
				
			case 1:
				System.out.println("Enter comma separated item(s) to add :");
				scanner.nextLine();
				String[] items=(scanner.nextLine()).split(",");
				for(String item: items) {
					if(groceryList.contains(item)) {
						System.out.println("Already item exits "+item);
					}
					else {
						groceryList.add(item);
					}
				}
				
				printItems(groceryList);
				break;
				
			case 2:
				System.out.println("Enter comma separted item(s) to remove : ");
				scanner.nextLine();
				String[] removingItems=(scanner.nextLine()).split(",");
				for(String item:removingItems) {
					
					if(groceryList.contains(item)) {
						groceryList.remove(item);
					}
					else {
						System.out.println("Item not found");
					}
				}
				printItems(groceryList);
				break;
			default:
				System.out.println("Enter Correct option");
			}
			
		}
		
		scanner.close();
	}
	
	public static void printItems(ArrayList<String> items) {
		
		Collections.sort(items);
		
		for(String ele : items) {
			System.out.print(ele + " ");
		}
		System.out.println();
	}

}
