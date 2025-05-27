package com;

public class SumOfEvenDigits {
    public static int getEvenDigitSum(int number) {
        int sum = 0;
        if(number < 0) return -1;
        while(number > 0) {
            int digit = number%10;
            if(digit%2 == 0) sum += digit;
            number/=10;
        }
        return sum;
    }

    public static void main(String[] args) {
        int ans1 = getEvenDigitSum(123456789);
        System.out.println(ans1);
        int ans2 = getEvenDigitSum(252);
        System.out.println(ans2);
        int ans3 = getEvenDigitSum(-22);
        System.out.println(ans3);;
    }
}