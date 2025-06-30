package linkedListChallenge;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		LinkedList<Place> places = new LinkedList<>();
		Scanner sc = new Scanner(System.in);
		
		addPlace(places,new Place("Adelaide",1374));
		addPlace(places,new Place("Alice Springs",2771));
		addPlace(places,new Place("Brisbane",917));
		addPlace(places,new Place("Darwin",3972));
		addPlace(places,new Place("Melbourne",877));
		addPlace(places,new Place("Perth",3923));
		System.out.println(places);
		
		ListIterator<Place> iterator = places.listIterator();
		boolean exit = false;
		boolean forword = true;
		
		menu();
		
		while(!exit) {
			
			if(!iterator.hasPrevious()) {
				System.out.println("Start : "+ iterator.next());
				forword = true;
			}
			if(!iterator.hasNext()) {
				System.out.println("End : "+ iterator.previous());
				forword = false;
			}
			
			System.out.println("Enter Option:");
			char option = sc.nextLine().toUpperCase().charAt(0);
 
			switch(option) {
			case 'F':
				System.out.println("fordWards :");
				if(!forword) {
					forword = true;
					if(iterator.hasNext()) {
						iterator.next();
					}
				}
				if(iterator.hasNext()) {
					System.out.println(iterator.next());
				}
				break;
			case 'B':
				System.out.println("BackWards :");
				if(forword) {
					forword = false;
					if(iterator.hasPrevious()) {
						iterator.previous();
					}
				}
				if(iterator.hasPrevious()) {
					System.out.println(iterator.previous());
				}
				break;
			case 'L':
				System.out.println(places);
				break;
			case 'M':
				menu();
				break;
			default:
				exit=true;
				break;
			}
		}
		
		sc.close();
		
	}
	
	public static void addPlace(LinkedList<Place> list, Place place) {
		if(list.contains(place)) {
			System.out.println("Already place Exits "+ place);
			return;
		}
		
		for(Place p: list) {
			if(place.getName().equalsIgnoreCase(p.getName())) {
				System.out.println("Already place Exits "+ place);
				return;
			}
		}
		int index =0;
		for(Place p: list) {
			if(place.getDistance() < p.getDistance()) {
				list.add(index,place);
				return;
			}
			
			index +=1;
		}
		
		list.add(place);
	}
	
	public static void menu() {
		System.out.println("Available actions (seclect word or letter");
		System.out.println("(F)orwards");
		System.out.println("(B)ackwards");
		System.out.println("(L)ist Places");
		System.out.println("(M)enu");
		System.out.println("(Q)uit");
	}
}
