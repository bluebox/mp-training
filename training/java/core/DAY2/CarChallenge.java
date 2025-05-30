package training.java.core.DAY2;

public class CarChallenge {
    public static void main(String[] args) {
        Car car1= new GasPoweredCar("srisai",25,3);
                car1.startEngine();
    }
}
class Car{
    private String description;
    
    public Car(String description) {
        this.description = description;
    }
    
    public void startEngine(){

    }
    public void drive(){
        runEngine();
    }
    protected void runEngine(){}

    public String getDescription() {
        return description;
    }
}
class GasPoweredCar extends Car{
    private double avgKmPerLitre;
    private int cylinders;
    
    public GasPoweredCar( String desc,double avgKmPerLitre, int cylinders) {
        // this.description=desc;
        super(desc);
        this.avgKmPerLitre = avgKmPerLitre;
        this.cylinders = cylinders;
    }

    @Override
    public void startEngine(){
        System.out.printf("%s gas powered car engine is started with %.2f KMPL and %d cylineders in it\n",getDescription(),avgKmPerLitre,cylinders);
    }

    @Override
    public void drive(){
        System.out.printf("%s gas powered car engine is driving with %.2f KMPL and %d cylineders in it\n",getDescription(),avgKmPerLitre,cylinders);
    }
    @Override 
    public void runEngine(){
        System.out.printf("%s gas powered car engine is running with %f KMPL and %d cylineders in it\n",getDescription(),avgKmPerLitre,cylinders);
    }
}
class ElectricCar extends Car{
    private double avgKmPerCharge;
    private int batterySize;
    public ElectricCar(String description, double avgKmPerCharge, int batterySize) {
        super(description);
        this.avgKmPerCharge = avgKmPerCharge;
        this.batterySize = batterySize;
    }
     @Override
    public void startEngine(){
        System.out.printf("%s gas powered car engine is started with %d KMPL and %d batteriees in it",getDescription(),avgKmPerCharge,batterySize);
    }

    @Override
    public void drive(){
          System.out.printf("%s gas powered car engine is started with %d KMPL and %d cylineders in it",getDescription(),avgKmPerCharge,batterySize);
    }
    @Override 
    public void runEngine(){
          System.out.printf("%s gas powered car engine is started with %d KMPL and %d cylineders in it",getDescription(),avgKmPerCharge,batterySize);
    }

}
class HybridCar extends Car{
    private double avgKmPerLitre;
    private int batterySize;
    private int cylinders;
    
    
     public HybridCar(String description, double avgKmPerLitre, int batterySize, int cylinders) {
        super(description);
        this.avgKmPerLitre = avgKmPerLitre;
        this.batterySize = batterySize;
        this.cylinders = cylinders;
    }

     @Override
    public void startEngine(){
        System.out.printf("%s gas powered car engine is started with %d KMPL and %d cylineders in it",getDescription(),avgKmPerLitre,cylinders);
    }

    @Override
    public void drive(){
        System.out.printf("%s gas powered car engine is started with %d KMPL and %d cylineders in it",getDescription(),avgKmPerLitre,cylinders);
    }
    @Override 
    public void runEngine(){
        System.out.printf("%s gas powered car engine is started with %d KMPL and %d cylineders in it",getDescription(),avgKmPerLitre,cylinders);
    }
}