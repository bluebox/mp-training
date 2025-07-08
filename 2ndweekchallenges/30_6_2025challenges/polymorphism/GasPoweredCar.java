
public class GasPoweredCar extends Car {

    private double avgKmPerLitre;
    private int cylinders;
    public GasPoweredCar(){
        

    }
    public void startEngine(){
        System.out.println("Gaspowered car is starting");
    }
    public void drive(){
        System.out.println("gaspowered car is moving");
        runEngine();
        
    }
    protected void  runEngine(){
        System.out.println("gaspowered car engine is started");

    }
}