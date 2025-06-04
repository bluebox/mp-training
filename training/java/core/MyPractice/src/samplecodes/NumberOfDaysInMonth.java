package samplecodes;

public class NumberOfDaysInMonth {

    public static boolean isLeapYear(int year) {
        if (year < 1 || year > 9999) {
            return false;
        }
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static int getDaysInMonth(int month, int year) {
        if (month < 1 || month > 12 || year < 1 || year > 9999) {
            return -1;
        }

        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                return -1; // should never reach here
        }
    }

    public static void main(String[] args) {

        System.out.println("2000: " + NumberOfDaysInMonth.isLeapYear(2000)); 
        System.out.println("1900: " + NumberOfDaysInMonth.isLeapYear(1900)); 
       

     
        System.out.println("February 2024: " + NumberOfDaysInMonth.getDaysInMonth(2, 2024)); 
        System.out.println("February 2023: " + NumberOfDaysInMonth.getDaysInMonth(2, 2023)); 
        
    }
}
