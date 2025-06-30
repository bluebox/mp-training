package challenges_30th_june.CarClassChallenge;

public class Car {
    private String description;

    public Car(String description) {
        this.description = description;
    }

    public void startEngine() {
        System.out.println("Car -> startEngine()");
    }

    public void drive() {
        System.out.println("Car -> drive()");
        runEngine();
    }

    protected void runEngine() {
        System.out.println("Car -> runEngine()");
    }

    public String getDescription() {
        return description;
    }
}

