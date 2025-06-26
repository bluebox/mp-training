
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DatePractice {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println("Current Date: " + date);
        System.out.println("Year: " + date.getYear());
        System.out.println("Month: " + date.getMonth());
        System.out.println("Day of Month: " + date.getDayOfMonth());
        System.out.println("Day of Week: " + date.getDayOfWeek());
        System.out.println("Day of Year: " + date.getDayOfYear());
        System.out.println("Length of Month: " + date.lengthOfMonth());
        System.out.println("Length of Year: " + date.lengthOfYear());
        System.out.println("Is Leap Year: " + date.isLeapYear());

        LocalDate datePlusDays = date.plusDays(10);
        System.out.println("Date + 10 Days: " + datePlusDays);

        LocalDate dateMinusWeeks = date.minusWeeks(3);
        System.out.println("Date - 3 Weeks: " + dateMinusWeeks);

        LocalDate specificDate = LocalDate.of(2025, Month.JANUARY, 1);
        System.out.println("Specific Date: " + specificDate);

        System.out.println("Is Before Today: " + specificDate.isBefore(date));
        System.out.println("Is After Today: " + specificDate.isAfter(date));

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = date.format(dateFormatter);
        System.out.println("Formatted Date: " + formattedDate);

        LocalTime time = LocalTime.now();
        System.out.println("Current Time: " + time);
        System.out.println("Hour: " + time.getHour());
        System.out.println("Minute: " + time.getMinute());
        System.out.println("Second: " + time.getSecond());
        System.out.println("Nano: " + time.getNano());

        LocalTime timePlusHours = time.plusHours(2);
        System.out.println("Time + 2 Hours: " + timePlusHours);

        LocalTime timeMinusMinutes = time.minusMinutes(30);
        System.out.println("Time - 30 Minutes: " + timeMinusMinutes);

        LocalTime specificTime = LocalTime.of(10, 45, 30);
        System.out.println("Specific Time: " + specificTime);

        System.out.println("Is Before Current Time: " + specificTime.isBefore(time));
        System.out.println("Is After Current Time: " + specificTime.isAfter(time));

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(timeFormatter);
        System.out.println("Formatted Time: " + formattedTime);

        long secondsBetween = ChronoUnit.SECONDS.between(specificTime, time);
        System.out.println("Seconds Between: " + secondsBetween);
    }
}
