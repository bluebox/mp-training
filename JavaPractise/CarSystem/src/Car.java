public class Car {
    private String model;

    public Car(String model) {
        this.model = model;
    }

    public void startEngine() {
        System.out.println("Starting engine for: " + getClass().getSimpleName());
    }

    public void drive() {
        System.out.println("Now driving " + model);
        runEngine();
    }

    protected void runEngine() {
        System.out.println("Running default car engine");
    }
}