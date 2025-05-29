package Linkedlist;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class PlacesMain {
	public static void display() {
		System.out.println("(F)---Forward");
		System.out.println("(B)---Backward");
		System.out.println("(L)---List");
		System.out.println("(Q)---Quit");
		
	}
	public static Place forward(LinkedList<Place> places,ListIterator<Place> iter,Place currentPlace) {
		Place prev=currentPlace;
		if(iter.hasNext()) {
			currentPlace=iter.next();
			System.out.println("Moving forward from " +prev.name+" to "+currentPlace.name);
		}
		else {
			System.out.println(currentPlace.name+" is the end ");
		}
		return currentPlace;
	}
	public static Place backward(LinkedList<Place> places,ListIterator<Place> iter,Place currentPlace) {
		Place prev=currentPlace;
		if(iter.hasPrevious()) {
			currentPlace=iter.previous();
			System.out.println("Moving backward from " +prev.name+" to "+currentPlace.name);
		}
		else {
			System.out.println(currentPlace.name+" is the start ");
		}
		return currentPlace;
	}
	
	public static void list (LinkedList<Place> places) {
		ListIterator<Place> traverse=places.listIterator();
		while(traverse.hasNext()) {
			Place place=traverse.next();
			System.out.println(place.name +"   "+ place.distance);
		}
		}

	public static void main(String[] args) {
		LinkedList<Place> places=new LinkedList<Place>();
		places.add(new Place("Sydney",0));
		places.add(new Place("Adelaide",1374));
		places.add(new Place("Alice Springs",2771));
		places.add(new Place("Brisbane",917));
		places.add(new Place("Darwin",3972));
		places.add(new Place("Melbourne",877));
		places.sort((p1,p2)->Integer.compare(p1.distance, p2.distance));
		ListIterator<Place> iter=places.listIterator();
		Scanner sc=new Scanner(System.in);
		display();
		Place currentPlace=places.get(0);
		char choice =sc.next().charAt(0);
		while(choice!='Q') {
			switch (choice) {
			case 'F': currentPlace=forward(places,iter,currentPlace);
			break;
			case 'B': currentPlace=backward(places,iter,currentPlace);
			break;
			case 'L': list(places);
			break;
			default : System.out.println("Enter valid char");
			break;	
			}
			display();
			choice=sc.next().charAt(0);
			
		}
		sc.close();
		
		
	}
}
