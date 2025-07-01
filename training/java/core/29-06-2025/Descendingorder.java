import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
public class Descendingorder {


    public static void main(String[] args) {
        int[] numbers = new int[5]; 
        Random rand = new Random();

        
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = rand.nextInt(200) ;
        }

        System.out.println("Random numbers:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i] + " ");
        }

      
        Arrays.sort(numbers);

       
        System.out.println("Sorted in descending order:");
        for (int i = numbers.length - 1; i >= 0; i--) {
            System.out.print(numbers[i] + " ");
        }
    }
}





    
        
