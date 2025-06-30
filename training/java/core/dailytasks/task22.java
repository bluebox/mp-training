public class task22 {

    // First method: Converts seconds to minutes and seconds
    public static String getDurationString(int seconds) {
        if (seconds < 0) {
            return "Invalid Value: Seconds must be a non-negative number.";
        }

        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;

        return minutes + "m " + remainingSeconds + "s";
    }

    // Second method: Converts minutes and seconds to hours, minutes, and seconds
    public static String getDurationString(int minutes, int seconds) {
        if (minutes < 0) {
            return "Invalid Value: Minutes must be a non-negative number.";
        }
        if (seconds < 0 || seconds > 59) {
            return "Invalid Value: Seconds must be between 0 and 59.";
        }

        int totalSeconds = (minutes * 60) + seconds;
        int hours = totalSeconds / 3600; // 60 seconds * 60 minutes = 3600 seconds in an hour
        int remainingMinutes = (totalSeconds % 3600) / 60;
        int finalSeconds = totalSeconds % 60;

        return hours + "h " + remainingMinutes + "m " + finalSeconds + "s";
    }

    public static void main(String[] args) {
        System.out.println(getDurationString(65, 45)); 
        System.out.println(getDurationString(3945));   
        System.out.println(getDurationString(-10));    
        System.out.println(getDurationString(65, -10)); 
        System.out.println(getDurationString(-1, 45)); 
    }
}