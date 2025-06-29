package june26_methods;

import java.util.Scanner;

public class LargestPrime {

    public static int getLargestPrime(int number) {
        if (number < 2) {
            return -1;
        }

        int divisor = 2;
        while (number > 1) {
            if (number % divisor == 0) {
                number /= divisor;
            } else {
                divisor++;
            }
        }
        return divisor;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number to find its largest prime factor: ");
        int number = scanner.nextInt();

        int result = getLargestPrime(number);

        if (result != -1) {
            System.out.println("The largest prime factor is: " + result);
        } else {
            System.out.println("Invalid Input");
        }

        scanner.close();
    }
}
