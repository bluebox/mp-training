package com.Day2_;

public class Factors_Of_A_Given_NUmber {
    public static void main(String args[]) {
        printFactors(85);
        printFactors(34);
    }

    public static void printFactors(int number) {
        if (number < 1) {
            System.out.println("Invalid Value");
            return;
        }

        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                System.out.println(i);
            }
        }
    }
}
