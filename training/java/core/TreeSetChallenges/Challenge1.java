package training.java.core.TreeSetChallenges;
import java.util.*;
import java.util.TreeSet;

public class Challenge1 {
    public static void main(String[] args) {
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
        // t1.printedSeatMap();
        t1.printSeatMap();
    }
}
class Theatre{
    class Seat implements Comparable<Seat>{
       private String snumber;
        private boolean Reserved;        
        public Seat(char row,int number){
            int n=(number+"").length();
            snumber=row+"";
            snumber+=(n==2)?("0"+number):"00"+number;
        }
        
        public void setSnumber(String snumber) {
            this.snumber = snumber;
        }

        public void setReserved(boolean isReserved) {
            this.Reserved = isReserved;
        }

        public String getSnumber() {
            return snumber;
        }

        public boolean isReserved() {
            return Reserved;
        }

        @Override
        public int compareTo(Seat other) {
            int res=(this.snumber.charAt(0)+"").compareTo(other.snumber.charAt(0)+"");
            if(res==0){
                return Integer.compare(Integer.parseInt(this.snumber.substring(1)), Integer.parseInt(other.snumber.substring(1)));
            }
            return res;
        }
        
     }

    String tname;
    int tseats;
    int trows;
    Set<Seat> seats;
    public Theatre(String tname,int tseats,int trows){
        seats= new TreeSet<>();
        this.tname=tname;
        this.tseats=tseats;
        this.trows=trows;
        int i=1;
        for(char ch='A';i<=trows;i++,ch++){
            for(int j=1;j<=tseats;j++){
                Seat seat= new Seat(ch, j);
                seats.add(seat);
            }
        }
    }
    public void printSeatMap(){
        char ch='A';
            for(int rows=1;rows<=trows;rows++,ch++){
                for(int i=1;i<=tseats;i++){
                    String s=ch+"";
                    if(i<10){
                        s=s+"00"+i;
                    }else{
                        s=s+"0"+i;
                    }
                    System.out.print(s+" ");
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
     
}

