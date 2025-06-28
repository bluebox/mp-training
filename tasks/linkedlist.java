import java.util.LinkedList;
import java.util.Scanner;

public class linkedlist {
    public static void main(String[] args) {

        LinkedList<Integer> l1 = new LinkedList<Integer>();
        l1.add(1);
        l1.add(2);
        l1.add(3);
        l1.add(4);
        l1.add(5);
        System.out.println(l1);
        LinkedList<Integer> l2=new LinkedList<Integer>();
        
        for (int i=(l1.size())-1;i>=0;i--){
            l2.add(l1.get(i));
        }
        System.out.println(l2);
       
       l1.add(0,6);
       System.out.println(l1);
       Scanner sc=new Scanner(System.in);
       System.out.println("enter:");
       int ne=sc.nextInt();
       if (l1.contains(ne)){
        l1.remove(Integer.valueOf(ne));
        
       }
       System.out.println(l1);


       
       
    
}
}
