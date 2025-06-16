package samplecodes;
import java.util.*;
public class InputChallenge {
    public static void main(String[] args) {
        int sum = 0;
        int counter = 1;

        Scanner scanner = new Scanner(System.in);
        while (counter <= 10) {
            System.out.println("Enter Value #" + counter);
            boolean hasNextInt = scanner.hasNextInt();
            if (hasNextInt) {
                sum += scanner.nextInt();
                counter++;
            } else {
                System.out.println("Invalid Value");
            }
            scanner.nextLine();
        }
        System.out.println(sum);
        scanner.close();
    }
}
