package training.java.core.enums;

public class Days {
    public static void main(String[] args) {
        Day mon=Day.SUNDAY;
    System.out.println("today is "+mon);
    // for(Friend friend:Friend.values()){
    //     System.out.println("my name is "+ friend);
    // }
    // Friend f1=Friend.SRISAI;
    // System.out.println(f1);
    // System.out.println(f1.getVlg());
    Friend bestfriend= Friend.NITIN;
    Friend closeFriend= Friend.CHIKKU;
    System.out.println(closeFriend);
    System.out.println(closeFriend.getVlg());

    }
}
enum Day{
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY
    // studentName("srisai",451);
}

enum Friend{
    NITIN,
    CHIKKU,

    SRISAI("Kmr",451),
    RAHUL("ngl",335),
    BADRI("nkl",407),
    GANESH("kmm",535),
    ASHOK("mgr",405);
    int id;
    String vlg;
   private Friend(String vlg,int id){
        this.id=id;
        this.vlg=vlg;
    }
    private Friend(){
        id=888;
        vlg="nagar";
    }
    public int getId(){
        return id;
    }
    public String getVlg(){
        return vlg;
    }
}