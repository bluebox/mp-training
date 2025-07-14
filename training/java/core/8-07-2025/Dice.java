import java.util.Random;
import java.util.stream.IntStream;
import java.util.Scanner;;


public class Dice {
   public static void main(String[] args) {
    
    
           Random r= new Random();
           
           IntStream dicenumbers= r.ints(5,1,6);
          dicenumbers.forEach(s->{
            System.out.print(s+ " ");});
          System.out.println(" \n enter the number");
          Scanner sc = new Scanner(System.in);
          

        }
}
    

