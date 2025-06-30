public class MinutesToYearsDaysCalculator {

    public static void printYearsAndDays(long minutes) {
        if (minutes < 0) {
            System.out.println("Invalid Value");
            return;
        }

        long years = minutes / 525600; // 365 * 24 * 60
        long days = (minutes % 525600) / 1440; // remaining minutes to days

        System.out.println(minutes + " min = " + years + " y and " + days + " d");
    }

    public static void main(String[] args) {
        // Test cases from the image
        printYearsAndDays(525600);     // Expected: 525600 min = 1 y and 0 d
        printYearsAndDays(1051200);    // Expected: 1051200 min = 2 y and 0 d
        printYearsAndDays(561600);     // Expected: 561600 min = 1 y and 26 d
        printYearsAndDays(-500);       // Expected: Invalid Value (extra test case)
    }
}
