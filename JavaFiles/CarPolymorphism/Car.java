public class Car {
    private String description;

    public Car(String description) {
        this.description = description;
    }

    public void startEngine() {
    }

    public void drive() {
        runEngine();
    }

    protected void runEngine() {
    }

    public String getDescription() {
        return this.description;
    }
}
