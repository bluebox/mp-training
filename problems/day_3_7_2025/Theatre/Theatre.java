package day_3_7_2025.Theatre;

import java.util.Set;
import java.util.TreeSet;

public class Theatre {
     public Set<Seat> seats=new TreeSet<>();
     public String name;
      public int noOfSeatsinrow;
      
      public Theatre(String name,int rowseats,Set<Seat> seats) {
    	  this.name=name;
    	  this.noOfSeatsinrow=rowseats;
    	  this.seats=seats;
      }
      
      
      public void printSeats() {
    	  seats.forEach(Seat::toString);
      }
      
      public void reservationseat(char row,int num) {
    	  Seat seat=new Seat(row,num,false);
    	  seat.reserve();
    	  if(seats.contains(seat)) {
    		  System.out.println("this seat is already reserved");
    	  }else {
    		  System.out.println("This seat is booked successfully");
    	  }
      }
      
      public boolean contains(Seat seat) {
    	  boolean check=false;
    	  for(Seat set:seats) {
    	  if(seat.hashCode()==set.hashCode()) {
    		  check=true;
    	  }
    	  }
    	  return check;
      }
      
      public void addseat(char row,int num,boolean val) {
    	  seats.add(new Seat(row,num,val));
      }
      
	public
       static int xdhn() {
    	  return 1;
      }
      
	public class Seat{
		public Character row;
		public Integer number;
	    public boolean reserved;
		
	    public Seat() {
	    	
	    }
	    public Seat(Character row,Integer num,boolean reserverd) {
	    	this.row=row;
	    	this.number=num;
	    	this.reserved=reserverd;
	    }
	    
	    public void reserve() {
	    	this.reserved=true;
	    }
	    
		@Override
		public String toString() {
			return (reserved != true)?( ""+row+""+((number<10)?"00"+number:"0"+number)):("*"+row+""+((number<10)?"00"+number:"0"+number));
		}
		
		
		
	}




	
	
	
}
