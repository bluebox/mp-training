import java.util.Scanner;
class SecondsAndMinutesChallenge {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the seconds: ");
        int seconds = input.nextInt();
        String result = getDurationString(seconds);
        System.out.println("HH : MM: SS format: " + result);
        input.close();
    }
    public static String getDurationString(int seconds)
    {
        if(seconds < 0)
        {
            return "Invalid value entered for seconds parameter.";
        }
        else
        {
            int minutes = seconds / 60;
            int remainingSeconds = seconds % 60;
            return getDurationString(minutes, remainingSeconds);
        }
    }
    public static String getDurationString(int minutes, int seconds)
    {
        if(minutes < 0 || seconds < 0)
        {
            return "Invalid values entered for minutes and seconds parameter.";
        }
        else
        {
            int hours = minutes / 60;
            int remainingMinutes = minutes % 60;
            return (hours + "h " + remainingMinutes + "m " + seconds + "s" );
        }
    }
    
}
