public class MT_3_Speed_Converter{
    public static void main(String[] args) {
        double km = 10.0;          
        double miles = 6.2;        
        
        if (km < 0){
            System.out.println("Kilometers must be a positive value");
        }
        else{
            double kmToMiles = km * 0.621371;
            System.out.println(km + " kilometers = " + kmToMiles + " miles");
        }
        
        
        if (miles < 0){
            System.out.println("Miles must be a positive value");
        }
        else{
            double milesToKm = miles / 0.621371;
            System.out.println(miles + " miles = " + milesToKm + " kilometers");

        }
        
    }
}