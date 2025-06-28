import java.util.Scanner;
public class factorsch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number:");
        int n = sc.nextInt();
        printfactors(n);
    }

    public static void printfactors(int n) {
        if (n < 1) {
            System.out.println("invalid value");
            return;
        }
        int i = 1;
        while (i <= n) {
            if (n % i == 0) {
                System.out.print(i + " ");
            }
            i++;
        }
        System.out.println();
    }
}