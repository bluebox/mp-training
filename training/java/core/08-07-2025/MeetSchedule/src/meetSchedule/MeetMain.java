package meetSchedule;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class MeetMain {
	public static void main(String[] args) {
        ZoneId zoneNewYork = ZoneId.of("America/New_York");
        ZoneId zoneSydney = ZoneId.of("Australia/Sydney");

        ZonedDateTime nowNY = ZonedDateTime.now(zoneNewYork).truncatedTo(ChronoUnit.HOURS).plusHours(1);
        LocalTime startTime = LocalTime.of(7, 0);
        LocalTime endTime = LocalTime.of(20, 0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy, h:mm a", Locale.US);

        System.out.println("Possible Meeting Times:\n");

        int daysChecked = 0;
        ZonedDateTime datePointer = nowNY.plusDays(1).withHour(0).withMinute(0);

        while (daysChecked < 10) 
        {
            if (isWeekday(datePointer))
            {
                for (int hour = 7; hour <= 20; hour++) 
                {
                    ZonedDateTime meetingTimeNY = datePointer.withHour(hour);
                    ZonedDateTime meetingTimeSydney = meetingTimeNY.withZoneSameInstant(zoneSydney);

                    LocalTime sydneyTime = meetingTimeSydney.toLocalTime();
                    if (!sydneyTime.isBefore(startTime) && !sydneyTime.isAfter(endTime))
                    {
                        System.out.println("Jane [" + zoneNewYork + "] : " + meetingTimeNY.format(formatter)
                                + "  <-->  Joe [" + zoneSydney + "] : " + meetingTimeSydney.format(formatter));
                    }
                }
                daysChecked++;
            }
            datePointer = datePointer.plusDays(1);
        }
    }

    private static boolean isWeekday(ZonedDateTime dateTime) 
    {
        DayOfWeek day = dateTime.getDayOfWeek();
        return day!=DayOfWeek.SATURDAY && day != DayOfWeek.SATURDAY;
    }

}
