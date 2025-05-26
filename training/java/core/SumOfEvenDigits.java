import java.util.Scanner;
public class SumOfEvenDigits {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int number=sc.nextInt();
        
        int sum=sumOfEvenDigits(number);
        System.out.println("sum of digits "+sum);

    }
    static int sumOfEvenDigits(int number)
    {
        int sum=-1;
        while(number>0)
        {
            int digit=number%10;
            if(digit%2==0)
            {
                sum+=digit;
            }
            number/=10;
        }
        return sum;
        
    }
    
}
