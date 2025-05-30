package linkedlist;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import arraylist.contact;

public class LinkedListChallenge {
	private LinkedList<Destination> cities;
	public LinkedListChallenge() {
		this.cities = new LinkedList<Destination>();
	}
	private int findCity(Destination city) {
		for(int i=0;i<cities.size();i++) {
			if(cities.get(i).getTownName()== city.getTownName())
				return i;
		}
		return -1;
	}
	public void addTown(Destination city) {

		if(findCity(city) == -1) {
			cities.add(city);
			System.out.println(city.getTownName()+ " added");
		}
		else {
			System.out.println(city.getTownName()+" Already exists ");
		}
		
	}
	public void moveForward(){
		ListIterator<Destination> iterator = cities.listIterator(cities.size()); 
		if(iterator.hasNext()) {
			System.out.println(iterator.next().getTownName());
	}
		else
			System.out.println("Last city");
	}
	public void moveBackward(){
		ListIterator<Destination> iterator = cities.listIterator(cities.size()); 
		if(iterator.hasPrevious())
			System.out.println(iterator.previous().getTownName());
		else
			System.out.println("First city");
	}
	public static void main(String [] args) {
		LinkedListChallenge cities = new LinkedListChallenge();
		Destination t1 = new Destination("Adelaide",1374);
		cities.addTown(t1);
		cities.addTown(new Destination("Adelaide",2771));
		cities.addTown(new Destination("Alice Springs",2771));
		cities.addTown(new Destination("Brisbane",917));
		cities.addTown(new Destination("Darwin",3972));
		cities.addTown(new Destination("Melbourne",877));
		cities.addTown(new Destination("perth",3293));
		Scanner sc =new Scanner(System.in);
		while(true) {
			System.out.println("Available Actions:");
			System.out.println("(F)orward");
			System.out.println("(B)ackward");
			System.out.println("(Q)uit");
			((List<Destination>) cities).getFirst();
			String option = sc.nextLine();
			if(option.charAt(0)=='F') {
				cities.moveForward();
			}
			if(option.charAt(0)=='B') {
				cities.moveBackward();
			}
			if(option.charAt(0)=='Q') {
				break;
			}
		}
		System.out.println("Tour Ended");
	}

}
