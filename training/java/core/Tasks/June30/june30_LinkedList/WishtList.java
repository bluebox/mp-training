package corejava.june30_LinkedList;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class WishtList {
	private List<PlacesToVisit> myList;
	private ListIterator<PlacesToVisit> it;
	
	public WishtList() {
		myList=new LinkedList<>();
	}
	
	public void addPlace(PlacesToVisit p) {
		 if (containsPlace(p.getPlace())) {
		        return; 
		    }

		 for (int i = 0; i < myList.size(); i++) {
			 if (p.getDistanceInKM() < myList.get(i).getDistanceInKM()) { 
				 myList.add(i, p);
		         return;
		     }
		}
		myList.add(p);
		if(myList.size()==1) {
			this.it=myList.listIterator();
		}
	}
	
	public boolean containsPlace(String placeName) {
	    for (PlacesToVisit item : myList) {
	        if (item.getPlace().equals(placeName)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public void removePlace(String name) {
		
		for (PlacesToVisit place : myList) {
	        if (place.getPlace().equals(name)) {
	            myList.remove(place);
	        }
	    }
	}
	
	public void listPlaces() {
		for (PlacesToVisit place : myList) {
	        System.out.println("Place: "+place.getPlace()+", distance: "+place.getDistanceInKM());
	    }
	}
	
	public void moveForward() {
		if(it.hasNext()) {
			PlacesToVisit current=it.next();
			System.out.println("Place: " + current.getPlace() + ", Distance: " + current.getDistanceInKM());
		}
	}
	
	public void moveBackward() {
		ListIterator<PlacesToVisit> it=myList.listIterator();
		if(it.hasPrevious()) {
			PlacesToVisit current=it.previous();
			System.out.println("Place: " + current.getPlace() + ", Distance: " + current.getDistanceInKM());
		}
	}
	public boolean isEmpty() {
	    return myList == null || myList.isEmpty();
	}
}
