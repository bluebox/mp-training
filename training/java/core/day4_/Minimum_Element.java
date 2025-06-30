package com.day4_;

import java.util.Scanner;

public class Minimum_Element {

    public static void main(String[] args) {
        int[] numbers = readIntegers();
        int min = findMin(numbers);    
        System.out.println("Minimum number is: " + min);
    }

    public static int[] readIntegers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter numbers separated by commas:");
        String input = scanner.nextLine();

        String[] parts = input.split(",");
        int[] array = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            array[i] = Integer.parseInt(parts[i].trim());
        }

        return array;
    }

    public static int findMin(int[] array) {
        int min = array[0]; 

        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }
}