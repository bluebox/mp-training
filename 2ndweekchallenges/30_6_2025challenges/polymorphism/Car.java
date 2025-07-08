public class Car {
    private String description;
    public Car(){
        

    }

    public void startEngine(){
        System.out.println("Engine is started");

    }
    public void drive(){

        System.out.println("car is moving");
        runEngine();
    }
    protected void runEngine(){
        System.out.println("car engine is running");

    }
    
}
