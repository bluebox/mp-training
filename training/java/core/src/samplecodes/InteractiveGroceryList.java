package samplecodes;
import java.util.*;
public class InteractiveGroceryList {
	public static void main(String[] args) {
		ArrayList<String> groceryList=new ArrayList<>();
		addItem("Soaps",groceryList);
		addItem("Detergents",groceryList);
		addItem("Soaps",groceryList);
		addItem("Biscuits",groceryList);
		addItem("Shampoo",groceryList);
		addItem("Toothpaste",groceryList);
		
		removeItem("Toothpaste",groceryList);
		
	}
	public static void addItem(String item, ArrayList<String> groceryList) {
		if(!checkExists(item,groceryList)) {
			groceryList.add(item);
			Collections.sort(groceryList);
			System.out.println(groceryList);
		}
	}
	public static void removeItem(String item, ArrayList<String> groceryList) {
		groceryList.remove(groceryList.indexOf(item));
		Collections.sort(groceryList);
		System.out.println(groceryList);
	}
	public static boolean checkExists(String item,ArrayList<String> groceryList) {
		return groceryList.contains(item);
	}
}
