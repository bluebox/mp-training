
import java.util.*;
public class Arraylinkedlist {


        public static void main(String[] args) {
            Scanner sc=new Scanner(System.in);
            LinkedList<Places>ll=new LinkedList<Places>();
            while (true) {
                System.out.println("Enter the option \n 1-Enter the place and distance \n 2-Forward option \n 3-backward option \n 4-exit");
                int option=sc.nextInt();
                if(option==1){
                System.out.println("Enter the place and distance");
                String place=sc.next();
                int distance=sc.nextInt();
                Places p=new Places(place,distance);
                ll.add(p);
                Collections.sort(ll,(a,b)->b.getDistance()-a.getDistance());
                
                }
                else if(option==2){
                    ListIterator<Places> lit = ll.listIterator(ll.size());
                    while (lit.hasNext()) {
                        Places p=lit.next();
            System.out.println(p.getName());
            System.out.println(p.getDistance());
        }


                }
                else if(option==3){
                    ListIterator<Places> lit = ll.listIterator();
                    while(lit.hasPrevious()){
                        Places p=lit.previous();
                        System.out.println(p.getName());
                        System.out.println(p.getDistance());
                    }
                
                }
                else{
                    break;
                }
                
                

                
                
            }
            
        }




}
class Places{
    private String name;
    private int distance;
    public Places(String name,int distance){
        this.name=name;
        this.distance=distance;

    }
    public int getDistance() {
        return distance;
    }
    public String getName() {
        return name;
    }
    public void setDistance(int distance) {
        this.distance = distance;
    }
    public void setName(String name) {
        this.name = name;
    }


}
