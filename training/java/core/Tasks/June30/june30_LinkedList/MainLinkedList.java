package corejava.june30_LinkedList;

import java.util.ListIterator;
import java.util.Scanner;

public class MainLinkedList {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		WishtList myWishList=new WishtList();
		String option;
		do {
			System.out.println("Available actions (Select word to letter)");
			System.out.println("(A)dd to List");
			System.out.println("(F)orward");
			System.out.println("(B)ackward");
			System.out.println("(L)ist Places");
			System.out.println("(M)enu");
			System.out.println("(Q)uit");
			System.out.println("(R)emove from List");
			option =sc.next().trim().toUpperCase();
			switch(option) {
			case "A":
				System.out.println("Enter a place to visit");
				sc.nextLine();
				String name=sc.nextLine();
				System.out.println("Enter distance of the place from sydney");
				int distance=sc.nextInt();
				PlacesToVisit p=new PlacesToVisit(name,distance);
				myWishList.addPlace(p);
				break;
			case "F":if (myWishList != null && !myWishList.isEmpty()) {
				myWishList.moveForward();
				}
				else {
					System.out.println("Your WishList is Empty");
				}
				break;
			case "B":
				if (myWishList != null && !myWishList.isEmpty()) {
					myWishList.moveBackward();
				}
				else {
					System.out.println("Your WishList is Empty");
				}
				break;
			case "L":myWishList.listPlaces();
				break;
			case "M":System.out.println("");
				break;
			case "Q":System.out.println("Thank You!");
				break;
			case "R":
				System.out.println("Enter a place to remove from list");
				sc.nextLine();
				String removeName=sc.nextLine();
				myWishList.removePlace(removeName);
				break;
			default: System.out.println("You entered an invalid Syntax. Please enter a valid syntax.");
			
			}
		}while(!option.equals("Q"));
		sc.close();
	}

}
