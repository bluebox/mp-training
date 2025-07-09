package differentLocalMeeting;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Employee {

	private String name ;
	private ZoneId zone;
	
	public Employee(String name, String zone) {
		this.name = name;
		this.zone = ZoneId.of(zone);
	}
	
	public ZonedDateTime getZoneDateTime(LocalDate date,int hour) {
		
		return ZonedDateTime.of(date,LocalTime.of(hour, 0),zone);
		
	}

	@Override
	public String toString() {
		return name +" ["+zone+ "] : ";
	}

	public String getName() {
		return name;
	}

	public ZoneId getZone() {
		return zone;
	}
	
}
