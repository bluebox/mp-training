public class ElectricCar extends Car {
    private double avgKmPerLitre;
    private int cylinders;
    public ElectricCar(){
        
       
    }

      public void startEngine(){
        System.out.println("Electric car is starting");
    }
    public void drive(){
        System.out.println("Electric car is moving.the avgkmperlit"+this.avgKmPerLitre);
        runEngine();
        
    }
    protected void  runEngine(){
        System.out.println("Electric car engine is started.uses"+this.cylinders+"cylnders");

    }
}
