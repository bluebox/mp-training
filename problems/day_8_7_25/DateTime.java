package day_8_7_25;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.zone.ZoneRules;
import java.util.Locale;

public class DateTime<locale> {
	
	private Record Employee(String name,Locale Locality,ZoneId zone) {
		
		public Employee(String name,Locale Locality,String zone)) {
			this(name,Locality,ZoneId.of(zone));
		}
		
		public Employee(String name,String Locality,String zone)) {
			this(name,Locale.forLanguageTag(Locality),ZoneId.of(zone));
		}
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
           Employee joe=new Employee("Prabhas",Locale.US,"America/New_York") ;
           Employee jane=new Employee("Santosh",Locale.CANADA,"Canada/Pacific");
           
           ZoneRules joesRules = joe.zone.getRules();
           ZoneRules janesRules = jane.zone.getRules();
           System.out.println(jane + " " + janesRules);
           System.out.println(joe + " " + joesRules);

           ZonedDateTime janeNow = ZonedDateTime.now(jane.zone);
           ZonedDateTime joeNow = ZonedDateTime.of(janeNow.toLocalDateTime(), joe.zone);
           long hoursBetween = Duration.between(joeNow, janeNow).toHours();
           long minutesBetween = Duration.between(joeNow, janeNow).toMinutesPart();
           System.out.println("Joe is " + Math.abs(hoursBetween) + " hours " +
                   Math.abs(minutesBetween) + " minutes " +
                   ((hoursBetween < 0) ? "behind" : "ahead"));

           System.out.println("Joe in daylight savings? " +
                   joesRules.isDaylightSavings(joeNow.toInstant()) + " " +
                   joesRules.getDaylightSavings(joeNow.toInstant()) + ": " +
                   joeNow.format(ofPattern("zzzz z")));

           System.out.println("Jane in daylight savings? " +
                   janesRules.isDaylightSavings(janeNow.toInstant()) + " " +
                   janesRules.getDaylightSavings(janeNow.toInstant()) + ": " +
                   janeNow.format(ofPattern("zzzz z")));

           int days = 10;
           var map = schedule(joe, jane, days);
           DateTimeFormatter dtf = ofLocalizedDateTime(FormatStyle.FULL,
                   FormatStyle.SHORT);

           
           for (LocalDate ldt : map.keySet()) {
               System.out.println(ldt.format(ofLocalizedDate(FormatStyle.FULL)));
               for (ZonedDateTime zdt : map.get(ldt)) {
                   System.out.println("\t" +
                           jane.getDateInfo(zdt, dtf) + " <---> " +
                           joe.getDateInfo(zdt.withZoneSameInstant(joe.zone()), dtf));
               }
           }
       }

           
	private static Map<LocalDate, List<ZonedDateTime>> schedule(Employee first,
            Employee second,
            int days) {

Predicate<ZonedDateTime> rules = zdt ->
zdt.getDayOfWeek() != DayOfWeek.SATURDAY
&& zdt.getDayOfWeek() != DayOfWeek.SUNDAY
&& zdt.getHour() >= 7 && zdt.getHour() < 21;

LocalDate startingDate = LocalDate.now().plusDays(2);

return startingDate.datesUntil(startingDate.plusDays(days + 1))
.map(dt -> dt.atStartOfDay(first.zone()))
.flatMap(dt -> IntStream.range(0, 24).mapToObj(dt::withHour))
.filter(rules)
.map(dtz -> dtz.withZoneSameInstant(second.zone()))
.filter(rules)
.collect(
Collectors.groupingBy(ZonedDateTime::toLocalDate,
TreeMap::new, Collectors.toList()));
    }
   
           
	

}
