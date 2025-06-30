import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class number {

    public static int findMissingNumber(ArrayList<Integer> arr) {
        if (arr == null || arr.size() == 0) {
          
           
            System.out.println("Array is null or empty. No missing number can be determined.");
            return Integer.MIN_VALUE; 
        }

        // Step 1: Find the minimum and maximum values in the array
        int minVal = arr.get(0);
        int maxVal = arr.get(0);
        for (int num : arr) {
            if (num < minVal) {
                minVal = num;
            }
            if (num > maxVal) {
                maxVal = num;
            }
        }

        // Step 2: Add all numbers from the array into a HashSet
        Set<Integer> numSet = new HashSet<>();
        for (int num : arr) {
            numSet.add(num);
        }

        // Step 3: Iterate from minVal to maxVal and check which number is missing
        for (int i = minVal; i <= maxVal; i++) {
            if (!numSet.contains(i)) {
                return i; // Found the missing number
            }
        }

        
        System.out.println("No missing number found within the range defined by the array elements.");
        return Integer.MAX_VALUE; 
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter number");
        int n=sc.nextInt();
        ArrayList<Integer> arr=new ArrayList<>();
        for (int i=0;i<n;i++){
            int a=(sc.nextInt());
            arr.add(a);
        }
        
        int missing = findMissingNumber(arr);

        if (missing != Integer.MIN_VALUE && missing != Integer.MAX_VALUE) {
            System.out.println("The missing number is: " + missing);
        }
    }
}