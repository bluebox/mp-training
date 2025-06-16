import java.lang.*;
import java.util.*;
class IteratorPractice{
   public static void main(String[] args){
      ArrayList<Integer> al=new ArrayList<>();    
      al.add(1);
      al.add(2);
      al.add(3);
      System.out.println("Using Iterator");
      Iterator<Integer> i=al.iterator();
      while(i.hasNext()){
         System.out.println(i.next());
      }
      System.out.println("---------------------------");
      System.out.println("Using ListIterator");
      ListIterator<Integer> li=al.listIterator();
      System.out.println("Forward direction");
      while(li.hasNext()){
          System.out.println(li.next());
      }
      System.out.println("Backward direction");
      while(li.hasPrevious()){
          System.out.println(li.previous());
      }
      Vector<Integer> v=new Vector<>();
      v.add(10);
      v.add(20);
      v.add(30);
      System.out.println("---------------------------");
      System.out.println("Using Enumeration");
      Enumeration<Integer> e=v.elements();
      while(e.hasMoreElements()){
          System.out.println(e.nextElement());
      }
   }
}   