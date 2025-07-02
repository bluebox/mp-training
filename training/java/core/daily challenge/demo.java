import java.util.Scanner;

public class demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        
        System.out.print("Enter number of elements: ");
        int size = sc.nextInt();
        int[] arr = new int[size];

        System.out.println("Enter the elements (positive or negative):");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        int n = arr.length;
        int k = n - 1;
        int maxSum = Integer.MIN_VALUE;
        int[] maxSubarray = new int[k];  

        for (int i = 0; i < n; i++) {
            int sum = 0;
            int[] currentSubarray = new int[k];

            for (int j = 0; j < k; j++) {
                int index = (i + j) % n;
                currentSubarray[j] = arr[index];
                sum += arr[index];
            }

            if (sum > maxSum) {
                maxSum = sum;

                
                for (int m = 0; m < k; m++) {
                    maxSubarray[m] = currentSubarray[m];
                }
            }
        }

     
        System.out.print("\nSubarray with maximum sum: [ ");
        for (int num : maxSubarray) {
            System.out.print(num + " ");
        }
        System.out.println("]"); 

        System.out.println("Maximum sum: " + maxSum);
        System.out.println("Subarray length: " + k);
    }
} 