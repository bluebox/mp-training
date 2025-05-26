import java.util.Scanner;

public class SpeedConverter {
  public static void main(String args[]) {
      
    Scanner input = new Scanner(System.in);
    System.out.println("Enter the speed in km/hr: ");
    double speed = input.nextDouble();
    
    System.out.println("Speed converted in miles/hr is : "+ toMilesPerHour(speed) + "miles/hr");
    input.close();
  }
  public static long toMilesPerHour(double kilometersPerHour)
  {
      if(kilometersPerHour < 0)
      {
          return -1;
      }
      else
      {
          double milesPerHour = Math.round(kilometersPerHour * 0.621371);
          return (long)milesPerHour;
      }
  }
}
