import java.util.Scanner;
public class PallindromeNumber{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int number=sc.nextInt();
        boolean isPallindrome = checkPallindrome(number);
        if(isPallindrome)
        {
            System.out.println(number+ " is Pallindrome");
        }
        else{
            System.out.println(number+" is not a Pallindrome");
        }


    }
    public static boolean checkPallindrome(int number)
    {
        int reverseNumber=0;
        int copyNumber=number;
        while(number>0)
        {
            reverseNumber=reverseNumber*10 + (number%10);
            number/=10;
        }
        return reverseNumber==copyNumber;
    }

}