package Challenges.ImplementMinFinder;

import java.util.Scanner;

public class ImplementMinFinder {

    private static int readInteger(Scanner sc) {
        System.out.println("Enter the number of elements:");
        return sc.nextInt();
    }

    private static int[] readElements(int x, Scanner sc) {
        System.out.println("Enter the elements:");
        int[] a = new int[x];
        for (int i = 0; i < x; i++) {
            a[i] = sc.nextInt();
        }
        return a;
    }

    private static int findMin(int[] a) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min) {
                min = a[i];
            }
        }
        return min;
    }

    // public static void main(String[] args) {
    //     Scanner sc = new Scanner(System.in);
    //     int z = readInteger(sc);
    //     int[] array = readElements(z, sc);
    //     int minValue = findMin(array);
    //     System.out.println("The minimum value is: " + minValue);
    //     sc.close();
    // }
}
