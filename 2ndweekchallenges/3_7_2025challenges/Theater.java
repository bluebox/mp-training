
import java.util.Set;
import java.util.TreeSet;

public class Theater {
    Set<Seat> seats=new TreeSet<>();
    String theatre_name;
    int rowseat;
    public Theater(String t_name,int rowseat){
        this.theatre_name=t_name;
        this.rowseat=rowseat;

        
    }
    
    public void assign_Seat( ){
        for(char c='A';c<='Z';c++){
            for(int i=1;i<this.rowseat;i++){
                seats.add(new Seat(String.valueOf(c),i));
            }

        }
    }


    class Seat implements Comparable<Seat>{
        private String rowname;
        private int seatno;
        public Seat(String rowname,int seatno){
            this.rowname=rowname;
            this.seatno=seatno;

        }
        public String getRowname() {
            return rowname;
        }
        public int getSeatno() {
            return seatno;
        }
        public void setRowname(String rowname) {
            this.rowname = rowname;
        }
        public void setSeatno(int seatno) {
            this.seatno = seatno;
        }
         @Override
    public String toString() {
        String a=(this.seatno<10)?"00":"0"+this.seatno;
        
        return this.rowname+a;
    }
         @Override
         public int compareTo(Seat o) {
            if((int)this.rowname.charAt(0)<(int)o.rowname.charAt(0)){
                return -1;
            }
            else if((int)this.rowname.charAt(0)>(int)o.rowname.charAt(0)){
                return 1;
            }
            else{
                if(this.seatno<o.seatno){
                    return -1;
                }
                else if(this.seatno>o.seatno){
                    return 1;
                }
                else{
                    return 0;
                }
            }
            
            
         }


    }
    
}
