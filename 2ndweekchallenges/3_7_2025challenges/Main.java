//import Theater.Seat;

public class Main {

     public static void main(String[] args) {
          String userrowname="B";
          int userseatno=9;
        Theater theater=new Theater("asian", 10);
        theater.assign_Seat();
        for(Theater.Seat s:theater.seats){
          if(s.getRowname().equals(userrowname) && s.getSeatno()==userseatno){
          if(s.getRowname().charAt(0)!='*'){
               String newrowname=s.getRowname();
               s.setRowname("*"+newrowname);
               break;
          }
          else{
               System.out.println("Seat is alredy booked");
               break;
          }}
        }
        theater.seats.forEach((s)->System.out.println(s.getRowname()+s.getSeatno()));
     }
}