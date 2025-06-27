package com.tulasidhar.june26;

public class ChallangeSummingFirstAndLastDig {
	public static void main(String[] args) {
		System.out.println(sumFirstAndLastDigit(10329));
		System.out.println(sumFirstAndLastDigit(-10329));
		System.out.println(sumFirstAndLastDigit(252));
	}
    public static int sumFirstAndLastDigit(int number) {
        if (number < 0) {
            return -1;
        }

        int lastDigit = number % 10;
        int firstDigit = number;

        while (firstDigit >= 10) {
            firstDigit = firstDigit / 10;
        }

        return firstDigit + lastDigit;
    }
}