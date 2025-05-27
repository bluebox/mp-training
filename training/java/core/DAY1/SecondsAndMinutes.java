public class SecondsAndMinutes {
    public static void main(String[] args) {
        System.out.println(getDurationString(20));
    }
    public static String getDurationString(int seconds){
        if(seconds<0){
            return "invalid input";
        }
        int hrs=0,remSec=0,min=0;
        remSec=seconds;
        hrs=seconds/(60*60);
        remSec=seconds%(60*60);
        min=(remSec)/60;
        remSec=remSec%60;
        return hrs+"h "+min+"m "+remSec+"s";
    }
    public static String getDurationString(int minutes,int seconds){
        if(minutes<0){
            return "Entered INVALID INPUT, minutes parameter should be positive ";
        }
        if(seconds<0){
            return "Entered INVALID INPUT, seconds parameter should be >= 0 and <=59  ";
        }
        return getDurationString(seconds+minutes*60);
    }
}
