import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;
public class Dice {


public static void main(String[] args) {
    Random random = new Random();
    IntStream dicevalues = random.ints(5, 1, 6);
    int[] newarr=dicevalues.toArray();
    System.out.println("dice values are");
    for(int na:newarr){
        System.out.print(na+" ");
    }
    
    Scanner sc=new Scanner(System.in);
    String option=sc.nextLine();
    System.out.println(option);
    if(option.equals("ALL")){
        System.out.println("hello");


    }
    else if(option.equals(" ")){
        System.out.println("game over");

    }
    else{
        
        String[] arr=option.split(" ");
        int[] intarr=new int[arr.length];
        for(int i=0;i<arr.length;i++){
            intarr[i]=Integer.parseInt(arr[i]);
        }
        
        
      IntStream dice= Arrays.stream(newarr).filter(s->Arrays.stream(intarr).anyMatch(n->s==n)).map(s->random.nextInt(1, 6));
      System.out.println("input arr "+Arrays.toString(intarr));
      for(int a:intarr){
        for(int i=0;i<newarr.length;i++){
            if(newarr[i]==a){
                newarr[i]=random.nextInt(1,6);
                break;
            }
        }
      }
      System.out.println(Arrays.toString(newarr));
    }
    
    
}

     
}
