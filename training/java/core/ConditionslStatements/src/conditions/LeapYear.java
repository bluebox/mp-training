package conditions;

public class LeapYear {
	public static boolean isLeapYear(int year) {
        if (year < 1 || year > 9999) {
            return false;
        }
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
	public static void main(String[] args) {
        System.out.println(LeapYear.isLeapYear(1600)); 
        System.out.println(LeapYear.isLeapYear(2000)); 
        System.out.println(LeapYear.isLeapYear(2020));
        System.out.println(LeapYear.isLeapYear(2023));
        System.out.println(LeapYear.isLeapYear(-1600)); 
        System.out.println(LeapYear.isLeapYear(10000));
    }

}
