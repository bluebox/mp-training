package june26_methods;

public class getDurationString {

    // Method to convert seconds into hours, minutes, and seconds
    public static String getDurationString(int seconds) {
        if (seconds >= 0) {
            int minutes = seconds / 60;
            int remainingSeconds = seconds % 60;
            return getDurationString(minutes, remainingSeconds);
        } else {
            return "Invalid input: seconds must be >= 0";
        }
    }

    // Method to convert minutes and seconds into hours, minutes, and seconds
    public static String getDurationString(int minutes, int seconds) {
        if (minutes >= 0 && seconds >= 0 && seconds <= 59) {
            int hours = minutes / 60;
            int remainingMinutes = minutes % 60;
            return hours + " hours " + remainingMinutes + " minutes " + seconds + " seconds";
        } else {
            return "Invalid input: minutes >= 0 and seconds between 0 and 59";
        }
    }

    public static void main(String[] args) {
        System.out.println(getDurationString(234));           // Output from seconds
        System.out.println(getDurationString(234, 45));       // Output from minutes and seconds
    }
}
