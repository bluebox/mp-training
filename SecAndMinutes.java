package corejavaday_Two;

public class SecAndMinutes
{

    public static void main(String[] args){

        //minutes and hours
        System.out.println(getDuration(3985));
        System.out.println(getDuration(45,65));
    }
    public static String getDuration(int seconds){
        int minutes=seconds/60;
        return getDuration(minutes,seconds);
    }

    public static String getDuration(int minutes,int seconds){
        int hours=minutes/60;
        int rem_mins=minutes%60;
        int rem_secs=seconds%60;

        return hours+"h "+rem_mins+"m "+rem_secs+"s";
    }
}
