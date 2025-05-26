import java.util.Scanner;
class TeenNumberChecker{
    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the 3 integer values: ");
        int first = input.nextInt();
        int second = input.nextInt();
        int third = input.nextInt();
        
        if(hasTeen(first, second, third) == true)
        {
            System.out.println("One of the 3 numbers entered is a TEEN i.e within range [13,19]");
        }
        else
        {
             System.out.println("None of the 3 numbers entered is a TEEN i.e within range [13,19]");
        }
        input.close();
    }
    public static boolean hasTeen(int first, int second, int third)
    {
        if((first >= 13 && first <= 19) || (second >= 13 && second <= 19) || (third >= 13 && third <= 19))
        {
            return true;
        }
        return false;
    }
}
