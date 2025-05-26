import java.util.Scanner;
class DecimalComparator{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter 2 decimal values for comparision");
        double value1 = input.nextDouble();
        double value2 = input.nextDouble();
        if(areEqualByThreeDecimalPlaces(value1, value2) == true)
        {
            System.out.println("The two values are equal upto 3 decimal places.");
        }
        else
        {
            System.out.println("The two values are not equal upto 3 decimal places.");
        }
        input.close();
    }
    public static boolean areEqualByThreeDecimalPlaces(double value1, double value2)
    {
        long modified1 = (long)(value1 * 1000);
        long modified2 = (long)(value2 * 1000);
        if(modified1 == modified2)
        {
            return true;
        }
        return false;
    }
}
