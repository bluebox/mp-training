
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
	if(!s.matches("[FMBQ]")) 
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
	}
	
	}
	catch(InvalidInputException  e) {
		System.out.println("enter the valid number");
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

class ListOperations{
	LinkedList<Location>lis=new LinkedList<Location>();
	public void listLocation(Location l){
		lis.add(l);
	}
	public void showLocation() {
		Iterator<Location> i=lis.iterator();
		while(i.hasNext()) {
			System.out.println(i.next());
		}
	}
//	public Location forwardLocation(int forward) {
//		if(lis.isEmpty()) {
//			return new 
//		}
//		
//		
//	}
//	public String BackwardLocation() {
//		
//	}
}
