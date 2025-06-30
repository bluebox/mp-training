package com.day4_;

import java.util.ArrayList;
import java.util.Scanner;

public class Grocery_Arraylist {

	public static void main(String[] args) {
		ArrayList<String> array = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("0 - to shutdown\n" 
		+ "1 - to add items(s) to list (comma delimited list)\n" 
		+ "2 - to remove any items (comma delimited list)\n"
		+ "3 - print arraylist\n"
		+ "Enter a number for which action you want to do:");
			int n=scanner.nextInt();
			scanner.nextLine();
			switch (n){
				case 0:
					System.out.println("Exiting...");
					return;
				case 1:
					array=addToList(array,scanner);
					break;
				case 2:
					array=removeFromList(array,scanner);
					break;
				case 3:
					System.out.println(array);
					break;
				default:
					System.out.println("invalid choice");
			}
		}
	}

	private static ArrayList<String> addToList(ArrayList<String> arrayList, Scanner scanner) {
		String[] strings=scanner.nextLine().split(",");
		for (String i : strings) {
			if (!arrayList.contains(i)) {
				arrayList.add(i);
			}
		}
		return arrayList;
	}

	private static ArrayList<String> removeFromList(ArrayList<String> arrayList, Scanner scanner) {
		String[] strings=scanner.nextLine().split(",");
			for (String i : strings) {
			if (arrayList.contains(i)) {
				arrayList.remove(i);
			}
		}
		return arrayList;
	}
}