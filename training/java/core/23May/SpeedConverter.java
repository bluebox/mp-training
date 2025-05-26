public class SpeedConverter {
    public static void main(String[] args) {
        printConversion(-1.2);
        printConversion(35);
        printConversion(120);

    }
    public static int toMilesPerHour(double kilometersPerHour){
        if(kilometersPerHour<0){
            return -1;
        }
        return (int)Math.round((kilometersPerHour/1.609));
    }
    public static void printConversion(double kilometersPerHour){
        if(kilometersPerHour<0){
            System.out.println("Invalid Value");
        }else{
                System.out.println(kilometersPerHour+" km/h = "+toMilesPerHour(kilometersPerHour) +" M/h");
        }
    }
}