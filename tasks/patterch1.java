import java.util.Scanner;
public class patterch1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the square:");
        int n = sc.nextInt();
        printSquareStar(n);
    }

    public static void printSquareStar(int n) {
        if (n < 5) {
            System.out.println("invalid value");
            return;
        }
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                if (r == 1 || r == n || c == 1 || c == n ||
                    r == c || c == (n - r + 1)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}