package com.company.day2;

import java.util.Arrays;
import java.util.Random;

public class Array {
    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[5];
        for(int i=0;i<arr.length;i++) {
            arr[i] = random.nextInt(10);
        }
        System.out.println("Array before sorting: " + Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println("Array after sorting: " + Arrays.toString(arr));
    }
}
