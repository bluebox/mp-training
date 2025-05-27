package methods;

public class SpeedConverter {
	public static long convertToMilesPerHour(double kmph) {
        if (kmph < 0) {
            return -1;
        }
        double conversionFactor = 1.609;
        return Math.round(kmph / conversionFactor);
    }

    public static void displayConversion(double speedInKmph) {
        if (speedInKmph < 0) {
            System.out.println("Invalid Value");
        } else {
            long speedInMph = convertToMilesPerHour(speedInKmph);
            System.out.println(speedInKmph + " km/h = " + speedInMph + " mi/h");
        }
    }
        public static void main(String[] args) {
            SpeedConverter.displayConversion(0);        
            SpeedConverter.displayConversion(1.5);      
            SpeedConverter.displayConversion(10.25);    
            SpeedConverter.displayConversion(-5.0);     
            SpeedConverter.displayConversion(100.0);    
        }
}
