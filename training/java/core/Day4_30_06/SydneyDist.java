package Day4_30_06;

import java.util.LinkedList;
import java.util.Scanner;

public class SydneyDist {
	public LinkedList<Place> lis=new LinkedList<>();
	public LinkedList<Place> getLis() {
		return lis;
	}
    private static void printMenu() {
        System.out.println("""
                Available actions (select word or letter):
                (F)orward
                (B)ackwards
                (L)ist Places
                (M)enu
                (Q)uit""");
    }

	public void addPlace(Place p) {
		 if (lis.contains(p)) {
	            System.out.println("Found duplicate: " + p);
	            return;
	        }
	        for (Place pl : lis) {
	            if (pl.name.equalsIgnoreCase(p.name)) {
	                System.out.println("Found duplicate: " + p);
	                return;
	            }
	        }
	        int matchedIndex = 0;
	        for (var listPlace : lis) {
	            if (p.distance < listPlace.distance) {
	                lis.add(matchedIndex, p);
	                return;
	            }

	            matchedIndex++;
	        }

	        lis.add(p);
	}
	public static void main(String args[]) {
		SydneyDist list=new SydneyDist();
		
		  list.addPlace(new Place("Adelaide", 1374));
		  list.addPlace(new Place("Brisbane", 917));
		  list.addPlace(new Place("Alice Springs", 2771));
		  list.addPlace(new Place("Darwin", 3972));
		  list.addPlace(new Place("Melbourne", 877));
		  list.addPlace(new Place("Perth", 3923));
		  list.addPlace(new Place("Perth", 3923));

		  list.addPlace(new Place("Sydney", 0));
	        System.out.println(list);

	        var iterator = list.getLis().listIterator();
	        Scanner scanner = new Scanner(System.in);
	        boolean quitLoop = false;
	        boolean forward = true;

	        printMenu();

	        while (!quitLoop) {
	            if (!iterator.hasPrevious()) {
	                System.out.println("Originating : " + iterator.next());
	                forward = true;
	            }
	            if (!iterator.hasNext()) {
	                System.out.println("Final : " + iterator.previous());
	                forward = false;
	            }
	            System.out.print("Enter Value: ");
	            String menuItem = scanner.nextLine().toUpperCase().substring(0, 1);

	            switch (menuItem) {
	                case "F":
	                    System.out.println("User wants to go forward");
	                    if (!forward) {           // Reversing Direction
	                        forward = true;
	                        if (iterator.hasNext()) {
	                            iterator.next();  // Adjust position forward
	                        }
	                    }

	                    if (iterator.hasNext()) {
	                        System.out.println(iterator.next());
	                    }

	                    break;

	                case "B":
	                    System.out.println("User wants to go backwards");
	                    if (forward) {           // Reversing Direction
	                        forward = false;
	                        if (iterator.hasPrevious()) {
	                            iterator.previous();  // Adjust position backwards
	                        }
	                    }
	                    
	                    if (iterator.hasPrevious()) {
	                        System.out.println(iterator.previous());
	                    }
	                    break;

	                case "M":
	                    printMenu();
	                    break;

	                case "L":
	                    System.out.println(list.lis);
	                    break;

	                default:
	                    quitLoop = true;
	                    break;
	            }
	        }



	}
	
	
}
