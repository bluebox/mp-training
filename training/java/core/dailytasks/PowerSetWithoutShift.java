
import java.util.*;

public class PowerSetWithoutShift {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        String[] arr = new String[n];
        System.out.print("Enter elements: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.next();
        }

        int total = (int) Math.pow(2, n);
        System.out.println("\nPowerset:");
        for (int i = 0; i < total; i++) {
            String binary = String.format("%" + n + "s", Integer.toBinaryString(i)).replace(' ', '0');
            System.out.print("{ ");
            for (int j = 0; j < n; j++) {
                if (binary.charAt(j) == '1') {
                    System.out.print(arr[j] + " ");
                }
            }
            System.out.println("}");
        }
    }
}