package dataAndTime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class MeetingScheduler {

    private final ZoneId zoneNY = ZoneId.of("America/New_York");
    private final ZoneId zoneSydney = ZoneId.of("Australia/Sydney");

    private final DateTimeFormatter formatterNY = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy, h:mm a", Locale.US);
    private final DateTimeFormatter formatterSydney = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy, h:mm a", Locale.US);

    public void printAvailableMeetingTimes() {
             ZonedDateTime start=ZonedDateTime.now(zoneNY).plusDays(1).withHour(0).truncatedTo(ChronoUnit.HOURS);

        System.out.println("Available meeting times between New York and Sydney (Next 10 weekdays only):\n");

        for (int day = 0; day < 10; day++) {
            for (int hour = 7; hour <=21; hour++) {
                ZonedDateTime nyTime = start.plusDays(day).withHour(hour);
                ZonedDateTime sydTime = nyTime.withZoneSameInstant(zoneSydney);

                if (isWorkingHour(nyTime) && isWorkingHour(sydTime)) {
                    printMeetingSlot(nyTime, sydTime);
                }
            }
        }
    }

    private boolean isWorkingHour(ZonedDateTime time) {
        DayOfWeek day = time.getDayOfWeek();
        int hour = time.getHour();
        return day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY && hour >= 7 && hour <= 20;
    }

    private void printMeetingSlot(ZonedDateTime nyTime, ZonedDateTime sydTime) {
        System.out.println("New York [" + zoneNY + "] — " + formatterNY.format(nyTime));
        System.out.println("Sydney    [" + zoneSydney + "] — " + formatterSydney.format(sydTime));
        System.out.println("-----------------------------------------------------------------------");
       
    }
}
