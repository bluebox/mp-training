public class HybridCar extends Car {
    private double avgKmPerLitre;
    private int batterySize;
    private int cylinders;
    public HybridCar(){
       
    }
       public void startEngine(){

        System.out.println("Hybrid car is starting.it uses "+this.cylinders+"cylinders");
    }
    public void drive(){
        System.out.println("Hybrid car is moving.the avgkmperlitre"+this.avgKmPerLitre);
        runEngine();
        
    }
    protected void  runEngine(){
        System.out.println("Hybrid car engine is started.Engine battery size is "+this.batterySize);

    }
}
