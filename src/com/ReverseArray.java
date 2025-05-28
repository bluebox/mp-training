package com.company.day2;

import java.util.Arrays;

public class ReverseArray {
    public static void reverseArray(int[] arr) {
        int n = arr.length;
        int low = 0, high = n-1;
        while(low < high) {
            int temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
            low++;high--;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,2,5};
        System.out.println("Before reversal: " + Arrays.toString(arr));
        reverseArray(arr);
        System.out.println("After reversal: " + Arrays.toString(arr));

        int[] arr2 = {1,2,3,4,5};
        System.out.println("Before reversal: " + Arrays.toString(arr2));
        reverseArray(arr2);
        System.out.println("After reversal: " + Arrays.toString(arr2));
    }
}
