import java.util.Scanner;
public class perfectnch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number:");
        int n = sc.nextInt();
       System.out.println(isperfect(n));
    
        }
    

    public static boolean isperfect(int n) {
        if (n < 1) {
            return false;
        }
        int sum = 0;
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                sum += i;
            }
        }
        return sum == n;
    }
}