import java.time.*;
import java.util.*;
public class Main {
	public static void main(String[] args) {
		LocalDate date=LocalDate.now();
		System.out.println(date);
		LocalTime time=LocalTime.now();
		System.out.println(time);
		
		
		int dd=date.getDayOfMonth();
		int mm=date.getMonthValue();
		int yy=date.getYear();
		System.out.println(dd+"-"+mm+"-"+yy);
		
		
		int h=time.getHour();
		int m=time.getMinute();
		int s=time.getSecond();
		int ns=time.getNano();
		System.out.println(h+"-"+m+"-"+s+"-"+ns);
		
		
		System.out.println("-----------------");
		LocalDateTime DT=LocalDateTime.now();
		System.out.println(DT);
		int day=DT.getDayOfMonth();
		int month=DT.getMonthValue();
		int year=DT.getYear();
		System.out.println(day+"-"+month+"-"+year);
		int hour=DT.getHour();
		int min=DT.getMinute();
		int sec=DT.getSecond();
		int nsec=DT.getNano();
		System.out.println(hour+"-"+min+"-"+sec+"-"+nsec);
		
		LocalDateTime Birth_Time=LocalDateTime.of(2004,Month.SEPTEMBER,03,12,45);
		System.out.println(Birth_Time);
		System.out.println("After 36 month :"+Birth_Time.plusMonths(36));
		System.out.println("Before 36  months : "+Birth_Time.minusMonths(36));
	}
}
