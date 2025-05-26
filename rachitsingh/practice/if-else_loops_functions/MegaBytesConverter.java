import java.util.Scanner;

public class MegaBytesConverter {
  public static void main(String args[]) {
      Scanner input = new Scanner(System.in);
      System.out.println("Enter the Kilobytes to be converted: ");
      int kiloBytes = input.nextInt();
      printMegaBytesAndKiloBytes(kiloBytes);
      input.close();
  }
  public static void printMegaBytesAndKiloBytes(int kiloBytes)
  {
      if (kiloBytes < 0)
      {
          System.out.println("Invalid Value");
      }
      int megaBytes = kiloBytes / 1024;
      int remainingKbs = kiloBytes % 1024;
      System.out.println(kiloBytes + " KB = " + megaBytes + " MB and "+ remainingKbs + " KB");
  }
}
