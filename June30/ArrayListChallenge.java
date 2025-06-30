package June30;

import java.util.Scanner;
import java.util.ArrayList;

public class ArrayListChallenge {
	private static ArrayList<Integer> arr=new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		boolean flag=false;
		while (true) {
			System.out.println("\nSelect an option from the below");
			System.out.println("1. Add an item");
			System.out.println("2. Remove an item");
			System.out.println("3. Quit");
			System.out.print("Enter your choice :");
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				System.out.print("Enter the number to add : ");
				int addItem=sc.nextInt();
				addItems(addItem);
				System.out.print("Array elements after adding : ");
				print();
				break;
			case 2:
				System.out.print("Enter the number to remove : ");
				int removeItem=sc.nextInt();
				removeItems(removeItem);
				System.out.print("Array elements after removing : ");
				print();
				break;
			case 3:
				flag=true;
				System.out.println("Existing");
				break;
			}
			if(flag) break;
		}
	}
	public static void addItems(int a) {
		if(!arr.contains(a)) {
			arr.add(a);
			System.out.println(a + " is added to the list");
		}
		else
			System.out.println(a + " is already present in the list");
	}
	public static void removeItems(int a) {
		if(arr.contains(a)) {
			arr.remove(Integer.valueOf(a));
			System.out.println(a + " is removed from the list");
		}
		else
			System.out.println(a + " is not present in the list");
	}
	public static void print() {
		for(int i:arr) {
			System.out.print(i+" ");
		}
	}
}
