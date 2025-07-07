import java.util.*;
public class Theatre {
    private String name;
    private int seatsPerRow;
    private TreeSet<Seat> seats;
    public Theatre(String name,int numRows,int seatsPerRow){
       this.name=name;
        this.seatsPerRow=seatsPerRow;
        this.seats=new TreeSet<>();
        for (char row='A';row<'A'+numRows;row++){
            for(int seatNum=1;seatNum<=seatsPerRow;seatNum++){
                String seatId=String.format("%c%03d",row,seatNum);
                seats.add(new Seat(row,seatNum,seatId));
            }
        }
    }

    class Seat implements Comparable<Seat>{
        private char row;
        private int number;
        private String seatId;
        private boolean reserved;
        public Seat(char row, int number, String seatId){
            this.row = row;
            this.number = number;
            this.seatId = seatId;
            this.reserved = false;
        }
        public boolean reserve(){
            if (!reserved){
                reserved = true;
                return true;
            }
            return false;
        }
        public boolean isReserved(){
            return reserved;
        }
        public String toString(){
            return reserved ? "["+seatId+"*]":"["+seatId+"]";
        }        
        public int compareTo(Seat other){
            return this.seatId.compareTo(other.seatId);
        }

    }
    public void printSeatMap()
    {
    	int count=0;
    	for(Seat seat:seats){
    		if(count%seatsPerRow==0){
    			System.out.println();
    		}
    		System.out.print(seat+" ");
    		count++;
    	}
    	System.out.println();
    }
    public boolean reserveSeat(String seatId)
    {
        for (Seat seat:seats) {
            if (seat.seatId.equals(seatId)){
                return seat.reserve();
            }
        }
        System.out.println("Seat not found");
        return false;
    }
    public static void main(String[] args)
    {
        Theatre theatre=new Theatre("Theatre", 3, 5); 
        theatre.printSeatMap();
        System.out.println("Reserving B004");
        if(theatre.reserveSeat("B004")){
            System.out.println("Seat B004 reserved successfully");
        } 
        else
        {
            System.out.println("Seat B004 already reserved.");
        }
        theatre.printSeatMap();
        System.out.println("Trying to reserve B004 again");
        if (!theatre.reserveSeat("B004")){
            System.out.println("Seat B004 is already reserved.");
        }
    }
}