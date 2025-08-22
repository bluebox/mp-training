public class MinutesAndSeconds {
    public static void main(String[] args) {
        System.out.println(durationStr(-1));
        System.out.println(durationStr(234,45));
    }

    public static String durationStr(int seconds){
        if(seconds >= 0){
        int minutes = seconds / 60;

        return durationStr(minutes , seconds);
        }
        else{
            //System.out.println("Seconds should be positive number");
            return "Seconds should be positive number";
        }
    }

    public static String durationStr(int minutes, int seconds){
        if(minutes>=0 && seconds>=0 && seconds<=59){
        int hours = minutes / 60;

        int remainminutes = minutes % 60;
        int remainseconds = seconds % 60;

        return hours + "h " + remainminutes + "m " + remainseconds + "s" ;}
        else{
            return "Only Positive values are required";
        }
    }

}
