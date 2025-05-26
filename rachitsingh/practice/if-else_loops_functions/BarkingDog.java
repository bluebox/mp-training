// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.Scanner;
class BarkingDog {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Tell if the dog is barking or not: ");
        boolean barkingStatus = input.nextBoolean();
        
        System.out.println("Enter the hour: ");
        int hourOfDay = input.nextInt();
        
        if(shouldWakeUp(barkingStatus, hourOfDay))
        {
            System.out.println("It's time to Wake up !");
        }
        else
        {
            System.out.println("No need to Wake up.");
        }
        input.close();
    }
    public static boolean shouldWakeUp(boolean barking, int hourOfDay)
    {
        if(hourOfDay < 0 || hourOfDay > 23)
        {
            return false;
        }
        else if(barking && (hourOfDay < 8 || hourOfDay > 22))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
