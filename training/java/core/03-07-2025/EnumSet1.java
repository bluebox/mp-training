import java.util.*;
enum Day{
	SUNDAY,MONDAY,TUESDAY,WEDNESDAY,THURSDAY
}

public class EnumSet1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Set<Day> days=EnumSet.range(Day.SUNDAY,Day.THURSDAY);
		for(Day d:days)
		{
			System.out.println(d);
		}

	}

}
