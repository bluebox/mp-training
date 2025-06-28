import java.util.Scanner;
public class fndlsum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the number");
        int n = sc.nextInt();
        int sum = firstLastSum(n);
        System.out.println("Sum of first and last digit: " + sum);
    }

    public static int firstLastSum(int n) {
        if (n < 0) {
            n = -n;
            int last = n % 10;
            int first = n;
            while (first >= 10) {
                first /= 10;
            }
            return -(first); 
        } else {
            int last = n % 10;
            int first = n;
            while (first >= 10) {
                first /= 10;
            }
            return first + last;
        }
    }
}