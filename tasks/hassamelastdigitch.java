import java.util.Scanner;
public class hassamelastdigitch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first number:");
        int a = sc.nextInt();
        System.out.println("Enter second number:");
        int b = sc.nextInt();
        System.out.println("Enter third number:");
        int c = sc.nextInt();

        System.out.println(hasSameLastDigit(a, b, c));
    }

    public static boolean hasSameLastDigit(int a, int b, int c) {
        if (!isValid(a) || !isValid(b) || !isValid(c)) {
            return false;
        }
        int aLast = a % 10;
        int bLast = b % 10;
        int cLast = c % 10;
        return (aLast == bLast) || (aLast == cLast) || (bLast == cLast);
    }

    public static boolean isValid(int n) {
        return n >= 10 && n <= 1000;
    }
}