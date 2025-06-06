package TimeAndDate;
import java.util.TimeZone;

public class TimeZones {

 public static void main(String[] args)
 {
	 
	 TimeZone timezone = TimeZone.getDefault();

     String LocalTimeZoneDisplayName = timezone.getDisplayName();

     System.out.println("Local Time zone : "+LocalTimeZoneDisplayName);
     
     TimeZone defaultTimezone = TimeZone.getDefault();

     System.out.println("The id of default Time zone is");
     System.out.println(timezone.getID());

     String[] availableTimezones = TimeZone.getAvailableIDs();

     System.out.println("Total No of Time Zone Available : "+availableTimezones.length);


     // get all the  timezones  whose offset is 3600000 milliseconds means 1 hour
     String[] timezones = TimeZone.getAvailableIDs(10800000);

     System.out.println("No of Time Zone having time offset 3 hour : "+timezones.length);

     System.out.println("Timezone names having time offset 3 hour");

     for (int i = 0; i < timezones.length; i++) {
         System.out.println("   "+(i+1)+"    "+timezones[i]);
     }
     
     timezone = TimeZone.getTimeZone("Europe/Berlin");

     System.out.println("Display Name : "+timezone.getDisplayName());


     int timeInMilliseconds = timezone.getDSTSavings();

     System.out.println("\nDST of Europe/Berlin is");
     System.out.println(timezone.getDSTSavings());

    
     
 }
}
