package day4;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class LinkedListChallenge {
	public static void main(String[] args) {
		LinkedList<Places> places=new LinkedList<>();
		Scanner sc=new Scanner(System.in);
		addPlaces(places,"Sydney",0);
		addPlaces(places,"Adelaide",1374);
		addPlaces(places,"Alice Springs",2771);
		addPlaces(places,"Brisbane",917);
		addPlaces(places,"Darwin",3972);
		addPlaces(places,"Melbourne",877);
		addPlaces(places,"Perth",3923);
		places.sort((p1,p2)-> Integer.compare(p1.getDistance(), p2.getDistance()));
		
		//System.out.println(places.toString());
		boolean flag=true;
		char move=' ';
		ListIterator<Places> itr= places.listIterator();
		while(flag) {
			System.out.println("""
					Available actions ( Select word or letter ):
					(F)orward
					(B)ackward
					(L)ist Places
					(Q)uit 
					""");
			String str=sc.nextLine().toUpperCase().substring(0,1);
			switch(str) {
			case "F":
				if(move=='B') {
					itr.next();
				}
				if(itr.hasNext()) {
					System.out.println("moving to : "+itr.next());
				}
				else if(!itr.hasPrevious()) {
					System.out.println("starting from sydney ");
				}
				else {
					System.out.println("you reached the end ");
				}
				move='F';
				break;
			case "B":
				if(move=='F') {
					itr.previous();
				}
				if(itr.hasPrevious()) {
					System.out.println("moving back from : "+itr.previous());
				}
				else {
					System.out.println("you are at the sydney ");
				}
				move='B';
				break;
			case "Q":
				flag=false;
				break;
			case "L":
				System.out.println(places);
				break;
			default:
				System.out.println("Invalid input");
				
			
			}
		}
	}
	
	public static void addPlaces(LinkedList<Places> places,String place, int dis) {
		if(places.contains(place)) {
			System.out.println(place+" is already present in the list ");
			return;
		}
		places.add(new Places(place,dis));
	}
	
}

