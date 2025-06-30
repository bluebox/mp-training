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
            case 1:  // Jan
            case 3:  // Mar
            case 5:  // May
            case 7:  // Jul
            case 8:  // Aug
            case 10: // Oct
            case 12: // Dec
                return 31;
            case 4:  // Apr
            case 6:  // Jun
            case 9:  // Sep
            case 11: // Nov
                return 30;
            case 2:  // Feb
                return isLeapYear(year) ? 29 : 28;
            default:
                return -1;
        }
    }

    public static void main(String[] args) {
        // Testing isLeapYear
        System.out.println("isLeapYear(-1600) = " + isLeapYear(-1600));   // false
        System.out.println("isLeapYear(1600) = " + isLeapYear(1600));     // true
        System.out.println("isLeapYear(2017) = " + isLeapYear(2017));     // false
        System.out.println("isLeapYear(2000) = " + isLeapYear(2000));     // true

        // Testing getDaysInMonth
        System.out.println("getDaysInMonth(1, 2020) = " + getDaysInMonth(1, 2020));   // 31
        System.out.println("getDaysInMonth(2, 2020) = " + getDaysInMonth(2, 2020));   // 29
        System.out.println("getDaysInMonth(2, 2018) = " + getDaysInMonth(2, 2018));   // 28
        System.out.println("getDaysInMonth(-1, 2020) = " + getDaysInMonth(-1, 2020)); // -1
        System.out.println("getDaysInMonth(1, -2020) = " + getDaysInMonth(1, -2020)); // -1
    }
}
