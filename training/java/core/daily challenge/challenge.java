import java.util.Scanner;

public class Challenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(system.in);
        int s = sc.nextInt();
        system.out.println("enter a list: ");
     
        int n = arr.length;

        int maxSumNMinus1 = Integer.MIN_VALUE;
        int maxElementNMinus1 = Integer.MIN_VALUE;

        // Condition 1 & 2: Subarrays of size n-1
        for (int i = 0; i <= n - (n - 1); i++) {
            int sum = 0;
            int localMax = Integer.MIN_VALUE;
            for (int j = i; j < i + (n - 1); j++) {
                sum += arr[j];
                if (arr[j] > localMax) {
                    localMax = arr[j];
                }
            }
            if (sum > maxSumNMinus1) {
                maxSumNMinus1 = sum;
            }
            if (localMax > maxElementNMinus1) {
                maxElementNMinus1 = localMax;
            }
        }

        // Condition 3: Kadane's Algorithm (all possible subarrays)
        int maxKadane = arr[0];
        int currentSum = arr[0];

        for (int i = 1; i < n; i++) {
            currentSum = Math.max(arr[i], currentSum + arr[i]);
            maxKadane = Math.max(maxKadane, currentSum);
        }

        // Output results
        System.out.println("1. Max Sum of Subarray of Size n-1: " + maxSumNMinus1);
        System.out.println("2. Max Element in Subarrays of Size n-1: " + maxElementNMinus1);
        System.out.println("3. Max Sum of Any Subarray (Kadane’s): " + maxKadane);
    }
}