package Collections;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GroceryListUsingArrayList {
	
	
	public static void main(String[] args) {
		
	
		Scanner sc = new Scanner(System.in);
		int i =0;
		List<String> item = new ArrayList<>();
		String str ;
		
		do {
			System.out.print("\nEnter 0 to Exit \nEnter 1 to add items in the list\nEnter 2 to remove items \n\nEnter your input : ");
			i = sc.nextInt();
			switch(i) {
			case 1:{
				System.out.print("\nEnter you list : ");
				str = sc.next();
				for (String j : str.split(",")) {
					j=j.toLowerCase();
					if (item.contains(j)) {
						continue;
					}else item.add(j);
				}
				System.out.println("\nYour items list is : ");
				for (String k : item) {
					System.out.println(k);
				}
				break;
			}
			case 2:{
				System.out.print("\nEnter you list : ");
				str = sc.next();
				for (String j : str.split(",")) {
					j=j.toLowerCase();
					if (item.contains(j)) {
						item.remove(j);
					}
				}
				System.out.println("\nYour items list is : ");
				for (String k : item) {
					System.out.println(k);
				}
				break;
			}
			default : {
				break;
			}
			}
			}while(i!=0);
		
		System.out.println("\nYour final items in the list is : ");
		for (String k : item) {
			System.out.println(k);
		}
	}

}
