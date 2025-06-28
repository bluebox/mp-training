import java.util.Scanner;
public class minch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the number of minutes:");
        long mins = sc.nextLong();
        printyearsanddays(mins);
    }

    public static void printyearsanddays(long mins) {
        if (mins < 0) {
            System.out.println("invalid value");
        } else {
            long years = mins / (60 * 24 * 365);
            long days = (mins / (60 * 24)) % 365;
            System.out.println(mins + " XX " + years + " y " + days + " d");
        }
    }
}