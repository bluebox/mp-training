import java.util.Scanner;
public class evensum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number:");
        int n = sc.nextInt();
        int sum = evenSum(n);
        System.out.println("Sum of even digits: " + sum);
    }

    public static int evenSum(int n) {
        if (n < 0) {
            return -1;
        }
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            if (digit % 2 == 0) {
                sum += digit;
            }
            n /= 10;
        }
        return sum;
    }}