package com.Day2_;

public class Factor_Of_A_Number {
    public static void main(String args[]) {
        printFactors(3456543);
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
