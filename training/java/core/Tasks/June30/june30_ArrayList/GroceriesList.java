package corejava.june30_ArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GroceriesList {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int option;
		Groceries Items=new Groceries();
		do {
			System.out.println("---------------------Available Actions---------------------");
			System.out.println("\t0 - Shutdown");
			System.out.println("\t1 - add item(s) to list(comma delimited list)");
			System.out.println("\t2 - remove any item(s) from the list(comma delimited list)");
			System.out.println("Enter a number for which action you want to do: ");
			option=sc.nextInt();
			switch(option) {
			case 0:System.out.println("Shutting down...");
				break;
			case 1:
				ArrayList<String> groceries=new ArrayList<>();
				System.out.println("Enter the list of groceries to add to list seperating with ','");
				String[] values=sc.next().split(",");
				groceries.addAll(Arrays.asList(values));
				Items.setGroceries(groceries);
				break;
			case 2:
				ArrayList<String> groceries1=new ArrayList<>();
				System.out.println("Enter the list of griceries to remove from the list seperating with ','");
				String[] items=sc.next().split(",");
				groceries1.addAll(Arrays.asList(items));
				Items.removeGroceries(groceries1);
				break;
			default:
				Items.displayList();
			}
			
		}while(option!=0);
		
		sc.close();
	}

}
