import java.util.Scanner;
public class task7{
    public static boolean isOdd(int number){
        if (number <= 0){
            return false;
        }
        return number % 2 != 0;
    }
    public static int sumOdd(int start,int end){
        if (start <= 0 || end <= 0 || start > end){
            return -1;

        }
        int sum = 0;
        for (int i=start;i<=end;i++){
            if(isOdd(i)){
                sum += i;

            }
        }
        return sum;
    
    }
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
      
        System.out.println("enter a number  start range must be < 0 : ");
        int startRange = scanner . nextInt();
        System.out.println("enter a number must be end range:  ");
        int endRange = scanner.nextInt();
        int result = sumOdd(startRange,endRange);
        if (result == -1){
            System.out.println("invalid input :please enter starts > 0 and ends > 0 and start > end ");
        }else {
            System.out.println ("the sum of odd number between "+ startRange +"and "+ endRange +"is" +result);
        }
    }
}
