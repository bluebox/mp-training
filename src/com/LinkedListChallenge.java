import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class LinkedListChallenge implements Comparator<Place> {
	public static void main(String[] args) {
		List<Place> iteneraryList = new LinkedList<>();
		
		Place brisbane = new Place("Brisbane", 300);
		Place adelaidePlace = new Place("Adelaide", 100);
		Place melbournePlace = new Place("Melbourne", 880);
		
		iteneraryList.add(new Place("Sydney", 0));
		iteneraryList.add(brisbane);
		iteneraryList.add(melbournePlace);
		iteneraryList.add(adelaidePlace);
		
		iteneraryList.sort(new LinkedListChallenge());
		
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		ListIterator<Place> iterator = iteneraryList.listIterator();
		
		do {
			System.out.println("""
					Available actions (select word or letter):
					(F)orward
					(B)ackward
					(L)ist Places
					(M)enu
					(Q)uit
					""");
			
			String inputString = scanner.nextLine();
			switch(inputString.toUpperCase()) {
				case "F", "FORWARD": {forwardTraversal(iteneraryList, iterator);break;}
				case "B", "BACKWARD": {backwardTraversal(iteneraryList, iterator);break;}
				case "L", "LIST PLACES": {listPlaces(iteneraryList);break;} 
				case "M", "MENU": break;
				case "Q", "QUIT": {flag=false;System.out.println("Quitting...");break;}
				default: System.out.println("list ended");
			}
		} while(flag);
		

	}
	
	private static void listPlaces(List<Place> iteneraryList) {
		System.out.println("The places in the itenerary are: ");
		for(Place p:iteneraryList) {
			System.out.println(p.name);
		}
		System.out.println();
	}

	public int compare(Place o1, Place o2) {
		return o1.distFromSydney - o2.distFromSydney;
	}
	
	public static void forwardTraversal(List<Place> iteneraryList, ListIterator<Place> iterator) {
		if(iterator.hasNext()) {
			System.out.println("The next city in the itenerary is: " + iterator.next());
		}
		else {
			System.out.println("Sorry! This is the last city!");
		}
	}

	public static void backwardTraversal(List<Place> iteneraryList, ListIterator<Place> iterator) {
		if(iterator.hasPrevious()) {
			System.out.println("The previous city in the itenerary is: " + iterator.previous());
		}
		else {
			System.out.println("Sorry! This is the first city!");
		}
	}
}
