import java.util.Scanner;

public class problem22 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long num = sc.nextLong();
        long n = num;
        long maxPrime = -1;

        while (n % 2 == 0) {
            maxPrime = 2;
            n /= 2;
        }

        for (long i = 3; i <= Math.sqrt(n); i += 2) {
            while (n % i == 0) {
                maxPrime = i;
                n /= i;
            }
        }

        if (n > 2) {
            maxPrime = n;
        }

        System.out.println(maxPrime);
    }
}
