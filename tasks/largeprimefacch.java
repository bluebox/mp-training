import java.util.Scanner;
public class largeprimefacch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number:");
        int n = sc.nextInt();
        int result = largestPrimeFactor(n);
        System.out.println(result);
    }

    public static int largestPrimeFactor(int n) {
        if (n <= 0) {
            return -1;
        }
        int largest = -1;
        
        while (n % 2 == 0) {
            largest = 2;
            n /= 2;
        }
        
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            while (n % i == 0) {
                largest = i;
                n /= i;
            }
        }
      
        if (n > 2) {
            largest = n;
        }
        return largest;
    }}