package com.tulasidhar.june30.ArrayListChallenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ArrayListChallenge {
	public static void main(String[] args) {
		ArrayList<String> groceries = new ArrayList<String>();
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			boolean exit = false;
			System.out.println("1- Add Item to list\n2- Remove any item\n0- Exit\n");
			System.out.println("Enter your option");
			String input = sc.next();
			
			switch(input) {
				case "1":
					System.out.println("enter the item name");
					String item = sc.next();
					if(groceries.contains(item)) {
						System.out.println("Item already exists");
					}else {
						groceries.add(item);
					}
					printList(groceries);
					break;
					
				case "2":
					System.out.println("enter the item name to remove");
					String item1 = sc.next();
					if(groceries.contains(item1)) {
						groceries.remove(item1);
						System.out.println("Item deleted!");
					}else {
						System.out.println("Cannot find item");
					}
					printList(groceries);
					break;
	
				case "0":
					exit = true;
					break;
					
				default:
					System.out.println("enter a valid input");
					break;
			}
			if(exit) break;
		}
	}
	
	static void printList(ArrayList<String> groceries) {
		Collections.sort(groceries);
		System.out.println(groceries);
	}
	
}
