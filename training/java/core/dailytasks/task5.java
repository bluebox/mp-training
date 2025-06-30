import java.util.Scanner;
public class task5 {
    public static int sumDigits(int number) {
        if (number < 0) {
            return -1;
        } 
        int sum = 0;
            while(number > 0){
        

        int digit = number % 10;
        sum+= digit;
        number /= 10;
        
            }
    
    return sum;
}
    
    
public static void main(String[] args){
    Scanner se = new Scanner(System.in);
    System.out.println("Enter input: ");
    
        int userNumber = se.nextInt();
        int result = sumDigits(userNumber);
        if (result == -1){
            System.out.println("Invalid input.Plese enter a non negative number: ");

        }else {
            System.out.println("the sum of digits for " + userNumber + "is" + result );
        }
          

        }
    


}




