package com.tulasidhar.june26;



import java.util.Scanner;

//usage note: ues number to continue entering input and use anything else to stop the loop
public class ChallengeSumWithUserInput {

    public static void main(String[] args) {
        inputThenPrintSumAndAverage();
    }

    public static void inputThenPrintSumAndAverage() {
        Scanner scanner = new Scanner(System.in);

        int sum = 0;
        int count = 0;

        while (true) {
            boolean isInt = scanner.hasNextInt();
            if (isInt) {
                int number = scanner.nextInt();
                sum += number;
                count++;
            } else {
                break;
            }
        }

        long average = (count == 0) ? 0 : Math.round((double) sum / count);
        System.out.println("SUM = " + sum + " AVG = " + average);

        scanner.close();
    }
}