package Methods;

import java.util.Scanner;

public class PrintSquareStar {
    public static void printSquareStar(int number) {
        if (number < 5) {
            System.out.println("Invalid Value");
            return;
        }

        for (int row = 1; row <= number; row++) {
            for (int col = 1; col <= number; col++) {
                if (row == 1 || row == number || col == 1 || col == number || col == row || col == (number - row + 1)) {
                    System.out.print("*");
                } 
                else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        printSquareStar(number);
        scanner.close();
    }
}
