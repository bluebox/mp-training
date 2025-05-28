import java.util.*;
public class Enum {

    public enum DayOfTheWeek{
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
    public static DayOfTheWeek getRandomDay(){
        int random=new Random().nextInt(7);
        var allDays=DayOfTheWeek.values();
        System.out.println(Arrays.toString(allDays));
        return allDays[random];
    }
    public static void main(String[] args) {
        DayOfTheWeek day=getRandomDay();
        System.out.println(day);
    }
}
