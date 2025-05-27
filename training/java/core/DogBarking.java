public class DogBarking {

    public static boolean shouldWakeUp(boolean wake , int hr){
        if (hr >=8 && hr<=22 || wake == false){
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        int hr = 1;
        boolean wake = true;

        System.out.println(shouldWakeUp(wake, hr));
    }
}
