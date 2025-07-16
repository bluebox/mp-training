package ScheduleMeeting;

import java.util.TreeMap;
import java.time.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;
import java.util.List;
import java.lang.Math;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.time.Duration;
import java.time.ZoneId;
import java.time.zone.*;

public class Meeting_Plan {
	
	private record Employee(String name,Locale local,ZoneId zone) {
		
		public Employee(String name,String local,String zone) {
			
			this(name,Locale.forLanguageTag(local),ZoneId.of(zone));
		}
		
		public Employee(String name,Locale local,String zone) {
		
			this(name,local,ZoneId.of(zone));
		}
		
		String getDateInfo(ZonedDateTime zdt,DateTimeFormatter dft) {
			return "%s [%s]: %s".formatted(name,zone,zdt.format(dft.localizedBy(local)));
		}
	}
		
	public static void main(String[] args) {
		
		Employee joey=new Employee("Joey",Locale.US,"America/New_York");
		Employee chandler=new Employee("Chandler","en_AU","Australia/Sydney");
		
		ZoneRules joeyRule=joey.zone.getRules();
		ZoneRules chandlerRule=chandler.zone.getRules();
		
		System.out.println(joey+" "+joeyRule);
		System.out.println(chandler+" "+chandlerRule);
		
		ZonedDateTime joeyTime=ZonedDateTime.now(joey.zone);
		ZonedDateTime chandlerTime=ZonedDateTime.of(joeyTime.toLocalDateTime(),chandler.zone);
		
		long hrs_btw=Duration.between(joeyTime, chandlerTime).toHours();
		long mins_btw=Duration.between(joeyTime, chandlerTime).toMinutesPart();
		
		System.out.println("Chandler is "+Math.abs(hrs_btw)+" hours "+Math.abs(mins_btw)+" minutes "+((hrs_btw>0)?"behind":"ahead"));
		
		System.out.println("chandler in DayLight Savings? "+chandlerRule.isDaylightSavings(chandlerTime.toInstant())+" "+
		chandlerRule.getDaylightSavings(chandlerTime.toInstant())+" : "+chandlerTime.format(DateTimeFormatter.ofPattern("zzzz z")));
		
		System.out.println("joey in DayLight Savings? "+joeyRule.isDaylightSavings(joeyTime.toInstant())+" "+
				joeyRule.getDaylightSavings(joeyTime.toInstant())+" : "+joeyTime.format(DateTimeFormatter.ofPattern("zzzz z")));
		
		
		System.out.println("meeting days: "+meeting(chandler,joey,7));
		
	}
	
	public static Map<LocalDate,List<ZonedDateTime>> meeting(Employee first,Employee second,int days){
		
		Predicate<ZonedDateTime> rules=zdt -> zdt.getDayOfWeek()!=DayOfWeek.SATURDAY && 
				zdt.getDayOfWeek()!=DayOfWeek.SUNDAY && 
				zdt.getHour()>=7 && zdt.getHour()<21;
		
		LocalDate startDate=LocalDate.now().plusDays(2);
		
		return startDate.datesUntil(startDate.plusDays(days+1))
				.map(dt->dt.atStartOfDay(first.zone()))
				.flatMap(dt -> IntStream.range(0, 24).mapToObj(dt::withHour))
				.filter(rules)
				.map(dtz -> dtz.withZoneSameInstant(second.zone()))
				.filter(rules)
				.collect(Collectors.groupingBy(ZonedDateTime::toLocalDate,TreeMap::new,Collectors.toList() ));
		
		
		
	}
}
