import java.util.Scanner;
class EqualSumChecker{
    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the 3 values: ");
        int first = input.nextInt();
        int second = input.nextInt();
        int third = input.nextInt();
        
        if(hasEqualSum(first, second, third) == true)
        {
            System.out.println("Sum of " + first + " and " + second + " = " + third);
        }
        else
        {
             System.out.println("Sum of " + first + " and " + second + " not equal to " + third);
        }
        input.close();
    }
    public static boolean hasEqualSum(int first, int second, int third)
    {
        return ((boolean)(first + second == third) ? true : false);
    }
}
