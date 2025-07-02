package ArraysAndCollections;
import java.util.*;

class Place{
	private String name;
	private int distance;
	public Place() {
		
	}
	public Place(int distance,String name) {
		this.distance=distance;
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
}


public class LinkedList_1 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		LinkedList<Place> cityList=new LinkedList<>();
		cityList.add(new Place(1374,"Adelaide"));
		cityList.add(new Place(2771,"Alice Springs"));
		cityList.add(new Place(917,"Brisbane"));
		cityList.add(new Place(3972,"Darwin"));
		cityList.add(new Place(877,"Melbourne"));
		cityList.add(new Place(3923,"Perth"));
		cityList.add(new Place(0,"sydney"));
		while(true) {
		   System.out.println("Enter the option as mentioned in the menu");
		   System.out.println("Eneter 0 for the city place \n Enter the F for (F)orward \n Enter the B for"
		   		+ " (B)ackword\n Enter the L for the (L)ist places \n Enter the M for (M)enu \n Enter the Q for (Q)uit");
		   String input=sc.nextLine();
		   boolean quit=false;
		   switch(input) {
		   case "0":
			   String Place=sc.nextLine();
			   int distance =Integer.parseInt(sc.nextLine());
			   cityList.add(new Place(distance,Place));
			   break;
		   case "F":
			   ListIterator<Place> iterator=cityList.listIterator();
			   Collections.sort(cityList,(a,b)->(b.getDistance()-a.getDistance()));
			   while(iterator.hasNext()) {
				 System.out.println(iterator.next().getName()+" is " +iterator.next().getDistance() +" distance from sidney.");  
			   }
			   break;
		   case "B":
			   ListIterator<Place> iterator_1=cityList.listIterator();
			   Collections.sort(cityList,(a,b)->(b.getDistance()-a.getDistance()));
			   while(iterator_1.hasPrevious()) {
				 System.out.println(iterator_1.previous().getName()+" is " +iterator_1.previous().getDistance() +" distance from sidney.");  
			   }
			   break;
			   
		   case "M":
			   break;
		   case "Q":	   
			   System.out.println("The System is Exiting");
			   quit=true;
			   break;
		   case "L":
			   ListIterator<Place> iterator_2=cityList.listIterator();
			   Collections.sort(cityList,(a,b)->(b.getDistance()-a.getDistance()));
			   while(iterator_2.hasNext()) {
				 System.out.println(iterator_2.next().getName()+" is " +iterator_2.next().getDistance() +" distance from sidney.");  
			   }
			   break;
		   default:
			   System.out.println("Eneter the valid input result");
			   break;
		   }
		   if(quit) {
			   break;
		   }
		}
           
		
	}

}
