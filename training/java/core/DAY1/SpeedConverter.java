
public class SpeedConverter {
    public static void main(String[] args) {
        System.out.println(toMilesPerHour(10.25));
    }
    public static double toMilesPerHour(double kilometersPerHour){
        if(kilometersPerHour<0){
            return -1;
        }
        return kilometersPerHour/1.609;
    }
}
