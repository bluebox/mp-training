package com.tulasidhar.june30.LinkedListChallenge;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class LinkedListChallenge {
	public static void main(String[] args) {
		LinkedList<Place> LinkList = new LinkedList<Place>();
		
		LinkList.add(new Place("Amaravati",40));
		LinkList.add(new Place("Hyderabad",280));
		LinkList.add(new Place("Karnataka",800));
		LinkList.add(new Place("Guntur",80));
		
		LinkList.addFirst(new Place("Vijayawada",0));
		
		ListIterator<Place> ite = LinkList.listIterator();
		
		Scanner sc = new Scanner(System.in);
		char lastMove = ' ';
		while(true) {
			
			boolean exit = false;
			
			System.out.println("Available Actions : \n"
					+ "(F)orward\n"
					+ "(B)ackward\n"
					+ "(L)ist Places\n"
					+ "(M)enu\n"
					+ "(Q)uit\n");
			String input = sc.next();
			
			switch(input) {
				case "F":
					if(lastMove == 'b') {
						ite.next();
					}
					
					if(ite.hasNext()) {
						System.out.println(ite.next());
					}
					
					lastMove = 'f';
					break;
				case "B":
					
					if(lastMove == 'f') {
						//System.out.println("trying to go back ");
						ite.previous();
					}
					if(ite.hasPrevious()) {
						System.out.println(ite.previous());
					}
					lastMove = 'b';
					break;
				case "L":
					System.out.println(LinkList);
					break;
				case "M":
					break;
				case "Q":
					exit = true;
					break;
				default:
					break;
			}
		}
	}
}


class Place{
	String place;
	//distance from Vijayawada
	int distance;
	
	public Place(String place,int distance) {
		this.place = place;
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Place [place=" + place + ", distance=" + distance + "]";
	}
	
	
}