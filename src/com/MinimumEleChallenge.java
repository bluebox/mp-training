package com.company.day2;

import java.util.Arrays;
import java.util.Scanner;

public class MinimumEleChallenge {
    public static int[] readIntegers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];

        for(int i=0;i<n;i++) {
            System.out.println("Enter element " + (i+1));
            arr[i] = scanner.nextInt();
        }

        System.out.println("The array elements are: ");
        System.out.println(Arrays.toString(arr));
        return arr;
    }

    public static int findMinMethod(int[] arr) {
        int mini = Integer.MAX_VALUE;
        for(int i:arr) {
            mini = Math.min(mini, i);
        }
        return mini;
    }

    public static void main(String[] args) {
        int[] arr = readIntegers();
        int mini = findMinMethod(arr);
        System.out.println("The minimum number in the array is " + mini);
    }
}
