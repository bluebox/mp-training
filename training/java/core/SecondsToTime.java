public class SecondsToTime {

    public static void secondsToActualTime(int seconds){
        int hr = (int) seconds / 3600;
        int rem_sec = seconds  % 3600;
        int mins = (int) rem_sec / 60;

        int final_rem_sec = seconds % 60;

        System.out.printf("%dHH:%dMM:%dSS\n",hr,mins , final_rem_sec);
    }
    public static void main(String[] args) {
        secondsToActualTime(6789);
    }
}
