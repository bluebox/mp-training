import java.util.Scanner;
class MinutesToYearsAndDaysConverter{
    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the minutes: ");
        long minutes = input.nextLong();
        
        printYearsAndDays(minutes);
        input.close();
    }
    public static void printYearsAndDays(long minutes)
    {
        if (minutes < 0)
        {
            System.out.println("Invalid Value.");
        }
        else
        {
            long totalDays = minutes / (60 * 24);
            long years = totalDays / 365;
            long remainingDays  = totalDays % 365;
            System.out.println(minutes + " min = " + years + " y" + " and " + remainingDays +  " d");
        }
    }
}
