package Methods;

import java.util.Scanner;

public class GetLargestPrime {

    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    public static int getLargestPrime(int number) {
        if (number <= 1) return -1;
        int largestPrime = -1;
        for (int i = 2; i <= number; i++) {
            while (number % i == 0) {
                if (isPrime(i)) {
                    largestPrime = i;
                }
                number /= i;
            }
        }
        return largestPrime;
    }
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int result = getLargestPrime(number);
        System.out.println(result);
        scanner.close();
    }
}
