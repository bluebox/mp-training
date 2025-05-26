import java.util.Scanner;
class EqualityPrinter{
    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter 3 integer values: ");
        int first = input.nextInt();
        int second = input.nextInt();
        int third = input.nextInt();
        
        printEqual(first, second, third);
        input.close();
    }
    public static void printEqual(int first, int second, int third)
    {
        if (first < 0 || second < 0 || third < 0)
        {
            System.out.println("Invalid Value.");
        }
        else
        {
            if(first == second && second == third)
            {
                System.out.println("All numbers are equal.");
            }
            else if(first != second && second != third)
            {
                System.out.println("All numbers are different.");
            }
            else
            {
                System.out.println("Neither all are equal nor different.");
            }
        }
    }
}
