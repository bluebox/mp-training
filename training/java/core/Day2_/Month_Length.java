package com.Day2_;

public class Month_Length {

    public static void main(String[] args) {
    	
        System.out.println(isLeapYear(2024));  
        System.out.println(isLeapYear(10000)); 
        System.out.println(getDaysInMonth(1, 2020));
        System.out.println(getDaysInMonth(2, 2020));
    }

    public static boolean isLeapYear(int year) {
        if (year < 1 || year > 8000) {
            return false;
        }
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }

    public static int getDaysInMonth(int month, int year) {
        if (month < 1 || month > 12 || year < 1 || year > 8000) {
            return -1;
        }

        switch (month) {
            case 1:  case 3:  case 5:
            case 7:  case 8:  case 10: case 12:
                return 31;

            case 4: case 6:
            case 9: case 11:
                return 30;

            case 2:
                if (isLeapYear(year)) {
                    return 29;
                } else {
                    return 28;
                }

            default:
                return -1;
        }
    }
}
