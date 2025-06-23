package learn2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GroceryList {
	
	public static void main(String[] args) 
	{
		ArrayList<String> list = new ArrayList<>();
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("0:  quit");
		System.out.println("1:  add items ");
		System.out.println("2:  remove items ");
		boolean flag= true;
		do{
			int ch = scanner.nextInt();
			scanner.nextLine();
			switch(ch) {
				case 0 -> {
						System.out.println("The system is shutting down");
						flag=false;
					}
				case 1 -> {
						System.out.println(list);
						System.out.println("list the items to be added (, )");
						String newItems = scanner.nextLine();
						addToList(list,newItems.split(", "));
						System.out.println(" ");
					}
				case 2 ->{
					System.out.println(list);
					System.out.println("list the items to be removed (, )");
					String itemsToBeRemoved = scanner.nextLine();
					removeFromList(list ,itemsToBeRemoved.split(", "));
				}
				default -> System.out.println("invalid! enter again");
			}
		}while(flag);
		System.out.println(list);
	}

	private static void removeFromList(ArrayList<String> list, String[] itemsList) {
		for(String item : itemsList) {
			if(list.contains(item)) {
				list.remove(item);
			}
			else {
				System.out.println(item+" is not found to remove ");
			}
		}
		System.out.println("the updated list is " + list);
	}

	private static void addToList(ArrayList<String> list, String[] itemsList) {
		
		for(String item : itemsList) {
			if(list.contains(item)) {
				System.out.println("already added");
			}
			else {
				list.add(item);
			}
		}
		Collections.sort(list);
		System.out.println("the updated list is " + list);
	}
}
