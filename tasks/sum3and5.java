import java.util.Scanner;
public class sum3and5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the range:");
        int start = sc.nextInt();
        int end = sc.nextInt();
        System.out.println("count:");
        int count = sc.nextInt();
        int sum = 0;
        int found = 0; 
        int i = start;
        while ((i <= end) && (found < count)) {
            if (i % 3 == 0 && i % 5 == 0) {
                sum += i;
                found++;
            }
            i++;
        }
        System.out.println("sum of numbers divisible by 3 and 5 is:" + sum);
    }
}