import java.util.Scanner;
public class ispalch {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the number");
        int n =sc.nextInt();
        if (ispal(n)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    public static boolean ispal(int n) {
       
        if (n < 0) {
            n = -n;  
        }
        int reversed = 0;
        int temp = n;
        while (temp > 0) {
            reversed = reversed * 10 + temp % 10;
            temp /= 10;
        }
        return n == reversed;
    }
}