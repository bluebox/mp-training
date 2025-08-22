import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

class Place {
    String town;
    int distance;

    Place(String town, int distance) {
        this.town = town;
        this.distance = distance;
    }

    void print() {
        System.out.println(town + " (" + distance + " km)");
    }
}

public class LinkedListChallenge {
    public static void main(String[] args) {
        LinkedList<Place> list = new LinkedList<>();
	    Scanner sc = new Scanner(System.in);
	    boolean running = true;
	
	    while (running) {
	    	System.out.println("======= MENU =======");
	        System.out.println("1 .Add Place");
	        System.out.println("2. Show all places");
	        System.out.println("3. Forward Traversal");
	        System.out.println("4. Backward Traversal");
	        System.out.println("5. Quit");
	
	        System.out.print("Choose option: ");
	        int choice = sc.nextInt();
	
	        switch (choice) {
	            case 1 -> {
	            	System.out.println("Enter the Place :");
	            	String place=sc.next();
	            	System.out.println("Enter the Distance :");
	            	int dis = sc.nextInt();
	            	addPlace(list, new Place(place,dis));
	                
	            }
	
	            case 2 -> {
	            	System.out.println("== All Places  ==");
	                ListIterator<Place> forward = list.listIterator();
	                while (forward.hasNext()) {
	                    forward.next().print();
	                }
	                
	            }
	
	            case 3 -> {
	            	System.out.println("== Forward Iteration ==");
	                ListIterator<Place> forward = list.listIterator();
	                while (forward.hasNext()) {
	                    forward.next().print();
	                }
	
	            }
	
	            case 4 -> {
	            	System.out.println("== Backward Iteration ==");
		            ListIterator<Place> backward = list.listIterator(list.size());
		            while (backward.hasPrevious()) {
		                backward.previous().print();
		            }
	                
	            }
	
	            case 5 -> {
	            	System.out.println("Bye ");
	            	running =false;
	            }
	
	
	            default -> System.out.println("Invalid option.");
	        }
	    }
    }
    
    
    public static void addPlace(LinkedList<Place> list, Place place) {
        for (Place p : list) {
            if (p.town.equalsIgnoreCase(place.town)) {
                System.out.println("Duplicate place: " + place.town);
                return;
            }
        }
        list.add(place);
    }
	            
}