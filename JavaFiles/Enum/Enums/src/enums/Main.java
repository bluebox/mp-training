package enums;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		DayOfWeek day= DayOfWeek.MON; 
		System.out.println(day.name()+" "+day.ordinal());
		
		var d= day.values();
		
		System.out.println(Arrays.toString(d));
		
		
		DayOfWeek[] days = day.values();
		System.out.println(Arrays.toString(days));

		
		if(day ==DayOfWeek.MON) {
			System.out.println("Monday");
			
		}
		
		for(var da: days) {
			System.out.println(da.name()+" "+da.ordinal());

		}
	}
}
