package OOPS;

class Vehicle{

    private String model;
    private int year;
    Vehicle(){
        this.model = "unknown";
        this.year = -1;

    }
    Vehicle(String model , int year){
        this.model = model;
        this.year = year;
    }

    public void setModel(String model){
        this.model = model;
    }

    public void setYear(int year){
        this.year = year;
    }

    public String getModel(){
        return this.model;
    }

    public int getYear(){
        return this.year;
    }

    public void run(){
        System.out.println("Vehicle is running");
    }
  
    
    


}

class Car extends Vehicle{
    private String model;
    private int year;
    private int mileage;

    Car(){
        this.model = "unknown";
        this.year = -1;
        this.mileage = -1;
    }

    Car(String model , int year , int mileage){
        super(model , year);
        this.model = model;
        this.year = year;
        this.mileage = mileage;
    }

    public void checkMileage()
    {
        System.out.println("mileage is : "+this.mileage+" per hour");
    }

    public void run(){
        System.out.println("my car is running now..");

    }


}

public class Polymorphism {
    public static void main(String[] args) {
        Car car = new Car("swift" ,2025 , 20);

        car.run();
        
        car.setModel("fortuner");

        System.out.println(car.getModel());
    }
}
