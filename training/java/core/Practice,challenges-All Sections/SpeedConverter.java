public class SpeedConverter {
    public static void main(String[] args) {
        printConversion(-1.2);
        printConversion(35);
        printConversion(120);
        printConversion(1);

        printConversion(1.5);
        printConversion(10.25);


    }
    public static long toMilesPerHour(double kilometersPerHour){
        if(kilometersPerHour<0){
            return -1;
        }
        return (long)Math.round((kilometersPerHour*1.609));
    }
    public static long toKilometersPerHour(double kilometersPerHour){
        if(kilometersPerHour<0){
            return -1;
        }
        return (long)Math.round((kilometersPerHour/1.609));
    }
    public static void printConversion(double kilometersPerHour){
        if(kilometersPerHour<0){
            System.out.println("Invalid Value");
        }else{
            System.out.println(kilometersPerHour+" km/h = "+toMilesPerHour(kilometersPerHour) +" M/h");
            System.out.println(kilometersPerHour+" M/h= "+toKilometersPerHour(kilometersPerHour) +" km/h");

        }
    }
}