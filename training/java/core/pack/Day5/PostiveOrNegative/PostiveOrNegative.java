package Day5.PostiveOrNegative;
import java.util.Scanner;

public class PostiveOrNegative {
   public static void main(String args[]) {
	   PositiveNegativeZero p1=new PositiveNegativeZero();
	   Scanner sc=new Scanner(System.in);
	   int num=sc.nextInt();
	   p1.checkNumber(num);
	   
   }
}

 class PositiveNegativeZero {
    
    public static void checkNumber(int number){
        if(number > 0){
            System.out.println("positive");
        }else if(number < 0){
            System.out.println("negative");
        }else{
            System.out.println("zero");
        }
    }
}


