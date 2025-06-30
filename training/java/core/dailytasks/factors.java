import java.util.Scanner;
public class factors;
public static void main (String[] args) {
    Scanner s = new Scanner (System.in);
    System.out.println("enter a number :");
    int n = s.nextInt();
    for(int i=1;i<=n ;i++) {
        if(n % 1 == 0) {
            System.out.println(i + " ");
        }


    }

}
