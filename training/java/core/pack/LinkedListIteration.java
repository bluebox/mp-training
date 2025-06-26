
import java.util.*;


public class LinkedListIteration  {
	
public static void main(String args[]) throws InvalidInputException {
	ListOperations lo=new ListOperations();
	lo.listLocation(new Location("Sydney",0));
	
	Scanner sc=new Scanner(System.in);
	String s="",name;
	int distance;
	
	int forward=0,backward=0;
	while(true) {
		
	System.out.println("Enter \n (F) to list the location \n (M) to see all locations \n (N) for next location \n (B) for previous location \n (Q) to quit");
	try {
	s=sc.next().trim().toUpperCase();
	if(!s.matches("[FMBQN]")) 
		throw new InvalidInputException("Enter input from given data only");
	
	switch(s) {
	case "F"->{
		System.out.println("Enter the location");
		name=sc.next();
		System.out.println("Enter the distance");
		distance=sc.nextInt();
		lo.listLocation(new Location(name,distance));
	}
	case "M"->{
		lo.showLocation();
	}
	case "N" ->{
		System.out.println(lo.forwardLocation());
	}
	case "B" ->{
		System.out.println(lo.backwardLocation());
	}
	case "Q"->{
		System.out.println("exiting");
		return;
	}
	}
	
	}
	catch(InvalidInputException  e) {
		System.out.println("enter the valid number "+e);
	}
	
	}
	
	
	

}
}
class InvalidInputException extends Exception{
	public InvalidInputException(String message){
		super(message);
	}
}

class Location implements Comparable<Location>{
	String name;
	int distance ;
	Location(String name,int distance) {
		this.name=name;
		this.distance=distance;
	}
	public int compareTo(Location other){
		// Integer.compare(this.distance,other.distance);
	  	return this.distance-other.distance;
	}
	
	@Override
	public String toString() {
	    return name + " (" + distance + " km)";
	}

}

 class ListOperations {
    LinkedList<Location> lis = new LinkedList<>();
    private int currentIndex = 0;

    public void listLocation(Location l) {
        lis.add(l); // directly modify the list
    }

    public void showLocation() {
        for (Location loc : lis) {
            System.out.println(loc);
        }
    }

    public Location forwardLocation() {
        if (currentIndex < lis.size()) {
            return lis.get(currentIndex++);
        }
        return null;
    }

    public Location backwardLocation() {
        if (currentIndex > 0) {
            return lis.get(--currentIndex);
        }
        return null;
    }
}

