package training.java.core.DAY2;

import java.util.LinkedList;

public class LinkedListChallenge {
    public static LinkedList<Place> ll=new LinkedList<>(); 
    public static void main(String[] args) {
        
        Place p1= new Place("kamareddy",123);
        ll.add(p1);

        Place p2= new Place("hyd",145);
        Place p3= new Place("goa",563);
        Place p4= new Place("ooty",233);
        Place p5= new Place("ladakh",654);
        Place p6= new Place("manali",543);
        addPlacesInOrder(p2);
        addPlacesInOrder(p3);
        addPlacesInOrder(p4);
        addPlacesInOrder(p5);
        addPlacesInOrder(p6);
        printPlaces(ll);
    }
    public static void addPlacesInOrder(Place p){
        int idx=0;
        for(Place place:ll){
            if(place.getDist()>p.getDist()){
                ll.add(idx,p);
                return;
            }
            idx++;
        }
        ll.add(idx,p);
    }
    public static void printPlaces(LinkedList<Place> ll){
        for(Place p:ll){
            System.out.println(p);
        }
    }
}
class Place{
    private String town;
    private int dist;
    public Place(String town, int dist) {
        this.town = town;
        this.dist = dist;
    }
    public String getTown() {
        return town;
    }
    public int getDist() {
        return dist;
    }
    @Override
    public String toString() {
        return "Place [town=" + town + ", dist=" + dist + "]";
    }
    
}

