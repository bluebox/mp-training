import java.util.Scanner;
public class gcdch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first number:");
        int a = sc.nextInt();
        System.out.println("Enter second number:");
        int b = sc.nextInt();
        int result = gcdfibd(a, b);
        System.out.println("GCD: " + result);
    }

    public static int gcdfibd(int a, int b) {
        if (a < 10 || b < 10) {
            return -1;
        }
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
