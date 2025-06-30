package challenges_30th_june.CarClassChallenge;

public class HybridCar extends Car {
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
    public void startEngine() {
        System.out.println("HybridCar -> startEngine()");
    }

    @Override
    public void drive() {
        System.out.println("HybridCar -> drive(), Avg KM/L: " + avgKmPerLitre + ", Battery: " + batterySize);
        runEngine();
    }

    @Override
    protected void runEngine() {
        System.out.println("HybridCar -> runEngine()");
    }
}

