import java.util.Set;
import java.util.TreeSet;

public class TreeSetChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String seatnumber="A005";
        Theatre t1= new Theatre("Prasads",10, 5);       
        System.out.println(t1.bookSeat("A005"));  
        System.out.println(t1.bookSeat("A010"));
        System.out.println(t1.bookSeat("B008"));
        System.out.println(t1.bookSeat("C007"));
        System.out.println(t1.bookSeat("D004"));
        System.out.println(t1.bookSeat("E007"));
        System.out.println(t1.bookSeat("B004"));
        System.out.println(t1.bookSeat("A010"));
        t1.printSeatMap();
	}

}
class Theatre
{
	Set<Seat> seats;
	String name;
	int nrows;
	int nseats;
	
	public Theatre(String name,int tseats,int trows)
	{
		seats=new TreeSet<>();
		this.name=name;
		this.nrows=trows;
		this.nseats=tseats;
		char ch='A';
		for(int i=1;i<=trows;i++,ch++){
            for(int j=1;j<=tseats;j++){
                Seat seat= new Seat(ch, j);
                seats.add(seat);
            }
        }
		
	}
	public void printSeatMap(){
        char ch='A';
            for(int rows=1;rows<=nrows;rows++,ch++){
                for(int i=1;i<=nseats;i++){
                    String s=ch+"";
                    if(i<10){
                        s=s+"00"+i;
                    }else{
                        s=s+"0"+i;
                    }
                    System.out.print(s+" ");
//                    if(seats.get(i)seat.isReserved())
//    	                System.out.print(seat.getSnumber()+" ");
                }
                System.out.println();
            }
	}
	 public void printedSeatMap(){
	        for(Seat seat:seats){
	            if(seat.isReserved()){
	                System.out.print(seat.getSnumber()+" ");
	            }
	        }
	     }
	     public String bookSeat(String snumber){        
	        for(Seat seat:seats){
	            if(seat.getSnumber().equals(snumber)){
	                if(seat.isReserved()){
	                    return "The seat you are requested for Is not available";
	                }else{
	                    seat.setReserved(true);
	                    return "BOOKED Seat number : "+snumber;
	                }
	            }                        
	        }
	        return "Please enter correct seat number";
	     }
	
	
	
	class Seat {
		String seat;
		
		boolean reserved;
		Set<Seat> seats;
		public Seat(char row,int seatNum)
		{
			int n=(seatNum+"").length();
            seat=row+"";
            seat+=(n==2)?("0"+n):"00"+n;
		}
		public void setSnumber(String snumber) {
            this.seat = snumber;
        }

        public void setReserved(boolean isReserved) {
            this.reserved = isReserved;
        }

        public String getSnumber() {
            return seat;
        }

        public boolean isReserved() {
            return reserved;
        }
        public int compareTo(Seat other) {
            int res=(this.seat.charAt(0)+"").compareTo(other.seat.charAt(0)+"");
            if(res==0){
                return Integer.compare(Integer.parseInt(this.seat.substring(1)), Integer.parseInt(other.seat.substring(1)));
            }
            return res;
        }
		
	}
}

