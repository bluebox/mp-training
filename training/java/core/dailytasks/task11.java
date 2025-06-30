import java.util.Scanner;

public class task11 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Testing isLeapYear method ---");
        System.out.print("Enter a year to check if it's a leap year (1-9999): ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next();
        }
        int yearForLeap = scanner.nextInt();
        System.out.println(yearForLeap + " is a leap year: " + isLeapYear(yearForLeap));

        System.out.println("\n--- Testing getDaysInMonth method ---");

      
        System.out.print("Enter a month (1-12): ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next();
        }
        int month = scanner.nextInt();

        System.out.print("Enter a year (1-9999): ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next();
        }
        int yearForDays = scanner.nextInt();

        System.out.println("Number of days in " + month + "/" + yearForDays + ": " + getDaysInMonth(month, yearForDays));

        scanner.close(); 
    }

  
    public static boolean isLeapYear(int year) {
        if (year < 1 || year > 9999) {
            return false; // Parameter not in valid range
        }

        
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

  
    public static int getDaysInMonth(int month, int year) {

        if (month < 1 || month > 12) {
            return -1;
        }

    
        if (year < 1 || year > 9999) {
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
            case 2: 
                
                if (isLeapYear(year)) {
                    return 29;
                } else {
                    return 28;
                }
            case 4: 
            case 6: 
            case 9: 
            case 11: 
                return 30;
            default:
               
                return -1;
}
}
}