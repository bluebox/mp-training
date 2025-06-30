import java.util.Scanner;

public class problem15 {
    Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        int lastA = a % 10;
        int lastB = b % 10;
        int lastC = c % 10;

        if (lastA == lastB && lastB == lastC) {
            System.out.println("All last digits are same");
        } else {
            System.out.println("Last digits are not same");
        }
    }

